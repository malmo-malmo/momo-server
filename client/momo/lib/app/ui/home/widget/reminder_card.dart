import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/date_format.dart';
import 'package:momo/app/util/theme.dart';

class ReminderCard extends StatelessWidget {
  ReminderCard({Key? key}) : super(key: key);

  final _controller = ScrollController(
    initialScrollOffset: 53.w * (DateTime.now().day - 3),
  );

  @override
  Widget build(BuildContext context) {
    return Material(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(26),
      ),
      elevation: 5,
      child: Container(
        padding: const EdgeInsets.only(top: 20),
        height: 342.h,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(26),
          color: MomoColor.white,
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            SizedBox(
              height: 89.h,
              child: ListView.builder(
                controller: _controller,
                scrollDirection: Axis.horizontal,
                itemCount:
                    calendarDay(DateTime.now().year, DateTime.now().month),
                itemBuilder: (_, index) {
                  final title = dayTitle(
                    DateTime.now().year,
                    DateTime.now().month,
                    DateTime.now().day + index,
                  );
                  return _dateCard(
                    title: title,
                    day: index + 1,
                    index: index,
                  );
                },
              ),
            ),
            // SizedBox(height: 5.h),
            _reminderScheduleCard(
              title: '청계천 달리기 & 산책',
              date: '오전 11:00-12:00',
            ),
            Container(height: 1, width: 280.w, color: const Color(0xffdedede)),
            _reminderScheduleCard(
              title: '신촌 카공',
              date: '오후 1:00-3:00',
            ),
            Container(height: 1, width: 280.w, color: const Color(0xffdedede)),
            _reminderScheduleCard(
              title: '서울 맛집탐방',
              date: '오후 7:00-9:00',
            ),
          ],
        ),
      ),
    );
  }

  Widget _dateCard({
    required String title,
    required int day,
    required int index,
  }) {
    return Consumer(builder: (context, ref, _) {
      final check = DateTime.now().day == day;
      return Container(
        height: 89.h,
        width: 53.w,
        decoration: BoxDecoration(
          color: check ? MomoColor.main : MomoColor.white,
          borderRadius: BorderRadius.circular(16),
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            Text(
              title,
              style: MomoTextStyle.normal.copyWith(
                color: check ? MomoColor.white : MomoColor.black,
              ),
            ),
            Text('$day',
                style: MomoTextStyle.normal.copyWith(
                    color: check ? MomoColor.white : MomoColor.black)),
          ],
        ),
      );
    });
  }

  Widget _reminderScheduleCard({
    required String title,
    required String date,
  }) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 18),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Row(
            children: [
              Icon(
                CupertinoIcons.heart_circle,
                size: 36.w,
              ),
              SizedBox(width: 14.w),
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    title,
                    style: MomoTextStyle.defaultStyle,
                  ),
                  const SizedBox(height: 6),
                  Text(
                    date,
                    style: MomoTextStyle.small,
                  ),
                ],
              ),
            ],
          ),
          Transform.rotate(
            angle: pi,
            child: Icon(
              CupertinoIcons.back,
              color: MomoColor.black,
              size: 18.w,
            ),
          ),
        ],
      ),
    );
  }
}
