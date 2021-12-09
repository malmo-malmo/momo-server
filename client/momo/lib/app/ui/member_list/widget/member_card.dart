import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/components/card/profile_avatar.dart';
import 'package:momo/app/util/theme.dart';

Widget memberCard({
  required String name,
  required String profile,
  required int rate,
  required int index,
  required bool check,
  required void Function(int index) onSelect,
}) {
  return Container(
    padding: const EdgeInsets.symmetric(horizontal: 24),
    height: 72,
    width: double.infinity,
    child: Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Row(
          children: [
            profileAvatar(img: profile, rad: 18.w),
            const SizedBox(width: 10),
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  name,
                  style: MomoTextStyle.normal,
                ),
                const SizedBox(height: 4),
                Text(
                  '달성률 $rate%',
                  style:
                      MomoTextStyle.small.copyWith(color: MomoColor.unSelText),
                )
              ],
            ),
          ],
        ),
        InkWell(
          onTap: () {
            onSelect(index);
          },
          child: CircleAvatar(
            radius: 12,
            backgroundColor: check ? MomoColor.main : const Color(0xfff0f0f0),
            child: check
                ? const Icon(
                    Icons.check,
                    size: 16,
                    color: Color(0xfffdfdfd),
                  )
                : const SizedBox(),
          ),
        ),
      ],
    ),
  );
}
