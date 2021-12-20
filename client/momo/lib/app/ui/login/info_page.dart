import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/user/name_check_provider.dart';
import 'package:momo/app/provider/user/user_info__request_provider.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/components/dialog/confirm_dialog.dart';
import 'package:momo/app/ui/components/input_box/city_input_box.dart';
import 'package:momo/app/ui/components/input_box/university_input_box.dart';
import 'package:momo/app/ui/components/text/sub_title.dart';
import 'package:momo/app/ui/login/widget/agree_button.dart';
import 'package:momo/app/ui/components/input_box/district_input_box.dart';
import 'package:momo/app/ui/login/widget/input_box.dart';
import 'package:momo/app/ui/login/widget/title_text.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class InfoPage extends ConsumerWidget {
  const InfoPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final check = ref.watch(userInfoRequestCheckProvider);
    final userNameCheck = ref.watch(nameCheckProvider);
    final userInfo = ref.watch(userInfoRequestProvider);

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
                  onTap: () => ref.read(navigatorProvider).pop(),
                  child: Icon(
                    CupertinoIcons.back,
                    color: MomoColor.black,
                    size: 24.w,
                  ),
                ),
                const SizedBox(height: 25),
                titleText('내 정보 설정  3/3'),
                const SizedBox(height: 50),
                const SubTitle(title: '닉네임'),
                inputBox(
                  onTextChange: ref
                      .read(userInfoRequestStateProvider.notifier)
                      .setUserNickname,
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
                        style: MomoTextStyle.card.copyWith(
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
                              .read(userInfoRequestStateProvider.notifier)
                              .validateName(userInfo.nickname);
                          ref.read(validateNameStateProvider.state).state =
                              check;

                          showDialog(
                            context: context,
                            builder: (context) => ConfirmDialog(
                              dialogText:
                                  !check ? '사용 가능한 닉네임이에요' : '중복된 닉네임입니다',
                            ),
                          );
                          FocusScope.of(context).unfocus();
                        }
                      : () {},
                ),
                const SubTitle(title: '학교'),
                universityInputBox(
                    setUniversity: ref
                        .watch(userInfoRequestStateProvider.notifier)
                        .setUserUniversity),
                const SubTitle(title: '지역'),
                Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: [
                    CityInputBox(
                      city: ref
                          .watch(userInfoRequestStateProvider.notifier)
                          .userCity,
                      setCity: ref
                          .watch(userInfoRequestStateProvider.notifier)
                          .setUserCity,
                    ),
                    const SizedBox(width: 48),
                    DistrictInputBox(
                      district: userInfo.district,
                      cityCode: userInfo.city,
                      setDistrict: ref
                          .watch(userInfoRequestStateProvider.notifier)
                          .setUserDistrict,
                    ),
                  ],
                ),
                const SizedBox(height: 200),
                agreeButton(
                  check: check,
                  text: '다음',
                  onPressButton: () async {
                    await ref
                        .read(userInfoRequestStateProvider.notifier)
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
}
