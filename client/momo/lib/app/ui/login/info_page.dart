import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/login/name_check_provider.dart';
import 'package:momo/app/provider/login/user_info_provider.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/components/confirm_dialog.dart';
import 'package:momo/app/ui/login/widget/agree_button.dart';
import 'package:momo/app/ui/login/widget/input_box.dart';
import 'package:momo/app/ui/login/widget/school_result_dialog.dart';
import 'package:momo/app/ui/login/widget/set_city_box.dart';
import 'package:momo/app/ui/login/widget/set_country_box.dart';
import 'package:momo/app/ui/login/widget/title_text.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class InfoPage extends ConsumerWidget {
  const InfoPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final check = ref.watch(userInfoCheckProvider);
    final userNameCheck = ref.watch(nameCheckProvider);
    final userInfo = ref.watch(userInfoProvider);

    return SafeArea(
      child: Scaffold(
        backgroundColor: const Color(0xfff7f7f7),
        body: SingleChildScrollView(
          child: Padding(
            padding: const EdgeInsets.symmetric(horizontal: 20),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const SizedBox(height: 45),
                InkWell(
                  onTap: () {
                    ref.read(navigatorProvider).pop();
                  },
                  child: Icon(
                    CupertinoIcons.back,
                    color: MomoColor.black,
                    size: 24.w,
                  ),
                ),
                const SizedBox(height: 25),
                titleText('내 정보 설정  3/3'),
                const SizedBox(height: 50),
                _subTitle('닉네임'),
                nameInputBox(
                  onTextChange:
                      ref.read(userInfoStateProvider.notifier).setUserNickname,
                  searchIcon: Container(
                    height: 32,
                    width: 62,
                    decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(12),
                        color: userNameCheck
                            ? MomoColor.main
                            : MomoColor.unSelButton),
                    child: Center(
                      child: Text(
                        '중복확인',
                        style: TextStyle(
                          color: userNameCheck
                              ? MomoColor.white
                              : MomoColor.unSelText,
                        ),
                      ),
                    ),
                  ),
                  onTabIcon: userNameCheck
                      ? () {
                          showDialog(
                            context: context,
                            builder: (context) =>
                                confirmDialog(dialogText: '사용 가능한 닉네임이에요'),
                          );
                          FocusScope.of(context).unfocus();
                        }
                      : () {},
                ),
                _subTitle('학교'),
                inputBox(
                  value: userInfo.school,
                  searchIcon: Icon(
                    CupertinoIcons.search,
                    size: 28.w,
                  ),
                  onTabIcon: () {
                    showDialog(
                      context: context,
                      builder: (context) {
                        return schoolResultDialog(
                          onSelect: ref
                              .read(userInfoStateProvider.notifier)
                              .setUserSchool,
                        );
                      },
                    );
                    FocusScope.of(context).unfocus();
                  },
                ),
                _subTitle('지역'),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    cityInputBox(
                      city: userInfo.city,
                      setCity:
                          ref.read(userInfoStateProvider.notifier).setUserCity,
                    ),
                    countryInputBox(
                      country: userInfo.country,
                      setCountry: ref
                          .read(userInfoStateProvider.notifier)
                          .setUserCountry,
                    ),
                  ],
                ),
                const SizedBox(height: 200),
                agreeButton(
                  check: check,
                  nextPage: AppRoutes.onboarding,
                  text: '다음',
                ),
                const SizedBox(height: 36),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget _subTitle(String title) {
    return Padding(
      padding: EdgeInsets.only(top: 30.h, bottom: 14.h),
      child: Text(
        title,
        style: TextStyle(
          color: MomoColor.black,
          fontSize: 20.sp,
        ),
      ),
    );
  }
}
