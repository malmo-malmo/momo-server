import 'package:flutter/material.dart';
import 'package:momo/app/ui/calendar/widget/time_line_card.dart';

class TimeLineList extends StatelessWidget {
  const TimeLineList({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: const [
        TimeLineCard(count: 3),
        TimeLineCard(count: 2),
        TimeLineCard(count: 1),
        TimeLineCard(count: 3),
        TimeLineCard(count: 1),
      ],
    );
  }
}
