import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

class ReqMeetingTitle extends StatelessWidget {
  const ReqMeetingTitle({
    Key? key,
    required this.onOff,
    required this.meetingTitle,
    required this.count,
    required this.startDate,
    required this.location,
    required this.school,
  }) : super(key: key);

  final String onOff;
  final String meetingTitle;
  final int count;
  final String startDate;
  final String location;
  final String school;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(top: 56),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
            width: onOff.length * 11.0 + 21.w,
            height: 25.h,
            child: Center(
              child: Text(
                onOff,
                style: TextStyle(
                  fontSize: 12.sp,
                  color: MomoColor.white,
                ),
              ),
            ),
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(20),
              color: MomoColor.main,
            ),
          ),
          const SizedBox(height: 16),
          Text(
            meetingTitle,
            style: TextStyle(
              fontSize: 28.sp,
              fontWeight: FontWeight.bold,
            ),
          ),
          const SizedBox(height: 24),
          _titleRow(
            icon: CupertinoIcons.location,
            text: location,
            iconSize: 30,
            textSize: 16.sp,
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              _titleRow(
                icon: CupertinoIcons.house,
                text: school,
                iconSize: 30,
                textSize: 16.sp,
              ),
              Row(
                children: [
                  _titleRow(
                    icon: CupertinoIcons.group,
                    text: '$count',
                    iconSize: 20,
                    textSize: 12.sp,
                  ),
                  const SizedBox(width: 16),
                  _titleRow(
                    icon: CupertinoIcons.calendar_badge_plus,
                    text: startDate,
                    iconSize: 20,
                    textSize: 12.sp,
                  ),
                ],
              ),
            ],
          )
        ],
      ),
    );
  }

  Widget _titleRow({
    required IconData icon,
    required String text,
    required double iconSize,
    required double textSize,
  }) {
    return Row(
      children: [
        Icon(
          icon,
          size: iconSize,
        ),
        const SizedBox(width: 8),
        Text(
          text,
          style: TextStyle(
            fontSize: textSize,
          ),
        ),
      ],
    );
  }
}
