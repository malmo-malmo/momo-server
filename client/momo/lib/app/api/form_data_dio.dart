import 'package:dio/dio.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:hive_flutter/hive_flutter.dart';
import 'package:momo/app/api/dio_provider.dart';
import 'package:momo/app/model/common/token_data.dart';
import 'package:momo/app/model/group/group_request.dart';
import 'package:momo/app/model/post/post_request.dart';
import 'package:momo/app/model/user/user_info_request.dart';
import 'package:momo/app/util/constant.dart';

final formDataDioProvider = Provider<FormDataDio>((ref) {
  TokenData tokenData = Hive.box('auth').get('tokenData');
  final dio = Dio(
    BaseOptions(
      headers: {
        'Authorization': '${tokenData.accessTokenType} ${tokenData.accessToken}'
      },
      connectTimeout: 10000,
    ),
  );
  dio.interceptors.add(CustomLogInterceptor());

  final authDio = Dio();

  dio.interceptors.add(QueuedInterceptorsWrapper(
    onError: (error, handler) async {
      if (error.response?.statusCode == 401) {
        RequestOptions options = error.response!.requestOptions;

        authDio.post(baseUrl + '/oauth/login/refresh',
            data: {'refreshToken': tokenData.refreshToken}).then((response) {
          Hive.box('auth').put(
            'tokenData',
            TokenData(
              accessToken: response.data['accessToken'],
              accessTokenType: response.data['accessTokenType'],
              refreshToken: response.data['refreshToken'],
            ),
          );
        }).then((e) => dio.fetch(options).then((r) => handler.resolve(r)));
        return;
      }
      return handler.next(error);
    },
  ));
  return FormDataDio(dio);
});

class FormDataDio {
  final Dio dio;

  FormDataDio(this.dio);

  Future<dynamic> createGroup(GroupRequest groupRequest) async {
    final response = await dio.post(
      baseUrl + '/group',
      data: FormData.fromMap(
        {
          'name': groupRequest.name,
          'category': groupRequest.category,
          'isUniversity': groupRequest.isUniversity,
          'city': groupRequest.city,
          'district': groupRequest.district,
          'startDate': groupRequest.startDate,
          'recruitmentCnt': groupRequest.recruitmentCnt,
          'introduction': groupRequest.introduction,
          'isOffline': groupRequest.isOffline,
          'image': await MultipartFile.fromFile(groupRequest.imagePath),
        },
      ),
    );

    return response;
  }

  Future<dynamic> createPost(PostRequest postRequest) async {
    final formData = FormData.fromMap({
      'groupId': postRequest.groupId,
      'title': postRequest.title,
      'contents': postRequest.contents,
      'typeName': postRequest.typeName,
    });

    formData.files.addAll(
      postRequest.images
          .map(
            (e) => MapEntry(
              'images',
              MultipartFile.fromFileSync(e),
            ),
          )
          .toList(),
    );

    final response = await dio.post(
      baseUrl + '/post',
      data: formData,
    );

    return response;
  }

  Future<dynamic> updateUserInfo(UserInfoRequest userInfoRequest) async {
    final response = await dio.post(
      baseUrl + '/user/update',
      data: FormData.fromMap(
        {
          'nickname': userInfoRequest.nickname,
          'university': userInfoRequest.university,
          'city': userInfoRequest.city,
          'district': userInfoRequest.district,
          'image': await MultipartFile.fromFile(userInfoRequest.imagePath),
        },
      ),
    );

    return response;
  }
}
