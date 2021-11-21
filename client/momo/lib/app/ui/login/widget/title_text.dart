import 'package:flutter/material.dart';
import 'package:momo/app/util/theme.dart';

Widget titleText(String title) {
  return Text(
    title,
    style: MomoTextStyle.mainTitle,
  );
}

Widget subTitleText(String subTitle) {
  return Text(
    subTitle,
    style: MomoTextStyle.normal.copyWith(
      fontWeight: FontWeight.w400,
      height: 2,
    ),
  );
}
