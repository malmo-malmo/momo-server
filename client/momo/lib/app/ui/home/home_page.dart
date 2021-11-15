import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/home/widget/achievement_card.dart';
import 'package:momo/app/ui/home/widget/event_card.dart';
import 'package:momo/app/ui/home/widget/home_meeting_list.dart';
import 'package:momo/app/ui/home/widget/reminder_card.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: SingleChildScrollView(
        child: Padding(
          padding:
              const EdgeInsets.only(right: 16, left: 16, top: 43, bottom: 32),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  Icon(
                    CupertinoIcons.chat_bubble_fill,
                    size: 28.w,
                    color: MomoColor.main,
                  ),
                ],
              ),
              SizedBox(height: 12.h),
              Text(
                '어서와,\n이런 모임은 처음이지?',
                style: TextStyle(
                  fontSize: 28.sp,
                  fontWeight: FontWeight.bold,
                ),
              ),
              _subTitle(
                title: '리마인더',
                icon: CupertinoIcons.check_mark_circled_solid,
              ),
              const ReminderCard(),
              _subTitle(
                title: '추천',
                icon: CupertinoIcons.star_circle_fill,
              ),
              const HomeMeetingList(),
              _subTitle(
                title: '내 학교',
                icon: CupertinoIcons.flag_circle_fill,
              ),
              const HomeMeetingList(),
              _subTitle(
                title: '주변',
                icon: CupertinoIcons.location_circle_fill,
              ),
              const HomeMeetingList(),
              _subTitle(
                title: '주간 달성률 Top4',
                icon: CupertinoIcons.arrow_clockwise,
              ),
              const AchievementCard(),
              SizedBox(height: 30.h),
              const EventCard(),
            ],
          ),
        ),
      ),
    );
  }

  Widget _subTitle({required String title, required IconData icon}) {
    return Padding(
      padding: const EdgeInsets.only(top: 43, bottom: 14),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Row(
            children: [
              Icon(
                icon,
                size: 28.sp,
                color: MomoColor.main,
              ),
              SizedBox(width: 10.w),
              Text(
                title,
                style: TextStyle(
                  fontSize: 20.sp,
                ),
              ),
            ],
          ),
          Consumer(builder: (context, ref, _) {
            return InkWell(
              onTap: () {
                ref.read(navigatorProvider).navigateTo(
                      routeName: AppRoutes.meetingList,
                      arguments: title,
                    );
              },
              child: const Icon(
                Icons.add,
                size: 30,
              ),
            );
          }),
        ],
      ),
    );
  }
}
