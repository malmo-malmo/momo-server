import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class ScheduleCard extends StatelessWidget {
  const ScheduleCard({
    Key? key,
    required this.groupId,
  }) : super(key: key);

  final int groupId;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 16),
      height: 148,
      color: const Color(0xffffffff),
      width: double.infinity,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              const Text(
                '다가오는 일정',
                style: MomoTextStyle.subTitle,
              ),
              Consumer(builder: (context, ref, _) {
                return InkWell(
                  onTap: () {
                    ref.read(navigatorProvider).navigateTo(
                        routeName: AppRoutes.scheduleList, arguments: groupId);
                  },
                  child: Transform.rotate(
                    angle: pi,
                    child: Icon(
                      CupertinoIcons.back,
                      color: MomoColor.black,
                      size: 24.w,
                    ),
                  ),
                );
              }),
            ],
          ),
          const SizedBox(height: 16),
          Container(height: 1, color: MomoColor.divider),
          const SizedBox(height: 20),
          const Text(
            '우리 꼭 같이 달려요',
            style: MomoTextStyle.defaultStyle,
          ),
          const SizedBox(height: 8),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: const [
              Text(
                '11/5(금)',
                style: MomoTextStyle.small,
              ),
              Text(
                '오후 6:00 ~ 9:00',
                style: MomoTextStyle.small,
              ),
            ],
          ),
        ],
      ),
    );
  }
}
