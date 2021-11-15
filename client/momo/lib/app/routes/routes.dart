import 'package:flutter/material.dart';
import 'package:momo/app/ui/login/login_page.dart';
import 'package:momo/app/ui/main_page.dart';
import 'package:momo/app/ui/meeting_detail/meeting_detail_page.dart';
import 'package:momo/app/ui/meeting_list/meeting_list_page.dart';

class AppRoutes {
  static const main = '/main';
  static const login = '/login';
  static const meetingList = '/meetingList';
  static const meetingDetail = '/meetingDetail';
}

class AppRouter {
  static Route<dynamic>? onGenerateRoute(
    settings,
  ) {
    switch (settings.name) {
      case AppRoutes.main:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const MainPage(),
          settings: settings,
        );
      case AppRoutes.login:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const LoginPage(),
          settings: settings,
        );
      case AppRoutes.meetingList:
        final name = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => MeetingListPage(name: name),
          settings: settings,
        );
      case AppRoutes.meetingDetail:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const MeetingDetailPage(),
        );
    }
  }
}
