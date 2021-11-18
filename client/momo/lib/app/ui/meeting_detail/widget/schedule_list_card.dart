import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

class ScheduleListCard extends StatelessWidget {
  const ScheduleListCard({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 16),
      height: 90,
      width: double.infinity,
      color: Colors.lightBlueAccent,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                '다가오는 일정',
                style: TextStyle(
                  fontSize: 16.sp,
                  color: MomoColor.black,
                ),
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
          const SizedBox(height: 16),
          Text(
            '신촌 카공',
            style: TextStyle(
              fontSize: 14.sp,
              color: MomoColor.white,
            ),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                '11/5(금)',
                style: TextStyle(
                  fontSize: 14.sp,
                  color: MomoColor.white,
                ),
              ),
              Text(
                '오후 6:00 ~ 9:00',
                style: TextStyle(
                  fontSize: 14.sp,
                  color: MomoColor.white,
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
