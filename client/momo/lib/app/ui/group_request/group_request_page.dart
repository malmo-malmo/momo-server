import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/group/category_check_provider.dart';
import 'package:momo/app/provider/group/group_request_provider.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/button/confirm_action_icon.dart';
import 'package:momo/app/ui/components/category/category_column.dart';
import 'package:momo/app/ui/components/input_box/city_input_box.dart';
import 'package:momo/app/ui/components/input_box/content_input_box.dart';
import 'package:momo/app/ui/components/input_box/date_input_box.dart';
import 'package:momo/app/ui/components/input_box/district_input_box.dart';
import 'package:momo/app/ui/components/button/on_off_toggle_button.dart';
import 'package:momo/app/ui/components/text/sub_title.dart';
import 'package:momo/app/ui/group_request/widget/head_num_input_box.dart';
import 'package:momo/app/ui/group_request/widget/set_image_box.dart';
import 'package:momo/app/ui/group_request/widget/university_toggle_button.dart';
import 'package:momo/app/util/theme.dart';

class GroupRequestPage extends ConsumerWidget {
  const GroupRequestPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final groupRequest = ref.watch(groupRequestProvider);
    final check = ref.watch(groupRequestCheckProvider);
    final checks = ref.watch(groupRequestCategoryProvider);

    return SafeArea(
      child: Scaffold(
        backgroundColor: const Color(0xffffffff),
        appBar: CustomAppBar(
          leadingIcon: CupertinoIcons.xmark,
          isAction: true,
          title: '모임만들기',
          actionWidget: ConfirmActionIcon(
            check: check,
            title: '완료',
            onTapIcon: () async {
              await ref.read(groupRequestStateProvider.notifier).createGroup();
            },
            isShowDialog: true,
            dialogText: '${groupRequest.name}\n모임을 추가했어요!',
          ),
        ),
        body: SingleChildScrollView(
          child: Column(
            children: [
              const SizedBox(height: 20),
              SetImageBox(img: groupRequest.imageUrl),
              Padding(
                padding: const EdgeInsets.only(left: 26, right: 26, bottom: 24),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const SubTitle(title: '모임명'),
                    TextInputBox(
                      onTextChanged: ref
                          .read(groupRequestStateProvider.notifier)
                          .setGroupName,
                      height: 44,
                      hintText: '',
                    ),
                    const SubTitle(title: '활동 카테고리'),
                    Wrap(
                      spacing: 20,
                      runSpacing: 20,
                      children: [
                        for (int i = 0; i < checks.length; i++)
                          CategoryColumn(
                            check: checks[i],
                            index: i,
                            onTabIcon: (index) {
                              ref
                                  .read(groupRequestCategoryStateProvider
                                      .notifier)
                                  .checkCategory(index);

                              ref
                                  .read(groupRequestStateProvider.notifier)
                                  .setGroupCategory(index);
                            },
                            spaceHeight: 14,
                          ),
                      ],
                    ),
                    const SubTitle(title: '모임 유형'),
                    OnOffToggleButton(
                        tabButton: ref
                            .read(groupRequestStateProvider.notifier)
                            .setOnOff),
                    const SubTitle(title: '모임 시작일'),
                    DateInputBox(
                      selcetDate: ref
                          .read(groupRequestStateProvider.notifier)
                          .setStartDate,
                    ),
                    const SubTitle(title: '모집 인원'),
                    headNumInputBox(
                      onTextChanged: ref
                          .read(groupRequestStateProvider.notifier)
                          .setRecruitmentCnt,
                    ),
                    const SubTitle(title: '내 학교'),
                    UniversityToggleButton(
                      tabButton: ref
                          .read(groupRequestStateProvider.notifier)
                          .setUniversity,
                    ),
                    const SubTitle(title: '지역'),
                    Row(
                      children: [
                        CityInputBox(
                          city: groupRequest.city,
                          setCity: ref
                              .watch(groupRequestStateProvider.notifier)
                              .setCity,
                          backgroundColor: MomoColor.backgroundColor,
                        ),
                        const SizedBox(width: 24),
                        DistrictInputBox(
                          district: groupRequest.district,
                          cityCode: ref
                              .watch(groupRequestStateProvider.notifier)
                              .userCity,
                          setDistrict: ref
                              .watch(groupRequestStateProvider.notifier)
                              .setDistrict,
                          backgroundColor: MomoColor.backgroundColor,
                        ),
                      ],
                    ),
                    const SubTitle(title: '메모'),
                    TextInputBox(
                      onTextChanged: ref
                          .read(groupRequestStateProvider.notifier)
                          .setIntroduction,
                      height: 82,
                      maxLines: 6,
                      hintText: '모임에 대한 간단한 메모를 남겨주세요',
                    ),
                    const SizedBox(height: 24),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
