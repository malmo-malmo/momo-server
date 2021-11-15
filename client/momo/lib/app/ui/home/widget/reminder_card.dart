import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/date_format.dart';
import 'package:momo/app/util/theme.dart';

class ReminderCard extends StatelessWidget {
  const ReminderCard({Key? key}) : super(key: key);

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
          borderRadius: BorderRadius.circular(15),
          color: MomoColor.white,
        ),
        child: Column(
          children: [
            SizedBox(
              height: 89.h,
              child: ListView.builder(
                scrollDirection: Axis.horizontal,
                itemCount: 30,
                itemBuilder: (_, index) {
                  final title = dayTitle(
                    DateTime.now().year,
                    DateTime.now().month,
                    DateTime.now().day + index,
                  );
                  return _dateCard(
                    title: title,
                    day: DateTime.now().day + index,
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
      return InkWell(
        onTap: () {},
        child: Container(
          height: 89.h,
          width: 53.w,
          decoration: BoxDecoration(
            color: check ? MomoColor.main : MomoColor.white,
            borderRadius: BorderRadius.circular(16),
          ),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              Text(title,
                  style: TextStyle(
                      fontSize: 14.sp,
                      color: check ? MomoColor.white : MomoColor.black)),
              Text('$day',
                  style: TextStyle(
                      fontSize: 14.sp,
                      color: check ? MomoColor.white : MomoColor.black)),
            ],
          ),
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
                    style: TextStyle(
                      fontSize: 16.sp,
                      color: MomoColor.black,
                    ),
                  ),
                  SizedBox(height: 4.h),
                  Text(
                    date,
                    style: TextStyle(
                      fontSize: 12.sp,
                      color: MomoColor.black,
                    ),
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
