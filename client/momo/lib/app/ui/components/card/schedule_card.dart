import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/schedule/calendar_schedule.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/components/category/category_icon.dart';
import 'package:momo/app/util/format/time_format.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class ScheduleCard extends StatelessWidget {
  const ScheduleCard({Key? key, required this.schedule}) : super(key: key);

  final CalendarSchedule schedule;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 24),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Row(
            children: [
              categoryIcon(schedule.category.name),
              SizedBox(width: 14.w),
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    schedule.title,
                    style: MomoTextStyle.defaultStyle,
                  ),
                  const SizedBox(height: 6),
                  Text(
                    calStartTime(schedule.startDateTime),
                    style: MomoTextStyle.small,
                  ),
                ],
              ),
            ],
          ),
          Consumer(
            builder: (context, ref, _) {
              return InkWell(
                onTap: () {
                  ref.read(navigatorProvider).navigateTo(
                        routeName: AppRoutes.scheduleList,
                        arguments: schedule.groupId,
                      );
                },
                child: Transform.rotate(
                  angle: pi,
                  child: Icon(
                    CupertinoIcons.back,
                    color: MomoColor.black,
                    size: 18.w,
                  ),
                ),
              );
            },
          ),
        ],
      ),
    );
  }
}
