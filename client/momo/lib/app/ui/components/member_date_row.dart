import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/util/theme.dart';

Widget memberDateRow(int headNum, String startDay, {Color? color}) {
  return Row(
    children: [
      Row(
        children: [
          SvgPicture.asset(
            'assets/icon/icon_member_20.svg',
            color: color ?? MomoColor.white,
          ),
          Text(
            '$headNum',
            style: MomoTextStyle.small.copyWith(
              color: color ?? MomoColor.white,
            ),
          ),
        ],
      ),
      const SizedBox(width: 15),
      Row(
        children: [
          SvgPicture.asset(
            'assets/icon/icon_daystart_20.svg',
            color: color ?? MomoColor.white,
          ),
          Text(
            startDay,
            style: MomoTextStyle.small.copyWith(
              color: color ?? MomoColor.white,
            ),
          ),
        ],
      ),
    ],
  );
}
