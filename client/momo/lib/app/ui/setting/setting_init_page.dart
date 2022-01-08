import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/setting/setting_navigation_service.dart';
import 'package:momo/app/ui/setting/widget/setting_menu_card.dart';

class SettingInitPage extends StatelessWidget {
  const SettingInitPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        appBar: const CustomAppBar(
          backgroundColor: MomoColor.flutterWhite,
          leadingIcon: CupertinoIcons.back,
          isAction: false,
          title: '설정',
        ),
        body: Consumer(builder: (context, ref, _) {
          return Column(
            children: [
              SettingMenuCard(
                title: '내 정보 관리',
                onTap: () {
                  ref
                      .read(settingNavigatorProvider)
                      .navigateTo(routeName: 'settings/user_manage_page');
                },
              ),
              SettingMenuCard(
                title: '게시물 관리',
                onTap: () {
                  ref
                      .read(settingNavigatorProvider)
                      .navigateTo(routeName: 'settings/post_manage');
                },
              ),
            ],
          );
        }),
      ),
    );
  }
}
