import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/meeting_detail/widget/withdraw_dialog.dart';
import 'package:momo/app/util/navigation_service.dart';

Widget meetingDetailBottomSheetAdmin() {
  return SizedBox(
    height: 400,
    child: Column(
      mainAxisAlignment: MainAxisAlignment.spaceAround,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        Container(
          height: 10,
          width: 80,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(16),
            color: const Color(0xffd1d1d1),
          ),
        ),
        Text(
          '게시물 작성',
          style: TextStyle(
            fontSize: 18.sp,
          ),
        ),
        Text(
          '공지사항 작성',
          style: TextStyle(
            fontSize: 18.sp,
          ),
        ),
        Text(
          '일정 추가',
          style: TextStyle(
            fontSize: 18.sp,
          ),
        ),
        Text(
          '권한 넘기기',
          style: TextStyle(
            fontSize: 18.sp,
          ),
        ),
        Text(
          '모임 종료',
          style: TextStyle(
            fontSize: 18.sp,
          ),
        ),
      ],
    ),
  );
}

Widget meetingDetailBottomSheetUser() {
  return Consumer(builder: (context, ref, _) {
    return SizedBox(
      height: 200,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Container(
            height: 10,
            width: 80,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(16),
              color: const Color(0xffd1d1d1),
            ),
          ),
          InkWell(
            onTap: () {
              ref.read(navigatorProvider).navigateTo(
                    routeName: AppRoutes.post,
                  );
            },
            child: Text(
              '게시물 작성',
              style: TextStyle(
                fontSize: 18.sp,
              ),
            ),
          ),
          InkWell(
            onTap: () {
              showDialog(
                context: context,
                builder: (context) {
                  return withdrawDialog();
                },
              );
            },
            child: Text(
              '모임 탈퇴',
              style: TextStyle(
                fontSize: 18.sp,
              ),
            ),
          ),
        ],
      ),
    );
  });
}
