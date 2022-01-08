import 'package:flutter/material.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/routes/custom_arg/group_list_arg.dart';
import 'package:momo/app/routes/custom_arg/post_request_arg.dart';
import 'package:momo/app/ui/attendance_list/attendance_list_page.dart';
import 'package:momo/app/ui/chat_list/chat_list_page.dart';
import 'package:momo/app/ui/chat_room/chat_room_page.dart';
import 'package:momo/app/ui/full_img_page.dart';
import 'package:momo/app/ui/gallery/gallery_page.dart';
import 'package:momo/app/ui/group_detail/group_detail_page.dart';
import 'package:momo/app/ui/group_list/group_list_page.dart';
import 'package:momo/app/ui/group_list/recommend_group_list_page.dart';
import 'package:momo/app/ui/group_request/group_request_page.dart';
import 'package:momo/app/ui/login/category_page.dart';
import 'package:momo/app/ui/login/info_page.dart';
import 'package:momo/app/ui/login/login_page.dart';
import 'package:momo/app/ui/login/terms_page.dart';
import 'package:momo/app/ui/main_page.dart';
import 'package:momo/app/ui/member_list/member_list_page.dart';
import 'package:momo/app/ui/mypage/category_edit/category_edit_page.dart';
import 'package:momo/app/ui/mypage/wish_group/wish_group_page.dart';
import 'package:momo/app/ui/notice_list/notice_list_page.dart';
import 'package:momo/app/ui/onboarding/onboarding_page.dart';
import 'package:momo/app/ui/post_detail/post_detail_page.dart';
import 'package:momo/app/ui/post_request/post_request_page.dart';
import 'package:momo/app/ui/schedule_list/schedule_list_page.dart';
import 'package:momo/app/ui/schedule_request/schedule_request_page.dart';
import 'package:momo/splash_page.dart';

part './app_routes.dart';

class AppRouter {
  static Route<dynamic>? onGenerateRoute(settings) {
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
      case AppRoutes.recommendList:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const RecommendGroupListPage(),
          settings: settings,
        );

      case AppRoutes.groupDetail:
        final GroupInfo arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => GroupDetailPage(group: arg),
        );

      case AppRoutes.groupRequest:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const GroupRequestPage(),
        );

      case AppRoutes.gallery:
        final arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => GalleryPage(requestType: arg),
        );

      case AppRoutes.postRequest:
        final PostRequestArg arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
            builder: (_) => PostRequestPage(postRequestArg: arg));

      case AppRoutes.scheduleRequest:
        final arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => ScheduleRequestPage(groupId: arg),
        );

      case AppRoutes.noticeList:
        final arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => NoticeListPage(pagingController: arg),
        );

      case AppRoutes.memberList:
        final arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => MemberListPage(groupId: arg),
        );

      case AppRoutes.scheduleList:
        final arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => ScheduleListPage(groupId: arg),
        );

      case AppRoutes.attendanceList:
        final arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => AttendanceListPage(groupId: arg),
        );

      case AppRoutes.postDetail:
        final Post arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => PostDetailPage(post: arg),
        );

      case AppRoutes.fullImage:
        final arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => FullImagePage(img: arg),
        );

      case AppRoutes.chatList:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const ChatListPage(),
        );

      case AppRoutes.chatRoom:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const ChatRoomPage(),
        );

      case AppRoutes.categoryEdit:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const CategoryEditPage(),
        );

      case AppRoutes.wishGroup:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const WishGroupPage(),
        );
    }
  }
}
