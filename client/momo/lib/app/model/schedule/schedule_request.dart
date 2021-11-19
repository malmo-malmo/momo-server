import 'package:freezed_annotation/freezed_annotation.dart';

part 'schedule_request.g.dart';
part 'schedule_request.freezed.dart';

@freezed
class ScheduleRequest with _$ScheduleRequest {
  factory ScheduleRequest({
    required String name,
    required String onOff,
    required String date,
    required String time,
    required String texts,
  }) = _ScheduleRequest;

  factory ScheduleRequest.fromJson(Map<String, dynamic> json) =>
      _$ScheduleRequestFromJson(json);
}
