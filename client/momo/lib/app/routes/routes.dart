import 'package:flutter/material.dart';
import 'package:momo/app/routes/custom_arg/group_list_arg.dart';
import 'package:momo/app/routes/custom_arg/post_detail_arg.dart';
import 'package:momo/app/ui/attendance_list/attendance_list_page.dart';
import 'package:momo/app/ui/full_img_page.dart';
import 'package:momo/app/ui/gallery/gallery_page.dart';
import 'package:momo/app/ui/group_detail/group_detail_page.dart';
import 'package:momo/app/ui/group_list/group_list_page.dart';
import 'package:momo/app/ui/group_request/group_request_page.dart';
import 'package:momo/app/ui/login/category_page.dart';
import 'package:momo/app/ui/login/info_page.dart';
import 'package:momo/app/ui/login/login_page.dart';
import 'package:momo/app/ui/login/terms_page.dart';
import 'package:momo/app/ui/main_page.dart';
import 'package:momo/app/ui/member_list/member_list_page.dart';
import 'package:momo/app/ui/notice_list/notice_list_page.dart';
import 'package:momo/app/ui/onboarding/onboarding_page.dart';
import 'package:momo/app/ui/post_detail/post_detail_page.dart';
import 'package:momo/app/ui/post_request/post_request_page.dart';
import 'package:momo/app/ui/schedule_list/schedule_list_page.dart';
import 'package:momo/app/ui/schedule_request/schedule_request_page.dart';
import 'package:momo/splash_page.dart';

class AppRoutes {
  static const main = '/main';
  static const splash = '/splash';
  static const login = '/login';
  static const trems = '/trems';
  static const category = '/category';
  static const info = '/info';
  static const onboarding = '/onboarding';
  static const groupList = '/groupList';
  static const groupDetail = '/groupDetail';
  static const newMeet = '/newMeet';
  static const gallery = '/gallery';
  static const postRequest = '/postRequest';
  static const scheduleRequest = '/scheduleRequest';
  static const scheduleList = '/scheduleList';
  static const noticeList = '/noticeList';
  static const postDetail = '/postDetail';
  static const memberList = '/memberList';
  static const fullImage = '/fullImage';
  static const attendanceList = '/attendanceList';
}

class AppRouter {
  static Route<dynamic>? onGenerateRoute(
    settings,
  ) {
    switch (settings.name) {
      case AppRoutes.main:
        return MaterialPageRoute<dynamic>(
            builder: (_) => const MainPage(), settings: settings);

      case AppRoutes.splash:
        return MaterialPageRoute<dynamic>(
            builder: (_) => const SplashPage(), settings: settings);

      case AppRoutes.login:
        return MaterialPageRoute<dynamic>(
            builder: (_) => const LoginPage(), settings: settings);

      case AppRoutes.trems:
        return MaterialPageRoute<dynamic>(
            builder: (_) => const TermsPage(), settings: settings);

      case AppRoutes.category:
        return MaterialPageRoute<dynamic>(
            builder: (_) => const CategoryPage(), settings: settings);

      case AppRoutes.info:
        return MaterialPageRoute<dynamic>(
            builder: (_) => const InfoPage(), settings: settings);

      case AppRoutes.onboarding:
        return MaterialPageRoute<dynamic>(
            builder: (_) => const OnboardingPage(), settings: settings);

      case AppRoutes.groupList:
        GroupListArg arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => GroupListPage(
            name: arg.name,
            pagingController: arg.pagingController,
          ),
          settings: settings,
        );

      case AppRoutes.groupDetail:
        final arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => GroupDetailPage(groupId: arg),
        );

      case AppRoutes.newMeet:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const GroupRequestPage(),
        );

      case AppRoutes.gallery:
        return MaterialPageRoute<dynamic>(
          builder: (_) => GalleryPage(),
        );

      case AppRoutes.postRequest:
        final arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => PostRequestPage(title: arg),
        );

      case AppRoutes.scheduleRequest:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const ScheduleRequestPage(),
        );

      case AppRoutes.noticeList:
        final arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => NoticeListPage(pagingController: arg),
        );

      case AppRoutes.memberList:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const MemberListPage(),
        );

      case AppRoutes.scheduleList:
        final arg = settings.argument;
        return MaterialPageRoute<dynamic>(
          builder: (_) => ScheduleListPage(groupId: arg),
        );

      case AppRoutes.attendanceList:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const AttendanceListPage(),
        );

      case AppRoutes.postDetail:
        final PostDetailArg arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => PostDetailPage(
            postId: arg.postId,
            commentCnt: arg.commentCnt,
          ),
        );

      case AppRoutes.fullImage:
        final arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => FullImagePage(img: arg),
        );
    }
  }
}
