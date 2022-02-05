import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/user/user_data_provider.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/card/profile_avatar.dart';
import 'package:momo/app/ui/setting/setting_navigation_service.dart';
import 'package:momo/app/ui/setting/widget/settings_app_bar.dart';

class UserManagePage extends ConsumerWidget {
  const UserManagePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final userData = ref.watch(userDataProvider);

    return SafeArea(
      child: Scaffold(
        appBar: SettingsAppBar(
          backgroundColor: MomoColor.flutterWhite,
          leadingIcon: CupertinoIcons.back,
          isAction: true,
          title: '내 정보 관리',
          actionWidget: InkWell(
            onTap: () {
              ref
                  .read(settingNavigatorProvider)
                  .navigateTo(routeName: 'settings/user_info_edit');
            },
            child: Padding(
              padding: const EdgeInsets.symmetric(vertical: 10),
              child: Container(
                height: 36,
                width: 64,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(16),
                  color: MomoColor.main,
                ),
                child: Center(
                  child: Text(
                    '수정',
                    style: MomoTextStyle.small.copyWith(
                      color: MomoColor.white,
                    ),
                  ),
                ),
              ),
            ),
          ),
        ),
        body: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Container(
              padding: const EdgeInsets.only(top: 60, right: 16, left: 16),
              height: 370,
              width: double.infinity,
              color: MomoColor.flutterWhite,
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  ProfileAvatar(
                    img: userData.image ??
                        'https://file.mk.co.kr/meet/neds/2020/08/image_readtop_2020_864116_15980534304326707.png',
                    rad: 50,
                    backgroundColor: MomoColor.main,
                  ),
                  Column(
                    mainAxisAlignment: MainAxisAlignment.end,
                    children: [
                      SizedBox(
                        height: 54,
                        child: Padding(
                          padding: const EdgeInsets.symmetric(vertical: 17),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              const Text(
                                '닉네임',
                                style: MomoTextStyle.defaultStyle,
                              ),
                              Text(
                                userData.nickname,
                                style: MomoTextStyle.defaultStyleR,
                              ),
                            ],
                          ),
                        ),
                      ),
                      SizedBox(
                        height: 54,
                        child: Padding(
                          padding: const EdgeInsets.symmetric(vertical: 17),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              const Text(
                                '학교',
                                style: MomoTextStyle.defaultStyle,
                              ),
                              Text(
                                userData.university,
                                style: MomoTextStyle.defaultStyleR,
                              ),
                            ],
                          ),
                        ),
                      ),
                      SizedBox(
                        height: 54,
                        child: Padding(
                          padding: const EdgeInsets.symmetric(vertical: 17),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              const Text(
                                '지역',
                                style: MomoTextStyle.defaultStyle,
                              ),
                              Text(
                                userData.city.name + ' ' + userData.district,
                                style: MomoTextStyle.defaultStyleR,
                              ),
                            ],
                          ),
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),
            const Padding(
              padding: EdgeInsets.only(bottom: 40, left: 40),
              child: Text(
                '회원탈퇴',
                style: TextStyle(
                  fontSize: 15,
                  color: MomoColor.unSelIcon,
                  fontWeight: FontWeight.w400,
                  fontFamily: 'NanumSquareOTF',
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
