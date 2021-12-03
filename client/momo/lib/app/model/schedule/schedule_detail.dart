import 'package:freezed_annotation/freezed_annotation.dart';

part 'schedule_detail.g.dart';
part 'schedule_detail.freezed.dart';

@freezed
class ScheduleDetail with _$ScheduleDetail {
  factory ScheduleDetail({
    required int id,
    required String title,
    required String contents,
    required String authorNickname,
    required String authorImage,
    required String startDateTime,
    required bool offline,
    required bool attend,
    required bool attendanceCheck,
  }) = _ScheduleDetail;

  factory ScheduleDetail.fromJson(Map<String, dynamic> json) =>
      _$ScheduleDetailFromJson(json);
}
