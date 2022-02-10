import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/group/my_group.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';
import 'package:momo/app/ui/my_meet/widget/manage_meeting_card.dart';

class ManageMeetingList extends StatelessWidget {
  const ManageMeetingList({
    Key? key,
    required this.myGroups,
  }) : super(key: key);

  final List<MyGroup> myGroups;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 212.h,
      child: myGroups.isEmpty
          ? const NoItemCard()
          : ListView.separated(
              scrollDirection: Axis.horizontal,
              itemCount: myGroups.length,
              itemBuilder: (context, index) {
                return ManageMeetingCard(
                  id: myGroups[index].id,
                  title: myGroups[index].name,
                  headNum: 3,
                  rate: myGroups[index].achievementRate ?? 0,
                  img: myGroups[index].imageUrl ??
                      'https://img.hani.co.kr/imgdb/resize/2019/0606/53_1559732893_00500014_20190606.JPG',
                );
              },
              separatorBuilder: (_, index) => const SizedBox(width: 14),
            ),
    );
  }
}
