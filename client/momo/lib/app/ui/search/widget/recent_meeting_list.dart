import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/ui/components/card/group_card.dart';

class RecentMeetingList extends StatelessWidget {
  const RecentMeetingList({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SliverGrid(
      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
          mainAxisSpacing: 14,
          crossAxisSpacing: 14,
          mainAxisExtent: 200.h),
      delegate: SliverChildBuilderDelegate(
        (context, index) => groupCard(
          group: GroupInfo(
            id: 1,
            name: '기초를 위한 영어 회화 모임',
            offline: index % 2 == 0,
            participantCnt: 10,
            startDate: '2021-12-31',
          ),
          width: double.infinity,
          height: 200.h,
        ),
        childCount: 10,
      ),
    );
  }
}
