import 'package:dio/dio.dart';
import 'package:momo/app/model/common/code_name_pair.dart';
import 'package:momo/app/model/common/district_response.dart';
import 'package:retrofit/retrofit.dart';

part 'district_client.g.dart';

@RestApi(baseUrl: 'http://gunimon.iptime.org:8100/api')
abstract class DistrictClient {
  factory DistrictClient(
    Dio dio, {
    String baseUrl,
  }) = _DistrictClient;

  @GET('/district/cities')
  Future<List<CodeNamePair>> getCities();

  @GET('/district/districts')
  Future<List<DistrictResponse>> getDistricts(
    @Query('cityCode') String cityCode,
  );
}
