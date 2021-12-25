import 'dart:developer' as dp;

import 'package:dio/dio.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:hive_flutter/hive_flutter.dart';
import 'package:momo/app/model/common/token_data.dart';
import 'package:momo/app/util/constant.dart';

final dioProvider = Provider<Dio>((ref) {
  TokenData tokenData = Hive.box('auth').get('tokenData');

  final dio = Dio(
    BaseOptions(
      headers: {
        'Authorization': '${tokenData.accessTokenType} ${tokenData.accessToken}'
      },
      connectTimeout: 5000,
    ),
  );

  final authDio = Dio();

  dio.interceptors.add(CustomLogInterceptor());

  //  QueuedInterceptorsWrapper: 요청이 순차적으로 들어간다
  dio.interceptors.add(QueuedInterceptorsWrapper(
    onError: (error, handler) async {
      dp.log('>>>>>>>>>> onError <<<<<<<<<<');

      // accessToken 만료
      if (error.response?.statusCode == 401) {
        RequestOptions options = error.response!.requestOptions;

        dp.log('>>>>>>>>>> 토큰재발급 <<<<<<<<<<');
        authDio.post(baseUrl + '/oauth/login/refresh',
            data: {'refreshToken': tokenData.refreshToken}).then((response) {
          //  Hive에 tokenData 갱신
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
  return dio;
});

class CustomLogInterceptor extends Interceptor {
  @override
  void onRequest(RequestOptions options, RequestInterceptorHandler handler) {
    dp.log('REQUEST[${options.method}] => PATH: ${options.path}');
    dp.log('REQUEST DATA: ${options.data}');
    dp.log('REQUEST HEADER: ${options.headers}');
    dp.log('REQUEST PARAM: ${options.queryParameters}');
    dp.log('REQUEST URL: ${options.uri}');
    super.onRequest(options, handler);
  }

  @override
  void onResponse(Response response, ResponseInterceptorHandler handler) {
    dp.log(
        'RESPONSE[${response.statusCode}] => PATH: ${response.requestOptions.path}');
    dp.log('RESPONSE DATA => DATA: ${response.data}');
    super.onResponse(response, handler);
  }

  @override
  void onError(DioError err, ErrorInterceptorHandler handler) {
    dp.log(
        'ERROR[${err.response?.statusCode}] => PATH: ${err.requestOptions.path}');
    dp.log('ERROR headers: [${err.requestOptions.headers}]');
    dp.log('ERROR err: [${err.error}]');
    dp.log('ERROR msg: [${err.message}]');
    dp.log('ERROR stackTrace: [${err.stackTrace}]');
    super.onError(err, handler);
  }
}
