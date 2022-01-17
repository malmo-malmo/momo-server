import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/provider/group/group_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/card/cashed_image_card.dart';
import 'package:momo/app/ui/components/text/member_date_row.dart';
import 'package:momo/app/ui/components/card/on_off_card.dart';
import 'package:momo/app/util/navigation_service.dart';

class GroupCard extends ConsumerWidget {
  const GroupCard({
    Key? key,
    required this.group,
    this.width,
    this.height,
    required this.setLike,
  }) : super(key: key);

  final GroupInfo group;
  final double? width;
  final double? height;
  final Function setLike;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final groupParticipantCnt =
        ref.watch(groupStateProvider(group)).participantCnt;

    return InkWell(
      onTap: () {
        ref.read(navigatorProvider).navigateTo(
              routeName: AppRoutes.groupDetail,
              arguments: group,
            );
      },
      child: Stack(
        children: [
          ClipRRect(
            borderRadius: BorderRadius.circular(20),
            child: CachedImageCard(
              imageUrl: group.imageUrl ??
                  'http://m.pokjukworld.co.kr/web/product/big/pokjukworld_222.jpg',
              height: height ?? 200.h,
              width: width ?? 148,
            ),
          ),
          Container(
            padding: const EdgeInsets.all(14),
            width: width ?? 148,
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
                    OnOffCard(onOff: group.offline),
                    InkWell(
                      onTap: () {
                        setLike();
                      },
                      child: Icon(
                        group.favoriteGroup
                            ? CupertinoIcons.heart_fill
                            : CupertinoIcons.heart,
                        size: 24.w,
                        color: MomoColor.white,
                      ),
                    )
                  ],
                ),
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      group.name,
                      style: MomoTextStyle.defaultStyle
                          .copyWith(color: MomoColor.white),
                      maxLines: 2,
                      overflow: TextOverflow.ellipsis,
                    ),
                    SizedBox(height: 10.h),
                    MemberDateRow(
                        headNum: groupParticipantCnt,
                        startDay: group.startDate),
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
