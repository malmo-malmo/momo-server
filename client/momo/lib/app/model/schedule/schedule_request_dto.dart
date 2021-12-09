import 'package:freezed_annotation/freezed_annotation.dart';

part 'schedule_request_dto.freezed.dart';

@freezed
class ScheduleRequestDTO with _$ScheduleRequestDTO {
  factory ScheduleRequestDTO({
    required int groupId,
    required String title,
    required String contents,
    required bool isOffline,
    required int year,
    required int month,
    required int day,
    required int hour,
    required int minute,
  }) = _ScheduleRequestDTO;
}
