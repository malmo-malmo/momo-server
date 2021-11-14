import 'package:flutter/material.dart';
import 'package:momo/app/ui/login/login_page.dart';
import 'package:momo/app/ui/main_page.dart';

class AppRoutes {
  static const main = '/main';
  static const login = '/login';
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
    }
  }
}
