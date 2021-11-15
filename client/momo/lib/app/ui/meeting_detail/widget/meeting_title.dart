import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

class MeetingTitle extends StatelessWidget {
  const MeetingTitle({
    Key? key,
    required this.onOff,
    required this.meetingTitle,
    required this.count,
    required this.startDate,
  }) : super(key: key);

  final String onOff;
  final String meetingTitle;
  final int count;
  final String startDate;

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
              borderRadius: BorderRadius.circular(13),
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
          const SizedBox(height: 8),
          Row(
            children: [
              _titleRow(
                icon: CupertinoIcons.group,
                text: '$count',
              ),
              const SizedBox(width: 16),
              _titleRow(
                icon: CupertinoIcons.calendar_badge_plus,
                text: startDate,
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
  }) {
    return Row(
      children: [
        Icon(
          icon,
          size: 40,
        ),
        const SizedBox(width: 8),
        Text(
          text,
          style: TextStyle(
            fontSize: 16.sp,
          ),
        ),
      ],
    );
  }
}
