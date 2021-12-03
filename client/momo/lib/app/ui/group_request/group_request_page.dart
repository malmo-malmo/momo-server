import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/group/category_check_provider.dart';
import 'package:momo/app/provider/group/group_request_provider.dart';
import 'package:momo/app/ui/components/button/confirm_button.dart';
import 'package:momo/app/ui/components/category/category_column.dart';
import 'package:momo/app/ui/components/input_box/content_input_box.dart';
import 'package:momo/app/ui/components/input_box/date_input_box.dart';
import 'package:momo/app/ui/components/input_box/name_input_box.dart';
import 'package:momo/app/ui/components/button/on_off_toggle_button.dart';
import 'package:momo/app/ui/group_request/widget/head_num_input_box.dart';
import 'package:momo/app/ui/group_request/widget/set_meet_city_box.dart';
import 'package:momo/app/ui/group_request/widget/set_meet_country_box.dart';
import 'package:momo/app/ui/group_request/widget/top_box.dart';

class GroupRequestPage extends ConsumerWidget {
  const GroupRequestPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final groupRequest = ref.watch(groupRequestProvider);
    final check = ref.watch(groupRequestCheckProvider);
    final dialogText = "'${groupRequest.name}' 모임이 생성되었어요!";
    final checks = ref.watch(groupRequestCategoryProvider);

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
                            .read(groupRequestStateProvider.notifier)
                            .setMeetName),
                    _subTitle('카테고리'),
                    Wrap(
                      spacing: 20,
                      runSpacing: 20,
                      children: [
                        for (int i = 0; i < checks.length; i++)
                          categoryColumn(
                            check: checks[i],
                            index: i,
                            onTabIcon: ref
                                .read(
                                    groupRequestCategoryStateProvider.notifier)
                                .checkCategory,
                            spaceHeight: 14,
                          ),
                      ],
                    ),
                    _subTitle('모임 유형'),
                    onOffToggleButton(
                        tabButton: ref
                            .read(groupRequestStateProvider.notifier)
                            .setOnOff),
                    _subTitle('인원 수'),
                    headNumInputBox(
                        onTextChanged: ref
                            .read(groupRequestStateProvider.notifier)
                            .setRecruitmentCnt),
                    _subTitle('모임 시작 날짜'),
                    DateInputBox(
                        selcetDate: ref
                            .read(groupRequestStateProvider.notifier)
                            .setStartDate),
                    _subTitle('학교'),
                    _subTitle('지역'),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        SetMeetCityBox(
                          curCity: groupRequest.city,
                          onSelect: ref
                              .read(groupRequestStateProvider.notifier)
                              .setCity,
                        ),
                        SetMeetCountryBox(
                          curCountry: groupRequest.district,
                          onSelect: ref
                              .read(groupRequestStateProvider.notifier)
                              .setDistrict,
                        ),
                      ],
                    ),
                    _subTitle('모임 설명'),
                    contentInputBox(
                      onTextChanged: ref
                          .read(groupRequestStateProvider.notifier)
                          .setIntroduction,
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
