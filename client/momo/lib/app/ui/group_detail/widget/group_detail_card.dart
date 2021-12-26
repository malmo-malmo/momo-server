import 'package:flutter/material.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/group_detail/widget/notice_list_view.dart';
import 'package:momo/app/ui/group_detail/widget/schedule_card.dart';

Widget groupDetailCard({required int groupId}) {
  return SliverToBoxAdapter(
    child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        ScheduleCard(groupId: groupId),
        Container(height: 10, color: MomoColor.backgroundColor),
        NoticeListView(groupId: groupId),
        const Padding(
          padding: EdgeInsets.only(left: 16, top: 27, bottom: 17),
          child: Text('게시물', style: MomoTextStyle.subTitle),
        ),
      ],
    ),
  );
}
