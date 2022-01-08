import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/ui/setting/post_manage_page.dart';
import 'package:momo/app/ui/setting/setting_init_page.dart';
import 'package:momo/app/ui/setting/setting_navigation_service.dart';
import 'package:momo/app/ui/setting/user_info_edit_page.dart';
import 'package:momo/app/ui/setting/user_manage_page.dart';

class SettingNavigator extends ConsumerWidget {
  const SettingNavigator({Key? key}) : super(key: key);

  Route _onGenerateRoute(RouteSettings settings) {
    switch (settings.name) {
      case 'settings/init':
        return MaterialPageRoute(
          builder: (context) {
            return const SettingInitPage();
          },
          settings: settings,
        );
      case 'settings/user_manage_page':
        return MaterialPageRoute(
          builder: (context) {
            return const UserManagePage();
          },
          settings: settings,
        );
      case 'settings/user_info_edit':
        return MaterialPageRoute(
          builder: (context) {
            return const UserInfoEditPage();
          },
          settings: settings,
        );
      case 'settings/post_manage':
        return MaterialPageRoute(
          builder: (context) {
            return const PostManagePage();
          },
          settings: settings,
        );
      default:
        throw Exception('Invalid route: ${settings.name}');
    }
  }

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final _navigator = ref.watch(settingNavigatorProvider);
    return Navigator(
      key: _navigator.navigatorKey,
      initialRoute: 'settings/init',
      onGenerateRoute: _onGenerateRoute,
    );
  }
}
