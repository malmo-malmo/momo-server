import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/card/profile_avatar.dart';

Widget attendanceCard({
  required String name,
  required String profile,
  required int rate,
  required int index,
  required int checkIndex,
  required void Function({
    required int index,
    required int checkIndex,
  })
      onSelect,
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
            ProfileAvatar(img: profile, rad: 18.w),
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
        Row(
          mainAxisAlignment: MainAxisAlignment.end,
          children: [
            InkWell(
              onTap: () {
                onSelect(index: index, checkIndex: 0);
              },
              child: CircleAvatar(
                radius: 12,
                backgroundColor: checkIndex == 0
                    ? MomoColor.main
                    : MomoColor.checkBackground,
                child: checkIndex == 0
                    ? const Icon(
                        Icons.check,
                        size: 16,
                        color: MomoColor.white,
                      )
                    : const SizedBox(),
              ),
            ),
            const SizedBox(width: 32),
            InkWell(
              onTap: () {
                onSelect(index: index, checkIndex: 1);
              },
              child: CircleAvatar(
                radius: 12,
                backgroundColor: checkIndex == 1
                    ? MomoColor.main
                    : MomoColor.checkBackground,
                child: checkIndex == 1
                    ? const Icon(
                        Icons.check,
                        size: 16,
                        color: MomoColor.white,
                      )
                    : const SizedBox(),
              ),
            ),
          ],
        ),
      ],
    ),
  );
}
