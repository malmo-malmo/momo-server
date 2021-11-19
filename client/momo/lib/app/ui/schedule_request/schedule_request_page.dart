import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/schedule/schedule_request_provider.dart';
import 'package:momo/app/ui/components/confirm_button.dart';
import 'package:momo/app/ui/components/content_input_box.dart';
import 'package:momo/app/ui/components/date_input_card.dart';
import 'package:momo/app/ui/components/name_input_box.dart';
import 'package:momo/app/ui/components/on_off_toggle_button.dart';
import 'package:momo/app/ui/schedule_request/widget/time_input_card.dart';

class ScheduleRequestPage extends ConsumerWidget {
  const ScheduleRequestPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final scheduleRequest = ref.watch(scheduleRequestProvider);
    final check = ref.watch(scheduleRequestCheckProvider);

    return SafeArea(
      child: Scaffold(
        appBar: AppBar(),
        body: Padding(
          padding:
              const EdgeInsets.only(right: 16, left: 16, top: 24, bottom: 24),
          child: CustomScrollView(
            slivers: [
              SliverToBoxAdapter(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    _subTitle('일정명'),
                    nameInputBox(
                        onTextChanged: ref
                            .read(scheduleRequestStateProvider.notifier)
                            .setName),
                    _subTitle('모임 유형'),
                    onOffToggleButton(
                        tabButton: ref
                            .read(scheduleRequestStateProvider.notifier)
                            .setOnOff),
                    _subTitle('날짜 및 시간'),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        DateInputCard(
                            selcetDate: ref
                                .read(scheduleRequestStateProvider.notifier)
                                .setDate),
                        TimeInputCard(
                            selcetTime: ref
                                .read(scheduleRequestStateProvider.notifier)
                                .setTime),
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
                    const SizedBox(height: 48),
                    ConfirmButton(
                      check: check,
                      buttonText: '완료',
                      dialogText: '해당 일정이 추가되었습니다!',
                      isShowDialog: true,
                    ),
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
      padding: const EdgeInsets.only(top: 43, bottom: 14),
      child: Text(
        title,
        style: TextStyle(
          fontSize: 20.sp,
        ),
      ),
    );
  }
}
