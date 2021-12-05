import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/components/text/member_date_row.dart';
import 'package:momo/app/ui/components/card/on_off_card.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget groupCard({
  required GroupInfo group,
  double? width,
  double? height,
}) {
  return Consumer(builder: (context, ref, _) {
    return InkWell(
      onTap: () {
        ref.read(navigatorProvider).navigateTo(
              routeName: AppRoutes.groupDetail,
              arguments: group.id,
            );
      },
      child: Stack(
        children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(20),
            child: Image.network(
              group.imageUrl ??
                  'https://t1.daumcdn.net/cfile/tistory/213E554D58E120D71C',
              fit: BoxFit.fill,
              width: width ?? 148.w,
              height: height ?? 200.h,
            ),
          ),
          Container(
            padding: const EdgeInsets.all(14),
            width: width ?? 148.w,
            height: height ?? 200.h,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(20),
              color: Colors.transparent,
            ),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    onOffCard(group.offline),
                    Icon(
                      CupertinoIcons.heart,
                      size: 24.w,
                      color: MomoColor.white,
                    ),
                  ],
                ),
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      group.name,
                      style: MomoTextStyle.defaultStyle.copyWith(
                        color: MomoColor.white,
                      ),
                    ),
                    SizedBox(height: 10.h),
                    memberDateRow(group.participantCnt, group.startDate),
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  });
}
