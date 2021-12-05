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
      height: 148,
      color: const Color(0xffffffff),
      width: double.infinity,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                '다가오는 일정',
                style: MomoTextStyle.subTitle,
              ),
              Transform.rotate(
                angle: pi,
                child: Icon(
                  CupertinoIcons.back,
                  color: MomoColor.black,
                  size: 24.w,
                ),
              ),
            ],
          ),
          const SizedBox(height: 16),
          Container(height: 1, color: MomoColor.divider),
          const SizedBox(height: 20),
          Text(
            '우리 꼭 같이 달려요',
            style: MomoTextStyle.defaultStyle,
          ),
          const SizedBox(height: 8),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                '11/5(금)',
                style: MomoTextStyle.small,
              ),
              Text(
                '오후 6:00 ~ 9:00',
                style: MomoTextStyle.small,
              ),
            ],
          ),
        ],
      ),
    );
  }
}
