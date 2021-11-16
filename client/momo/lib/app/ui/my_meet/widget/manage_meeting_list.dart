import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/my_meet/widget/manage_meeting_card.dart';

class ManageMeetingList extends StatelessWidget {
  const ManageMeetingList({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 212.h,
      child: ListView.separated(
        scrollDirection: Axis.horizontal,
        itemCount: 10,
        itemBuilder: (_, index) {
          return const ManageMeetingCard(
            title: '주식&재테크 왕초보 스터디',
            headNum: 3,
            rate: 75,
            img:
                'https://img.hani.co.kr/imgdb/resize/2019/0606/53_1559732893_00500014_20190606.JPG',
          );
        },
        separatorBuilder: (_, index) => const SizedBox(width: 14),
      ),
    );
  }
}
