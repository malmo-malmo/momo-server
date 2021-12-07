import 'package:freezed_annotation/freezed_annotation.dart';

part 'district_response.g.dart';
part 'district_response.freezed.dart';

@freezed
class DistrictResponse with _$DistrictResponse {
  factory DistrictResponse({
    required String districtName,
  }) = _DistrictResponse;

  factory DistrictResponse.fromJson(Map<String, dynamic> json) =>
      _$DistrictResponseFromJson(json);
}
