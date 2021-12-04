import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/user/name_check_provider.dart';
import 'package:momo/app/provider/user/user_info_provider.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/components/dialog/confirm_dialog.dart';
import 'package:momo/app/ui/login/widget/agree_button.dart';
import 'package:momo/app/ui/login/widget/district_input_box.dart';
import 'package:momo/app/ui/login/widget/input_box.dart';
import 'package:momo/app/ui/login/widget/university_input_box.dart';
import 'package:momo/app/ui/login/widget/university_result_dialog.dart';
import 'package:momo/app/ui/login/widget/set_city_box.dart';
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
                inputBox(
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
                      ? () async {
                          final check = await ref
                              .read(userInfoStateProvider.notifier)
                              .validateName(userInfo.nickname);
                          ref.read(validateNameStateProvider.state).state =
                              check;

                          showDialog(
                            context: context,
                            builder: (context) => confirmDialog(
                              dialogText:
                                  !check ? '사용 가능한 닉네임이에요' : '중복된 닉네임입니다',
                            ),
                          );
                          FocusScope.of(context).unfocus();
                        }
                      : () {},
                ),
                _subTitle('학교'),
                universityInputBox(
                  onTabIcon: () {
                    showDialog(
                      context: context,
                      builder: (context) {
                        return universityResultDialog(
                          onSelect: ref
                              .read(userInfoStateProvider.notifier)
                              .setUserUniversity,
                        );
                      },
                    );
                    FocusScope.of(context).unfocus();
                  },
                  searchIcon: Icon(
                    CupertinoIcons.search,
                    size: 28.w,
                  ),
                ),
                _subTitle('지역'),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    cityInputBox(
                      city: ref.watch(userInfoStateProvider.notifier).userCity,
                      setCity:
                          ref.watch(userInfoStateProvider.notifier).setUserCity,
                    ),
                    // districtInputBox(
                    //   district: userInfo.district,
                    //   cityCode: userInfo.city,
                    //   setDistrict: ref
                    //       .read(userInfoStateProvider.notifier)
                    //       .setUserDistrict,
                    // ),
                    districtBox(
                      district: userInfo.district,
                      cityCode:
                          ref.watch(userInfoStateProvider.notifier).userCity,
                    ),
                  ],
                ),
                const SizedBox(height: 200),
                agreeButton(
                  check: check,
                  text: '다음',
                  onPressButton: () async {
                    await ref
                        .read(userInfoStateProvider.notifier)
                        .updateUserInfo(userInfo);
                    ref
                        .read(navigatorProvider)
                        .navigateTo(routeName: AppRoutes.onboarding);
                  },
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
