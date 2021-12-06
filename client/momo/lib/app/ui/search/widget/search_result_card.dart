import 'package:flutter/cupertino.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/provider/group/group_provider.dart';
import 'package:momo/app/ui/components/text/member_date_row.dart';
import 'package:momo/app/ui/components/card/on_off_card.dart';
import 'package:momo/app/util/theme.dart';

Widget searchResultCard({required GroupInfo group}) {
  return Consumer(builder: (context, ref, _) {
    final groupParticipantCnt = ref.watch(groupProvider(group));

    return SizedBox(
      height: 76.h,
      child: Row(
        children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(16),
            child: Image.network(
              group.imageUrl ??
                  'https://file.mk.co.kr/meet/neds/2021/11/image_readtop_2021_1081514_16372040344854618.jpg',
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
              onOffCard(group.offline),
              Text(
                group.name,
                style: MomoTextStyle.normal,
              ),
              memberDateRow(
                groupParticipantCnt.participantCnt,
                group.startDate,
                color: MomoColor.black,
              ),
            ],
          ),
        ],
      ),
    );
  });
}
