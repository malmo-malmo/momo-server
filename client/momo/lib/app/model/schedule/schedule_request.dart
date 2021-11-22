import 'package:freezed_annotation/freezed_annotation.dart';

part 'schedule_request.g.dart';
part 'schedule_request.freezed.dart';

@freezed
class ScheduleRequest with _$ScheduleRequest {
  factory ScheduleRequest({
    required int groupId,
    required String title,
    required bool isOffline,
    required String startDateTime,
    required String contents,
  }) = _ScheduleRequest;

  factory ScheduleRequest.fromJson(Map<String, dynamic> json) =>
      _$ScheduleRequestFromJson(json);
}
