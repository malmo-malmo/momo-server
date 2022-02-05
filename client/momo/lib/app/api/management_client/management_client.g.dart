// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'management_client.dart';

// **************************************************************************
// RetrofitGenerator
// **************************************************************************

class _ManagementClient implements ManagementClient {
  _ManagementClient(this._dio, {this.baseUrl}) {
    baseUrl ??= 'http://gunimon.iptime.org:8100/api';
  }

  final Dio _dio;

  String? baseUrl;

  @override
  Future<CountResponse> getParticipationGroupCount() async {
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{};
    final _headers = <String, dynamic>{};
    final _data = <String, dynamic>{};
    final _result = await _dio.fetch<Map<String, dynamic>>(
        _setStreamType<CountResponse>(
            Options(method: 'GET', headers: _headers, extra: _extra)
                .compose(_dio.options, '/management/group/participation/count',
                    queryParameters: queryParameters, data: _data)
                .copyWith(baseUrl: baseUrl ?? _dio.options.baseUrl)));
    final value = CountResponse.fromJson(_result.data!);
    return value;
  }

  @override
  Future<List<GroupInfo>> getParticipationGroupDetail() async {
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{};
    final _headers = <String, dynamic>{};
    final _data = <String, dynamic>{};
    final _result = await _dio.fetch<List<dynamic>>(
        _setStreamType<List<GroupInfo>>(
            Options(method: 'GET', headers: _headers, extra: _extra)
                .compose(_dio.options, '/management/groups/participation',
                    queryParameters: queryParameters, data: _data)
                .copyWith(baseUrl: baseUrl ?? _dio.options.baseUrl)));
    var value = _result.data!
        .map((dynamic i) => GroupInfo.fromJson(i as Map<String, dynamic>))
        .toList();
    return value;
  }

  @override
  Future<List<ManagementPostResponse>> getManagementPosts(page, size) async {
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{r'page': page, r'size': size};
    final _headers = <String, dynamic>{};
    final _data = <String, dynamic>{};
    final _result = await _dio.fetch<List<dynamic>>(
        _setStreamType<List<ManagementPostResponse>>(
            Options(method: 'GET', headers: _headers, extra: _extra)
                .compose(_dio.options, '/management/my-posts',
                    queryParameters: queryParameters, data: _data)
                .copyWith(baseUrl: baseUrl ?? _dio.options.baseUrl)));
    var value = _result.data!
        .map((dynamic i) =>
            ManagementPostResponse.fromJson(i as Map<String, dynamic>))
        .toList();
    return value;
  }

  @override
  Future<List<GroupSummaryReseponse>> getParticipationGroupSummary() async {
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{};
    final _headers = <String, dynamic>{};
    final _data = <String, dynamic>{};
    final _result = await _dio.fetch<List<dynamic>>(
        _setStreamType<List<GroupSummaryReseponse>>(
            Options(method: 'GET', headers: _headers, extra: _extra)
                .compose(_dio.options, '/management/groups/participation',
                    queryParameters: queryParameters, data: _data)
                .copyWith(baseUrl: baseUrl ?? _dio.options.baseUrl)));
    var value = _result.data!
        .map((dynamic i) =>
            GroupSummaryReseponse.fromJson(i as Map<String, dynamic>))
        .toList();
    return value;
  }

  @override
  Future<List<MyGroup>> getMyGroupDetail() async {
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{};
    final _headers = <String, dynamic>{};
    final _data = <String, dynamic>{};
    final _result = await _dio.fetch<List<dynamic>>(
        _setStreamType<List<MyGroup>>(
            Options(method: 'GET', headers: _headers, extra: _extra)
                .compose(_dio.options, '/management/my-groups/details',
                    queryParameters: queryParameters, data: _data)
                .copyWith(baseUrl: baseUrl ?? _dio.options.baseUrl)));
    var value = _result.data!
        .map((dynamic i) => MyGroup.fromJson(i as Map<String, dynamic>))
        .toList();
    return value;
  }

  @override
  Future<List<MyGroup>> getMyGroupSummary() async {
    const _extra = <String, dynamic>{};
    final queryParameters = <String, dynamic>{};
    final _headers = <String, dynamic>{};
    final _data = <String, dynamic>{};
    final _result = await _dio.fetch<List<dynamic>>(
        _setStreamType<List<MyGroup>>(
            Options(method: 'GET', headers: _headers, extra: _extra)
                .compose(_dio.options, '/management/my-groups/summary',
                    queryParameters: queryParameters, data: _data)
                .copyWith(baseUrl: baseUrl ?? _dio.options.baseUrl)));
    var value = _result.data!
        .map((dynamic i) => MyGroup.fromJson(i as Map<String, dynamic>))
        .toList();
    return value;
  }

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
