import 'package:flutter/material.dart';
import 'package:momo/app/theme/theme.dart';

Widget titleText(String title) {
  return Text(
    title,
    style: MomoTextStyle.mainTitle,
  );
}

Widget subTitleText(String subTitle) {
  return Text(
    subTitle,
    style: MomoTextStyle.normalR.copyWith(height: 2),
  );
}
