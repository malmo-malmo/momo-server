import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/components/profile_avatar.dart';
import 'package:momo/app/util/theme.dart';

Widget commentCard({
  required String name,
  required String profile,
  required String text,
}) {
  return Container(
    padding: const EdgeInsets.symmetric(vertical: 20, horizontal: 24),
    height: 80,
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(20),
      color: MomoColor.backgroundColor,
    ),
    child: Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Row(
          children: [
            profileAvatar(
              img: profile,
              rad: 18.w,
            ),
            const SizedBox(width: 16),
            Column(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(name, style: MomoTextStyle.defaultStyle),
                Text(
                  text,
                  style: MomoTextStyle.normal.copyWith(
                    fontWeight: FontWeight.w400,
                  ),
                ),
              ],
            ),
          ],
        ),
        Text(
          '삭제',
          style: MomoTextStyle.small.copyWith(
            color: MomoColor.unSelText,
            decoration: TextDecoration.underline,
          ),
        ),
      ],
    ),
  );
}
