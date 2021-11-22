import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/category.dart';

part 'calendar_schedule.g.dart';
part 'calendar_schedule.freezed.dart';

@freezed
class CalendarSchedule with _$CalendarSchedule {
  factory CalendarSchedule({
    required int groupId,
    required String startDateTime,
    required String title,
    @JsonKey(name: 'groupCategory') required Category category,
  }) = _CalendarSchedule;

  factory CalendarSchedule.fromJson(Map<String, dynamic> json) =>
      _$CalendarScheduleFromJson(json);
}
