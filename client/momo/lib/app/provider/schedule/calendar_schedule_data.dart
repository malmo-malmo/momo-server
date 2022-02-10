import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/schedule/calendar_schedule.dart';

part 'calendar_schedule_data.freezed.dart';

@freezed
class CalendarScheduleData with _$CalendarScheduleData {
  factory CalendarScheduleData({
    required bool isLoading,
    required DateTime selectedDate,
    required DateTime requestDate,
    required List<DateTime> scheduleDateTimes,
    required List<List<CalendarSchedule>> schedules,
  }) = _CalendarScheduleData;
}
