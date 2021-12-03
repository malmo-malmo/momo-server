import 'package:flutter/material.dart';
import 'package:momo/app/ui/components/app_bar/list_app_bar.dart';
import 'package:momo/app/ui/schedule_list/widget/schedule_list.dart';
import 'package:momo/app/util/theme.dart';

class ScheduleListPage extends StatelessWidget {
  const ScheduleListPage({
    Key? key,
    required this.groupId,
  }) : super(key: key);

  final int groupId;

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: MomoColor.backgroundColor,
        appBar: listAppBar(),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: const [
              SizedBox(height: 25),
              ScheduleList(),
            ],
          ),
        ),
      ),
    );
  }
}
