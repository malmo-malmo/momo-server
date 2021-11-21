import 'package:flutter/cupertino.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/components/member_date_row.dart';
import 'package:momo/app/ui/components/on_off_card.dart';
import 'package:momo/app/util/theme.dart';

Widget searchResultCard({
  required String title,
  required String img,
  required String onOff,
  required String startDay,
  required int headNum,
}) {
  return SizedBox(
    height: 76.h,
    child: Row(
      children: [
        ClipRRect(
          borderRadius: BorderRadius.circular(16),
          child: Image.network(
            img,
            height: 76.h,
            width: 76.h,
            fit: BoxFit.fill,
          ),
        ),
        const SizedBox(width: 14),
        Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            onOffCard(onOff),
            Text(
              title,
              style: MomoTextStyle.normal,
            ),
            memberDateRow(
              headNum,
              startDay,
              color: MomoColor.black,
            ),
          ],
        ),
      ],
    ),
  );
}
