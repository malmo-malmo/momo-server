import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/ui/components/card/group_card.dart';

class HomeMeetingList extends StatelessWidget {
  const HomeMeetingList({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 200,
      child: ListView.separated(
        scrollDirection: Axis.horizontal,
        itemBuilder: (context, index) => GroupCard(
          group: GroupInfo(
            favoriteGroup: true,
            id: 1,
            name: '테스트 모임',
            offline: true,
            participantCnt: 10,
            startDate: '',
          ),
          setLike: () {},
        ),
        separatorBuilder: (context, index) => const SizedBox(width: 14),
        itemCount: 10,
      ),
    );
  }
}
