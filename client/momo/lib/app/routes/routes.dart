import 'package:flutter/material.dart';
import 'package:momo/app/ui/attendance_list/attendance_list_page.dart';
import 'package:momo/app/ui/full_img_page.dart';
import 'package:momo/app/ui/gallery/gallery_page.dart';
import 'package:momo/app/ui/login/category_page.dart';
import 'package:momo/app/ui/login/info_page.dart';
import 'package:momo/app/ui/login/login_page.dart';
import 'package:momo/app/ui/login/terms_page.dart';
import 'package:momo/app/ui/main_page.dart';
import 'package:momo/app/ui/meet_request/meet_request_page.dart';
import 'package:momo/app/ui/meeting_detail/meeting_detail_page.dart';
import 'package:momo/app/ui/meeting_list/meeting_list_page.dart';
import 'package:momo/app/ui/member_list/member_list_page.dart';
import 'package:momo/app/ui/notice_list/notice_list_page.dart';
import 'package:momo/app/ui/onboarding/onboarding_page.dart';
import 'package:momo/app/ui/post_detail/post_detail_page.dart';
import 'package:momo/app/ui/post_request/post_request_page.dart';
import 'package:momo/app/ui/request_meeting/request_meeting_page.dart';
import 'package:momo/app/ui/schedule_list/schedule_list_page.dart';
import 'package:momo/app/ui/schedule_request/schedule_request_page.dart';

class AppRoutes {
  static const main = '/main';
  static const login = '/login';
  static const trems = '/trems';
  static const category = '/category';
  static const info = '/info';
  static const onboarding = '/onboarding';
  static const meetingList = '/meetingList';
  static const meetingDetail = '/meetingDetail';
  static const newMeet = '/newMeet';
  static const gallery = '/gallery';
  static const requestMeeting = '/requestMeeting';
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
      case AppRoutes.newMeet:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const MeetRequestPage(),
        );
      case AppRoutes.gallery:
        return MaterialPageRoute<dynamic>(
          builder: (_) => GalleryPage(),
        );
      case AppRoutes.postRequest:
        final arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => PostRequestPage(
            title: arg,
          ),
        );
      case AppRoutes.scheduleRequest:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const ScheduleRequestPage(),
        );
      case AppRoutes.noticeList:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const NoticeListPage(),
        );
      case AppRoutes.memberList:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const MemberListPage(),
        );
      case AppRoutes.scheduleList:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const ScheduleListPage(),
        );
      case AppRoutes.attendanceList:
        return MaterialPageRoute<dynamic>(
          builder: (_) => const AttendanceListPage(),
        );
      case AppRoutes.postDetail:
        final arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => PostDetailPage(
            postId: arg,
          ),
        );
      case AppRoutes.requestMeeting:
        final arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => RequestMeetingPage(
            img: arg,
          ),
        );
      case AppRoutes.fullImage:
        final arg = settings.arguments;
        return MaterialPageRoute<dynamic>(
          builder: (_) => FullImagePage(
            img: arg,
          ),
        );
    }
  }
}
