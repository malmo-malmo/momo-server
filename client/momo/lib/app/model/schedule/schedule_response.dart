import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/schedule/schedule_detail.dart';

part 'schedule_response.g.dart';
part 'schedule_response.freezed.dart';

@freezed
class ScheduleResponse with _$ScheduleResponse {
  factory ScheduleResponse({
    required bool manager,
    required List<ScheduleDetail> schedules,
  }) = _ScheduleResponse;

  factory ScheduleResponse.fromJson(Map<String, dynamic> json) =>
      _$ScheduleResponseFromJson(json);
}
