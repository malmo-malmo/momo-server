import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/ui/components/card/group_card.dart';

class RecentMeetingList extends StatelessWidget {
  const RecentMeetingList({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SliverGrid.count(
      crossAxisCount: 2,
      mainAxisSpacing: 14,
      crossAxisSpacing: 14,
      children: [
        for (int i = 0; i < 10; i++)
          groupCard(
            group: GroupInfo(
              id: 1,
              name: '기초를 위한 영어 회화 모임',
              offline: true,
              participantCnt: 10,
              startDate: '2021-12-31',
            ),
            width: double.infinity,
            height: 300.h,
          ),
      ],
    );
  }
}
