import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/common/code_name_pair.dart';

part 'calendar_schedule.g.dart';
part 'calendar_schedule.freezed.dart';

@freezed
class CalendarSchedule with _$CalendarSchedule {
  factory CalendarSchedule({
    required int groupId,
    required String startDateTime,
    required String title,
    @JsonKey(name: 'groupCategory') required CodeNamePair category,
  }) = _CalendarSchedule;

  factory CalendarSchedule.fromJson(Map<String, dynamic> json) =>
      _$CalendarScheduleFromJson(json);
}
