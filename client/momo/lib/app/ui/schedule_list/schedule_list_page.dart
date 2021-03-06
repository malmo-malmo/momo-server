import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/schedule_list/widget/schedule_list.dart';

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
        appBar: CustomAppBar(
          leadingIcon: CupertinoIcons.back,
          isAction: true,
          actionWidget: SvgPicture.asset('assets/icon/icon_msg_28.svg'),
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: CustomScrollView(
            slivers: [
              const SliverToBoxAdapter(child: SizedBox(height: 25)),
              ScheduleList(groupId: groupId),
            ],
          ),
        ),
      ),
    );
  }
}
