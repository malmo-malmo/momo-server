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
      padding: const EdgeInsets.symmetric(vertical: 20),
      child: Material(
        elevation: 5,
        child: Container(
          decoration: BoxDecoration(borderRadius: BorderRadius.circular(16)),
          height: count * 60,
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
                width: 280.w,
                color: const Color(0xffdedede),
              );
            },
          ),
        ),
      ),
    );
  }

  Widget _meetingCard({
    required String title,
    required String time,
    required IconData icon,
  }) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 16),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Row(
            children: [
              Icon(icon),
              const SizedBox(width: 8),
              Column(
                children: [
                  Text(title),
                  Text(time),
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
