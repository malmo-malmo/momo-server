import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/schedule/schedule_request_provider.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/button/confirm_action_icon.dart';
import 'package:momo/app/ui/components/button/on_off_toggle_button.dart';
import 'package:momo/app/ui/components/input_box/content_input_box.dart';
import 'package:momo/app/ui/components/input_box/date_input_box.dart';
import 'package:momo/app/ui/components/input_box/name_input_box.dart';
import 'package:momo/app/ui/components/text/sub_title.dart';
import 'package:momo/app/ui/schedule_request/widget/time_input_card.dart';

class ScheduleRequestPage extends ConsumerWidget {
  const ScheduleRequestPage({
    Key? key,
    this.groupId,
  }) : super(key: key);

  final int? groupId;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final check = ref.watch(scheduleRequestCheckProvider(groupId));
    final scheduleRequestState =
        ref.watch(scheduleRequestStateProvider(groupId).notifier);

    return SafeArea(
      child: Scaffold(
        backgroundColor: const Color(0xffffffff),
        appBar: customAppBar(
          leadingIcon: CupertinoIcons.xmark,
          isAction: true,
          title: '일정추가',
          actionWidget: confirmActionIcon(
            check: check,
            title: '완료',
            onTapIcon: () async {
              await scheduleRequestState.createSchedule();
            },
            isShowDialog: true,
            dialogText: '해당 일정을 추가했어요!',
          ),
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: CustomScrollView(
            slivers: [
              SliverToBoxAdapter(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    subTitle(title: '일정명'),
                    nameInputBox(
                        onTextChanged: scheduleRequestState.setTitle,
                        hintText: '제목'),
                    subTitle(title: '모임 유형'),
                    OnOffToggleButton(tabButton: scheduleRequestState.setOnOff),
                    subTitle(title: '날짜 및 시간'),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        DateInputBox(selcetDate: scheduleRequestState.setDate),
                        TimeInputCard(selcetTime: scheduleRequestState.setTime),
                      ],
                    ),
                    subTitle(title: '메모'),
                    contentInputBox(
                        onTextChanged: scheduleRequestState.setContents,
                        maxLines: 2,
                        height: 82,
                        hintText: '일정에 간단한 메모를 남겨주세요'),
                    const SizedBox(height: 88),
                  ],
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
