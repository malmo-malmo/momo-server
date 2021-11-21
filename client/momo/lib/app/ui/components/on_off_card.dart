import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

Widget onOffCard(String onOff) {
  return Container(
    width: onOff.length * 11.0 + 21.w,
    height: 25.h,
    child: Center(
      child: Text(
        onOff,
        style: TextStyle(
          fontSize: 12.sp,
          color: onOff == '온라인' ? MomoColor.main : MomoColor.white,
        ),
      ),
    ),
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(25),
      color: onOff == '온라인' ? MomoColor.white : MomoColor.main,
    ),
  );
}
