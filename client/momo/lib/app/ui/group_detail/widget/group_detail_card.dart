import 'package:flutter/material.dart';
import 'package:momo/app/ui/group_detail/widget/notice_list_view.dart';
import 'package:momo/app/ui/group_detail/widget/schedule_card.dart';
import 'package:momo/app/util/theme.dart';

Widget groupDetailCard({required int groupId}) {
  return SliverToBoxAdapter(
    child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        const ScheduleCard(),
        Container(
          height: 10,
          color: MomoColor.backgroundColor,
        ),
        NoticeListView(
          groupId: groupId,
        ),
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
