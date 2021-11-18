import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/new_meet/new_meet_provider.dart';
import 'package:momo/app/ui/new_meet/widget/categort_card.dart';
import 'package:momo/app/ui/new_meet/widget/confirm_button.dart';
import 'package:momo/app/ui/new_meet/widget/content_input_box.dart';
import 'package:momo/app/ui/new_meet/widget/date_card.dart';
import 'package:momo/app/ui/new_meet/widget/head_num_input_box.dart';
import 'package:momo/app/ui/new_meet/widget/name_input_box.dart';
import 'package:momo/app/ui/new_meet/widget/on_off_toggle_button.dart';
import 'package:momo/app/ui/new_meet/widget/school_input_box.dart';
import 'package:momo/app/ui/new_meet/widget/set_meet_city_box.dart';
import 'package:momo/app/ui/new_meet/widget/set_meet_country_box.dart';
import 'package:momo/app/ui/new_meet/widget/top_box.dart';

class NewMeetingPage extends ConsumerWidget {
  const NewMeetingPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final newMeet = ref.watch(newMeetProvider);

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
                    onOffToggleButton(),
                    _subTitle('인원 수'),
                    headNumInputBox(
                        onTextChanged:
                            ref.read(newMeetStateProvider.notifier).setHeadNum),
                    _subTitle('모임 시작 날짜'),
                    const DateCard(),
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
                        onTextChanged: ref
                            .read(newMeetStateProvider.notifier)
                            .setContents),
                    const SizedBox(height: 24),
                    const ConfirmButton(),
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
