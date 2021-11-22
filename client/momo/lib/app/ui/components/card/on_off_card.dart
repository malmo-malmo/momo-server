import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

Widget onOffCard(bool onOff) {
  return Container(
    width: onOff ? 33 + 21.w : 44 + 21.w,
    height: 25.h,
    child: Center(
      child: Text(
        onOff ? '온라인' : '오프라인',
        style: TextStyle(
          fontSize: 12.sp,
          color: onOff ? MomoColor.main : MomoColor.white,
        ),
      ),
    ),
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(25),
      color: onOff ? MomoColor.white : MomoColor.main,
    ),
  );
}
