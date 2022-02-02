import 'package:freezed_annotation/freezed_annotation.dart';

part 'count_response.g.dart';
part 'count_response.freezed.dart';

@freezed
class CountResponse with _$CountResponse {
  factory CountResponse({
    required int count,
  }) = _CountResponse;

  factory CountResponse.fromJson(Map<String, dynamic> json) =>
      _$CountResponseFromJson(json);
}
