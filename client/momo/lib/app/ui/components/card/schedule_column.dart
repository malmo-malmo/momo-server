import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/schedule/calendar_schedule.dart';
import 'package:momo/app/ui/components/card/schedule_card.dart';

Widget scheduleColumn(List<CalendarSchedule> schedules) {
  return ListView.separated(
    itemBuilder: (context, index) => scheduleCard(schedule: schedules[index]),
    separatorBuilder: (context, index) => Padding(
      padding: const EdgeInsets.symmetric(horizontal: 24),
      child: Container(height: 1, width: 280.w, color: const Color(0xffdedede)),
    ),
    itemCount: schedules.length,
  );
}