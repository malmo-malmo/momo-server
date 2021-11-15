import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

class ScheduleListCard extends StatelessWidget {
  const ScheduleListCard({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 8),
      height: 60,
      width: double.infinity,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(16),
        color: MomoColor.main,
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            '신촌 카공',
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
    );
  }
}
