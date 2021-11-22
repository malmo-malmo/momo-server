import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/components/card/profile_avatar.dart';

Widget memberCard({
  required String name,
  required String profile,
  required int rate,
}) {
  return Container(
    color: Colors.amberAccent,
    height: 100,
    width: double.infinity,
    child: Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Row(
          children: [
            profileAvatar(img: profile, rad: 24.w),
            Column(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                Text(name),
                Text(
                  '$rate%',
                )
              ],
            ),
          ],
        ),
        const CircleAvatar(
          radius: 16,
        ),
      ],
    ),
  );
}
