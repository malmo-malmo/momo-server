import 'package:flutter/material.dart';
import 'package:momo/app/model/schedule/calendar_schedule.dart';
import 'package:momo/app/ui/components/card/schedule_column.dart';

Widget schedulePageView({
  required List<CalendarSchedule> schedules,
}) {
  // 3개씩 일정 분리
  final sepSchedules = [];
  for (; schedules.length >= 3;) {
    sepSchedules.add(schedules.sublist(0, 3));
    schedules.removeRange(0, 3);
  }
  sepSchedules.add(schedules.sublist(0, schedules.length));

  return PageView(
    children: [
      for (int i = 0; i < sepSchedules.length; i++)
        scheduleColumn(sepSchedules[i])
    ],
  );
}
