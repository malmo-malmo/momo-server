import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/schedule/calendar_schedule.dart';
import 'package:momo/app/ui/components/card/schedule_card.dart';

class ScheduleColumn extends StatelessWidget {
  const ScheduleColumn({Key? key, required this.schedules}) : super(key: key);

  final List<CalendarSchedule> schedules;

  @override
  Widget build(BuildContext context) {
    return ListView.separated(
      physics: const NeverScrollableScrollPhysics(),
      itemBuilder: (context, index) => ScheduleCard(schedule: schedules[index]),
      separatorBuilder: (context, index) => Padding(
        padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 18),
        child:
            Container(height: 1, width: 280.w, color: const Color(0xffdedede)),
      ),
      itemCount: schedules.length,
    );
  }
}
