import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:momo/app/model/group/group_summary_response.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';
import 'package:momo/app/ui/my_meet/widget/participation_meeting_card.dart';

class ParticipationMettingList extends StatelessWidget {
  const ParticipationMettingList({
    Key? key,
    required this.participationGroups,
  }) : super(key: key);

  final List<GroupSummaryReseponse> participationGroups;

  @override
  Widget build(BuildContext context) {
    if (participationGroups.isEmpty) {
      return const NoItemCard();
    }
    return Column(
      children: [
        for (int i = 0; i < participationGroups.length; i++)
          ParticipationMeetingCard(
            id: participationGroups[i].id,
            title: participationGroups[i].name,
            category: participationGroups[i].category,
          ),
      ],
    );
  }
}
