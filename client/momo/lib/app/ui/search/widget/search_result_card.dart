import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/provider/group/group_provider.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/components/text/member_date_row.dart';
import 'package:momo/app/ui/components/card/on_off_card.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class SearchResultCard extends ConsumerWidget {
  const SearchResultCard({Key? key, required this.group}) : super(key: key);
  final GroupInfo group;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final groupParticipantCnt = ref.watch(groupProvider(group));

    return InkWell(
      onTap: () {
        ref.read(navigatorProvider).navigateTo(
              routeName: AppRoutes.groupDetail,
              arguments: group,
            );
      },
      child: SizedBox(
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
                OnOffCard(onOff: group.offline),
                Text(
                  group.name,
                  style: MomoTextStyle.normal,
                ),
                MemberDateRow(
                  headNum: groupParticipantCnt.participantCnt,
                  startDay: group.startDate,
                  color: MomoColor.black,
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
