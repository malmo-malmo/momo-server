import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/setting/setting_navigation_service.dart';
import 'package:momo/app/ui/setting/widget/settings_app_bar.dart';

class UserManagePage extends StatelessWidget {
  const UserManagePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
          appBar: SettingsAppBar(
            backgroundColor: MomoColor.flutterWhite,
            leadingIcon: CupertinoIcons.back,
            isAction: true,
            title: '내 정보 관리',
            actionWidget: Consumer(
              builder: (context, ref, _) {
                return InkWell(
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
                );
              },
            ),
          ),
          body: Container(
            padding: const EdgeInsets.only(top: 60),
            height: 360,
            color: MomoColor.flutterWhite,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [],
            ),
          )),
    );
  }
}
