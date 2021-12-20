import 'package:dio/dio.dart';
import 'package:momo/app/model/user/category_request.dart';
import 'package:momo/app/model/user/university.dart';
import 'package:momo/app/model/user/user_info_request.dart';
import 'package:momo/app/model/user/user_response.dart';
import 'package:retrofit/retrofit.dart';

part 'user_client.g.dart';

@RestApi(baseUrl: 'http://gunimon.iptime.org:8100/api')
abstract class UserClient {
  factory UserClient(
    Dio dio, {
    String baseUrl,
  }) = _UserClient;

  @GET('/user')
  Future<UserResponse> getUserInfo();

  @PATCH('/user')
  Future<dynamic> updateUserInfo(
    @Body() UserInfoRequest userInfoRequest,
  );

  @PATCH('/user/categories')
  Future<dynamic> updateCategory(
    @Body() CategoryRequest categoryRequest,
  );

  @GET('/user/duplicate-nickname')
  Future<dynamic> validateNickname(
    @Query('nickname') String nickname,
  );

  @GET('/universities')
  Future<List<University>> getUniversities(
    @Query('universityName') String universityName,
  );
}
