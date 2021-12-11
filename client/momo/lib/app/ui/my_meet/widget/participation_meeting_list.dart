import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:momo/app/ui/my_meet/widget/participation_meeting_card.dart';

class ParticipationMettingList extends StatelessWidget {
  const ParticipationMettingList({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        for (int i = 0; i < 5; i++)
          const ParticipationMeetingCard(
            title: '청계천 달리기 & 산책',
            time: '오후 7:00 ~ 9:00',
          ),
      ],
    );
  }
}
