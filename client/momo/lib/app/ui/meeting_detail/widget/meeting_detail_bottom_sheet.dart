import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

Widget meetingDetailBottomSheet() {
  return SizedBox(
    height: 400,
    child: Column(
      mainAxisAlignment: MainAxisAlignment.spaceAround,
      children: [
        Container(
          height: 10,
          width: 80,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(16),
            color: const Color(0xffd1d1d1),
          ),
        ),
        Text(
          '모임 종료하기',
          style: TextStyle(
            fontSize: 18.sp,
          ),
        ),
        Text(
          '출석체크',
          style: TextStyle(
            fontSize: 18.sp,
          ),
        ),
        Text(
          '공지사항 작성',
          style: TextStyle(
            fontSize: 18.sp,
          ),
        ),
        Text(
          '일정 만들기',
          style: TextStyle(
            fontSize: 18.sp,
          ),
        ),
        Text(
          '게시물 작성',
          style: TextStyle(
            fontSize: 18.sp,
          ),
        ),
      ],
    ),
  );
}
