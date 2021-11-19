import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/components/profile_avatar.dart';

Widget commentCard({
  required String name,
  required String profile,
  required String text,
}) {
  return Container(
    padding: const EdgeInsets.symmetric(vertical: 16),
    height: 70,
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
              children: [
                Text(name),
                Text(text),
              ],
            ),
          ],
        ),
        Text('삭제'),
      ],
    ),
  );
}
