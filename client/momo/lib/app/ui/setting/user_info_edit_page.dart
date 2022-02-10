import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/model/enum/photo_request_type.dart';
import 'package:momo/app/provider/gallery/permission_provider.dart';
import 'package:momo/app/provider/user/name_check_provider.dart';
import 'package:momo/app/provider/user/user_data_provider.dart';
import 'package:momo/app/provider/user/user_info_request_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/card/profile_avatar.dart';
import 'package:momo/app/ui/components/dialog/confirm_dialog.dart';
import 'package:momo/app/ui/components/input_box/city_input_box.dart';
import 'package:momo/app/ui/components/input_box/district_input_box.dart';
import 'package:momo/app/ui/components/input_box/nickname_input_box.dart';
import 'package:momo/app/ui/components/input_box/university_input_box.dart';
import 'package:momo/app/ui/components/text/sub_title.dart';
import 'package:momo/app/ui/setting/setting_navigation_service.dart';
import 'package:momo/app/ui/setting/widget/settings_app_bar.dart';
import 'package:momo/app/util/navigation_service.dart';

class UserInfoEditPage extends ConsumerWidget {
  const UserInfoEditPage({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final userNameCheck = ref.watch(nameCheckProvider);
    final userInfo = ref.watch(userInfoRequestProvider);
    final userData = ref.watch(userDataProvider);

    return SafeArea(
      child: Scaffold(
        backgroundColor: MomoColor.flutterWhite,
        appBar: SettingsAppBar(
          backgroundColor: MomoColor.flutterWhite,
          leadingIcon: CupertinoIcons.back,
          isAction: true,
          title: '내 정보 관리',
          actionWidget: InkWell(
            onTap: () async {
              await ref
                  .read(userDataProvider.notifier)
                  .updateUserInfo(userInfo);

              ref.read(settingNavigatorProvider).pop();
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
                    '완료',
                    style: MomoTextStyle.small.copyWith(
                      color: MomoColor.white,
                    ),
                  ),
                ),
              ),
            ),
          ),
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: LayoutBuilder(builder: (context, constraint) {
            return SingleChildScrollView(
              child: ConstrainedBox(
                constraints: BoxConstraints(minHeight: constraint.maxHeight),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const SizedBox(height: 60),
                    Align(
                      alignment: Alignment.center,
                      child: SizedBox(
                        height: 102,
                        width: 102,
                        child: Stack(
                          children: [
                            userInfo.imagePath.isEmpty
                                ? ProfileAvatar(
                                    img: userData.image ??
                                        'https://file.mk.co.kr/meet/neds/2020/08/image_readtop_2020_864116_15980534304326707.png',
                                    rad: 50,
                                    backgroundColor: MomoColor.main,
                                  )
                                : ProfileAvatarWithFile(
                                    imagePath: userInfo.imagePath,
                                  ),
                            Positioned(
                              right: 0,
                              bottom: 0,
                              child: InkWell(
                                onTap: () async {
                                  final check = await ref
                                      .read(photoPermissionProvider.future);
                                  if (check) {
                                    String? imagePath = await ref
                                        .read(navigatorProvider)
                                        .navigateTo(
                                          routeName: AppRoutes.gallery,
                                          arguments: PhotoRequestType.one,
                                        );
                                    if (imagePath != null) {
                                      ref
                                          .read(
                                              userInfoRequestProvider.notifier)
                                          .setImagePath(imagePath);
                                    }
                                  }
                                },
                                child: CircleAvatar(
                                  radius: 17,
                                  backgroundColor: MomoColor.main,
                                  child: Center(
                                    child: SvgPicture.asset(
                                      'assets/icon/mypage/btn_camera_2_18.svg',
                                    ),
                                  ),
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                    const SizedBox(height: 40),
                    const SubTitle(title: '닉네임'),
                    NickNameInputBox(
                      onTabIcon: userNameCheck
                          ? () async {
                              final check = await ref
                                  .read(userDataProvider.notifier)
                                  .validateName(userInfo.nickname);
                              ref.read(validateNameProvider.state).state =
                                  check;

                              showDialog(
                                context: context,
                                builder: (context) => ConfirmDialog(
                                  dialogText:
                                      !check ? '사용 가능한 닉네임이에요' : '중복된 닉네임입니다',
                                ),
                              );
                            }
                          : () {},
                      onTextChange: ref
                          .read(userInfoRequestProvider.notifier)
                          .setUserNickname,
                      userNicknameCheck: userNameCheck,
                      backgroundColor: MomoColor.backgroundColor,
                    ),
                    const SubTitle(title: '학교'),
                    UniversityInputBox(
                      setUniversity: ref
                          .read(userInfoRequestProvider.notifier)
                          .setUserUniversity,
                      backgroundColor: MomoColor.backgroundColor,
                    ),
                    const SubTitle(title: '지역'),
                    Row(
                      children: [
                        CityInputBox(
                          city: ref
                              .watch(userInfoRequestProvider.notifier)
                              .userCity,
                          setCity: ref
                              .watch(userInfoRequestProvider.notifier)
                              .setUserCity,
                          backgroundColor: MomoColor.backgroundColor,
                        ),
                        const SizedBox(width: 24),
                        DistrictInputBox(
                          district: userInfo.district,
                          cityCode: userInfo.city,
                          setDistrict: ref
                              .watch(userInfoRequestProvider.notifier)
                              .setUserDistrict,
                          backgroundColor: MomoColor.backgroundColor,
                        ),
                      ],
                    ),
                    const SizedBox(height: 200),
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
