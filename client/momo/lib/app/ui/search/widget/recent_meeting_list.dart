import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/components/card/home_group_card.dart';

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
          homeGroupCard(
            onOff: true,
            title: '기초를 위한 영어 회화 모임',
            headNum: 10,
            date: '9/1~',
            img:
                'https://thumb.mt.co.kr/06/2021/05/2021050417111791900_1.jpg/dims/optimize/',
            width: double.infinity,
            height: 157.h,
          ),
      ],
    );
  }
}
