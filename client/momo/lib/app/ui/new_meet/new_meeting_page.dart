import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/new_meet/new_meet_provider.dart';
import 'package:momo/app/ui/components/confirm_button.dart';
import 'package:momo/app/ui/components/content_input_box.dart';
import 'package:momo/app/ui/components/date_input_card.dart';
import 'package:momo/app/ui/components/name_input_box.dart';
import 'package:momo/app/ui/components/on_off_toggle_button.dart';
import 'package:momo/app/ui/new_meet/widget/categort_card.dart';
import 'package:momo/app/ui/new_meet/widget/head_num_input_box.dart';
import 'package:momo/app/ui/new_meet/widget/school_input_box.dart';
import 'package:momo/app/ui/new_meet/widget/set_meet_city_box.dart';
import 'package:momo/app/ui/new_meet/widget/set_meet_country_box.dart';
import 'package:momo/app/ui/new_meet/widget/top_box.dart';

class NewMeetingPage extends ConsumerWidget {
  const NewMeetingPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final newMeet = ref.watch(newMeetProvider);
    final check = ref.watch(newMeetCheckProvider);
    final dialogText = "'${newMeet.meetName}' 모임이 생성되었어요!";

    return SafeArea(
      child: Scaffold(
        body: CustomScrollView(
          slivers: [
            const TopBox(),
            SliverToBoxAdapter(
              child: Padding(
                padding: const EdgeInsets.only(left: 26, right: 26, bottom: 24),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    _subTitle('모임이름'),
                    nameInputBox(
                        onTextChanged: ref
                            .read(newMeetStateProvider.notifier)
                            .setMeetName),
                    _subTitle('카테고리'),
                    categoryCards(),
                    _subTitle('모임 유형'),
                    onOffToggleButton(
                        tabButton:
                            ref.read(newMeetStateProvider.notifier).setOnOff),
                    _subTitle('인원 수'),
                    headNumInputBox(
                        onTextChanged:
                            ref.read(newMeetStateProvider.notifier).setHeadNum),
                    _subTitle('모임 시작 날짜'),
                    DateInputCard(
                        selcetDate: ref
                            .read(newMeetStateProvider.notifier)
                            .setStartDay),
                    _subTitle('학교'),
                    schoolInputBox(
                        onTextChanged:
                            ref.read(newMeetStateProvider.notifier).setSchool),
                    _subTitle('지역'),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        SetMeetCityBox(
                          curCity: newMeet.city,
                          onSelect:
                              ref.read(newMeetStateProvider.notifier).setCity,
                        ),
                        SetMeetCountryBox(
                          curCountry: newMeet.country,
                          onSelect: ref
                              .read(newMeetStateProvider.notifier)
                              .setCountry,
                        ),
                      ],
                    ),
                    _subTitle('모임 설명'),
                    contentInputBox(
                      onTextChanged:
                          ref.read(newMeetStateProvider.notifier).setContents,
                      height: 98,
                      maxLines: 6,
                    ),
                    const SizedBox(height: 24),
                    ConfirmButton(
                      dialogText: dialogText,
                      buttonText: '완료',
                      check: check,
                      isShowDialog: true,
                    ),
                  ],
                ),
              ),
            ),
          ],
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
