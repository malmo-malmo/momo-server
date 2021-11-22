import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/schedule/schedule_request_provider.dart';
import 'package:momo/app/ui/components/confirm_dialog.dart';
import 'package:momo/app/ui/components/content_input_box.dart';
import 'package:momo/app/ui/components/date_input_card.dart';
import 'package:momo/app/ui/components/name_input_box.dart';
import 'package:momo/app/ui/components/on_off_toggle_button.dart';
import 'package:momo/app/ui/schedule_request/widget/meet_name_drop_down.dart';
import 'package:momo/app/ui/schedule_request/widget/time_input_card.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class ScheduleRequestPage extends ConsumerWidget {
  const ScheduleRequestPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final scheduleRequest = ref.watch(scheduleRequestProvider);
    final check = ref.watch(scheduleRequestCheckProvider);

    return SafeArea(
      child: Scaffold(
        backgroundColor: const Color(0xffffffff),
        body: Padding(
          padding: const EdgeInsets.only(right: 16, left: 16, top: 24),
          child: CustomScrollView(
            slivers: [
              SliverToBoxAdapter(
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Row(
                      children: [
                        InkWell(
                          onTap: () {
                            ref.read(navigatorProvider).pop();
                          },
                          child: Icon(
                            CupertinoIcons.xmark,
                            size: 28.w,
                          ),
                        ),
                        const SizedBox(width: 8),
                        Text(
                          '일정추가',
                          style: MomoTextStyle.defaultStyle,
                        ),
                      ],
                    ),
                    InkWell(
                      onTap: () async {
                        await showDialog(
                          context: context,
                          builder: (context) =>
                              confirmDialog(dialogText: '해당 일정을 추가했어요!'),
                        );
                        FocusScope.of(context).unfocus();
                        ref.read(navigatorProvider).pop();
                      },
                      child: Container(
                        padding: const EdgeInsets.symmetric(vertical: 8),
                        height: 36,
                        width: 64,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(20),
                          color:
                              check ? MomoColor.main : const Color(0xfff0f0f0),
                        ),
                        child: Center(
                            child: Text(
                          '완료',
                          style: MomoTextStyle.small.copyWith(
                            color:
                                check ? MomoColor.white : MomoColor.unSelIcon,
                          ),
                        )),
                      ),
                    ),
                  ],
                ),
              ),
              SliverToBoxAdapter(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    _subTitle('모임선택'),
                    meetNameDropBox(
                      meetName: scheduleRequest.meetName,
                      setMeetName: ref
                          .read(scheduleRequestStateProvider.notifier)
                          .setMeetName,
                    ),
                    _subTitle('일정명'),
                    nameInputBox(
                      onTextChanged: ref
                          .read(scheduleRequestStateProvider.notifier)
                          .setName,
                    ),
                    _subTitle('모임 유형'),
                    onOffToggleButton(
                      tabButton: ref
                          .read(scheduleRequestStateProvider.notifier)
                          .setOnOff,
                    ),
                    _subTitle('날짜 및 시간'),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        DateInputCard(
                          selcetDate: ref
                              .read(scheduleRequestStateProvider.notifier)
                              .setDate,
                        ),
                        TimeInputCard(
                          selcetTime: ref
                              .read(scheduleRequestStateProvider.notifier)
                              .setTime,
                        ),
                      ],
                    ),
                    _subTitle('메모'),
                    contentInputBox(
                      onTextChanged: ref
                          .read(scheduleRequestStateProvider.notifier)
                          .setTexts,
                      maxLines: 2,
                      height: 78,
                    ),
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

  Widget _subTitle(String title) {
    return Padding(
      padding: const EdgeInsets.only(top: 40, bottom: 14),
      child: Text(
        title,
        style: MomoTextStyle.subTitle,
      ),
    );
  }
}
