import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

Widget titleText(String title) {
  return Text(
    title,
    style: TextStyle(
      fontSize: 28.sp,
      color: MomoColor.black,
    ),
  );
}

Widget subTitleText(String subTitle) {
  return Text(
    subTitle,
    style: TextStyle(
      fontSize: 14.sp,
      color: MomoColor.black,
    ),
  );
}
