import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/ui/components/sub_title.dart';
import 'package:momo/app/ui/home/widget/achievement_card.dart';
import 'package:momo/app/ui/home/widget/event_card.dart';
import 'package:momo/app/ui/home/widget/home_meeting_list.dart';
import 'package:momo/app/ui/home/widget/reminder_card.dart';
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
                  SvgPicture.asset(
                    'assets/icon/icon_msg_28.svg',
                  ),
                ],
              ),
              SizedBox(height: 12.h),
              Text(
                '어서와,\n이런 모임은 처음이지?',
                style: MomoTextStyle.mainTitle.copyWith(
                  height: 1.3,
                ),
              ),
              subTitle(
                title: '${DateTime.now().month}월 리마인더',
                icon: 'assets/icon/home/icon_recommend_28.svg',
              ),
              ReminderCard(),
              subTitle(
                title: '추천',
                icon: 'assets/icon/home/icon_recommend_28.svg',
                actionIcon: Icons.add,
              ),
              const HomeMeetingList(),
              subTitle(
                title: '내 학교',
                icon: 'assets/icon/home/icon_myschool_28.svg',
                actionIcon: Icons.add,
              ),
              const HomeMeetingList(),
              subTitle(
                title: '주변',
                icon: 'assets/icon/home/icon_location_28.svg',
                actionIcon: Icons.add,
              ),
              const HomeMeetingList(),
              subTitle(
                title: '주간 달성률 Top4',
                icon: 'assets/icon/home/icon_topfour_28.svg',
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
}
