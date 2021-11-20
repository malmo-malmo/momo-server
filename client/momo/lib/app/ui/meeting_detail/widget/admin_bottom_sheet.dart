import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/meeting_detail/widget/user_bottom_sheet.dart';
import 'package:momo/app/util/navigation_service.dart';

Widget meetingDetailBottomSheetAdmin() {
  return Consumer(builder: (context, ref, _) {
    return Container(
      padding: const EdgeInsets.only(top: 18, left: 16, right: 16),
      height: 310,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Container(
            height: 4,
            width: 52,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(16),
              color: const Color(0xffd1d1d1),
            ),
          ),
          const SizedBox(height: 18),
          InkWell(
            onTap: () async {
              await ref.read(navigatorProvider).navigateTo(
                    routeName: AppRoutes.postRequest,
                    arguments: '게시물',
                  );
              ref.read(navigatorProvider).pop();
            },
            child: sheetTabButtob(
              title: '게시물 작성',
              icon: 'assets/icon/icon_write_28.svg',
            ),
          ),
          InkWell(
            onTap: () async {
              await ref.read(navigatorProvider).navigateTo(
                    routeName: AppRoutes.postRequest,
                    arguments: '공지사항',
                  );
              ref.read(navigatorProvider).pop();
            },
            child: sheetTabButtob(
              title: '공지사항 작성',
              icon: 'assets/icon/icon_notice_28.svg',
            ),
          ),
          InkWell(
            onTap: () async {
              await ref
                  .read(navigatorProvider)
                  .navigateTo(routeName: AppRoutes.scheduleRequest);
              ref.read(navigatorProvider).pop();
            },
            child: sheetTabButtob(
              title: '일정 추가',
              icon: 'assets/icon/icon_todoadd_28.svg',
            ),
          ),
          sheetTabButtob(
            title: '관리자 권한 넘기기',
            icon: 'assets/icon/icon_manager_28.svg',
          ),
          sheetTabButtob(
            title: '모임 종료',
            icon: 'assets/icon/icon_moimclose.svg',
          ),
        ],
      ),
    );
  });
}
