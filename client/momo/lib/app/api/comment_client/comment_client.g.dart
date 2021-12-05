// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'comment_client.dart';

// **************************************************************************
// RetrofitGenerator
// **************************************************************************

class _CommentClient implements CommentClient {
  _CommentClient(this._dio, {this.baseUrl}) {
    baseUrl ??= 'http://localhost:8080/api';
  }

  final Dio _dio;

  String? baseUrl;

  RequestOptions _setStreamType<T>(RequestOptions requestOptions) {
    if (T != dynamic &&
        !(requestOptions.responseType == ResponseType.bytes ||
            requestOptions.responseType == ResponseType.stream)) {
      if (T == String) {
        requestOptions.responseType = ResponseType.plain;
      } else {
        requestOptions.responseType = ResponseType.json;
      }
    }
    return requestOptions;
  }
}
