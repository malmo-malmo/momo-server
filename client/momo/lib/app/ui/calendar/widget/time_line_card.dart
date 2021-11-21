import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

class TimeLineCard extends StatelessWidget {
  const TimeLineCard({
    Key? key,
    required this.count,
  }) : super(key: key);

  final int count;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 7),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.center,
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Column(
            children: [
              Text(
                '8',
                style: MomoTextStyle.subTitle,
              ),
              Text(
                'Mon',
                style: MomoTextStyle.normal,
              ),
            ],
          ),
          const SizedBox(width: 16),
          Expanded(
            child: Material(
              borderRadius: BorderRadius.circular(20),
              elevation: 5,
              child: Container(
                padding: const EdgeInsets.symmetric(horizontal: 24),
                height: count * 76,
                child: Center(
                  child: ListView.separated(
                    physics: const NeverScrollableScrollPhysics(),
                    itemBuilder: (context, index) {
                      return _meetingCard(
                        time: '오전 11:00 ~ 12:00',
                        title: '청계천 달리기 & 산책',
                        icon: CupertinoIcons.alarm,
                      );
                    },
                    itemCount: count,
                    separatorBuilder: (BuildContext context, int index) {
                      return Container(
                        height: 1,
                        color: const Color(0xffdedede),
                      );
                    },
                  ),
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }

  Widget _meetingCard({
    required String title,
    required String time,
    required IconData icon,
  }) {
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 16),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Row(
            children: [
              Icon(icon, size: 36.w),
              const SizedBox(width: 14),
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    title,
                    style: MomoTextStyle.defaultStyle,
                  ),
                  const SizedBox(height: 6),
                  Text(
                    time,
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
