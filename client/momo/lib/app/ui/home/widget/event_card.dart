import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class EventCard extends ConsumerWidget {
  const EventCard({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Material(
      elevation: 5,
      borderRadius: BorderRadius.circular(16),
      child: InkWell(
        onTap: () {
          ref.read(navigatorProvider).navigateTo(
              routeName: AppRoutes.requestMeeting,
              arguments:
                  'http://image.kmib.co.kr/online_image/2017/1128/611311110011938257_1.jpg');
        },
        child: Container(
          padding: const EdgeInsets.symmetric(horizontal: 24),
          height: 97.h,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(16),
            color: MomoColor.white,
          ),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    '나에게 맞는 모임은?',
                    style: TextStyle(
                      fontSize: 12.sp,
                      color: MomoColor.black,
                    ),
                  ),
                  SizedBox(height: 8.h),
                  Text(
                    'MBTI별 모임 찾기',
                    style: TextStyle(
                      fontSize: 16.sp,
                      fontWeight: FontWeight.bold,
                      color: MomoColor.black,
                    ),
                  ),
                ],
              ),
              Transform.rotate(
                angle: pi,
                child: Icon(
                  CupertinoIcons.back,
                  size: 18.w,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
