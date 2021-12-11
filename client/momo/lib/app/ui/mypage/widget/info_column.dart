import 'package:flutter/material.dart';
import 'package:momo/app/util/theme.dart';

Widget infoColumn({
  required int count,
  required String title,
}) {
  return Column(
    children: [
      Container(
        color: const Color(0xffff0000).withOpacity(0.06),
        height: 44,
        width: 44,
        child: Center(
          child: Text(
            '$count',
            style: MomoTextStyle.onboarding.copyWith(
              decoration: TextDecoration.underline,
              color: MomoColor.black,
            ),
          ),
        ),
      ),
      Text(
        title,
        style: MomoTextStyle.normal.copyWith(
          fontWeight: FontWeight.w400,
        ),
      ),
    ],
  );
}
