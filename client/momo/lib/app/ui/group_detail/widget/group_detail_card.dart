import 'package:flutter/material.dart';
import 'package:momo/app/ui/group_detail/widget/notice_list_card.dart';
import 'package:momo/app/ui/group_detail/widget/schedule_list_card.dart';
import 'package:momo/app/util/theme.dart';

Widget groupDetailCard({required int groupId}) {
  return SliverToBoxAdapter(
    child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        const ScheduleListCard(),
        Container(
          height: 10,
          color: MomoColor.backgroundColor,
        ),
        const NoticeListCard(),
        Padding(
          padding: const EdgeInsets.only(left: 16, top: 27, bottom: 17),
          child: Text(
            '게시물',
            style: MomoTextStyle.subTitle,
          ),
        ),
      ],
    ),
  );
}
