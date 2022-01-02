import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/user/name_check_provider.dart';
import 'package:momo/app/provider/user/user_info__request_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/button/confirm_button.dart';
import 'package:momo/app/ui/components/dialog/confirm_dialog.dart';
import 'package:momo/app/ui/components/input_box/city_input_box.dart';
import 'package:momo/app/ui/components/input_box/district_input_box.dart';
import 'package:momo/app/ui/components/input_box/nickname_input_box.dart';
import 'package:momo/app/ui/components/input_box/university_input_box.dart';
import 'package:momo/app/ui/components/text/sub_title.dart';
import 'package:momo/app/ui/login/widget/title_text.dart';
import 'package:momo/app/util/navigation_service.dart';

class InfoPage extends ConsumerWidget {
  const InfoPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final check = ref.watch(userInfoRequestCheckProvider);
    final userNameCheck = ref.watch(nameCheckProvider);
    final userInfo = ref.watch(userInfoRequestProvider);

    return SafeArea(
      child: Scaffold(
        body: Padding(
          padding: const EdgeInsets.only(bottom: 36, right: 20, left: 20),
          child: LayoutBuilder(builder: (context, constraint) {
            return SingleChildScrollView(
              child: ConstrainedBox(
                constraints: BoxConstraints(minHeight: constraint.maxHeight),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Column(
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
                        NickNameInputBox(
                          onTabIcon: userNameCheck
                              ? () async {
                                  final check = await ref
                                      .read(userInfoRequestProvider.notifier)
                                      .validateName(userInfo.nickname);
                                  ref
                                      .read(validateNameStateProvider.state)
                                      .state = check;

                                  showDialog(
                                    context: context,
                                    builder: (context) => ConfirmDialog(
                                      dialogText: !check
                                          ? '사용 가능한 닉네임이에요'
                                          : '중복된 닉네임입니다',
                                    ),
                                  );
                                  FocusScope.of(context).unfocus();
                                }
                              : () {},
                          onTextChange: ref
                              .read(userInfoRequestProvider.notifier)
                              .setUserNickname,
                          userNicknameCheck: userNameCheck,
                        ),
                        const SubTitle(title: '학교'),
                        UniversityInputBox(
                            setUniversity: ref
                                .read(userInfoRequestProvider.notifier)
                                .setUserUniversity),
                        const SubTitle(title: '지역'),
                        Row(
                          children: [
                            CityInputBox(
                              city: userInfo.city,
                              setCity: ref
                                  .watch(userInfoRequestProvider.notifier)
                                  .setUserCity,
                            ),
                            const SizedBox(width: 24),
                            DistrictInputBox(
                              district: userInfo.district,
                              cityCode: ref
                                  .watch(userInfoRequestProvider.notifier)
                                  .userCity,
                              setDistrict: ref
                                  .watch(userInfoRequestProvider.notifier)
                                  .setUserDistrict,
                            ),
                          ],
                        ),
                        const SizedBox(height: 40),
                      ],
                    ),
                    ConfirmButton(
                      check: check,
                      buttonText: '다음',
                      onPressButton: () async {
                        await ref
                            .read(userInfoRequestProvider.notifier)
                            .updateUserInfo(userInfo);
                        ref
                            .read(navigatorProvider)
                            .navigateTo(routeName: AppRoutes.onboarding);
                      },
                    ),
                  ],
                ),
              ),
            );
          }),
        ),
      ),
    );
  }
}
