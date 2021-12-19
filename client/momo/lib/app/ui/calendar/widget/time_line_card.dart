import 'package:flutter/material.dart';
import 'package:momo/app/model/schedule/calendar_schedule.dart';
import 'package:momo/app/ui/components/card/schedule_column.dart';
import 'package:momo/app/util/format/day_title_format.dart';
import 'package:momo/app/util/theme.dart';

class TimeLineCard extends StatelessWidget {
  const TimeLineCard({Key? key, required this.schedules}) : super(key: key);

  final List<CalendarSchedule> schedules;

  @override
  Widget build(BuildContext context) {
    final dateTime = DateTime.parse(schedules.first.startDateTime);

    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 7),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.center,
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Column(
            children: [
              Text(
                '${dateTime.day}',
                style: MomoTextStyle.subTitle,
              ),
              const SizedBox(height: 4),
              Text(
                dayTitle(dateTime.year, dateTime.month, dateTime.day),
                style: MomoTextStyle.normal,
              ),
            ],
          ),
          const SizedBox(width: 16),
          Expanded(
            child: Material(
              borderRadius: BorderRadius.circular(20),
              elevation: 1,
              child: Container(
                padding: const EdgeInsets.symmetric(vertical: 20),
                height: schedules.length * 76,
                child: scheduleColumn(schedules),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
