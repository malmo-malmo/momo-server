import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/group/home_group_paging_controller.dart';
import 'package:momo/app/ui/components/button/message_button.dart';
import 'package:momo/app/ui/components/text/sub_title.dart';
import 'package:momo/app/ui/home/widget/top_rate_card.dart';
import 'package:momo/app/ui/home/widget/event_card.dart';
import 'package:momo/app/ui/home/widget/home_group_list.dart';
import 'package:momo/app/ui/home/widget/reminder_card.dart';
import 'package:momo/app/util/theme.dart';

class HomePage extends ConsumerWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final categoryPagingController = ref.watch(categoryController);
    final districtPagingController = ref.watch(districtController);
    final universityPagingController = ref.watch(universityController);

    return SafeArea(
      child: SingleChildScrollView(
        child: Padding(
          padding:
              const EdgeInsets.only(right: 16, left: 16, top: 43, bottom: 32),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const MessageButton(),
              SizedBox(height: 12.h),
              Text(
                '어서와,\n이런 모임은 처음이지?',
                style: MomoTextStyle.mainTitle.copyWith(
                  height: 1.3,
                ),
              ),
              SubTitle(
                title: '${DateTime.now().month}월 리마인더',
                icon: 'assets/icon/home/icon_recommend_28.svg',
              ),
              ReminderCard(),
              const SubTitle(
                title: '추천',
                icon: 'assets/icon/home/icon_recommend_28.svg',
                actionIcon: Icons.add,
              ),
              HomeMeetingList(pagingController: categoryPagingController),
              SubTitle(
                title: '내 학교',
                icon: 'assets/icon/home/icon_myschool_28.svg',
                actionIcon: Icons.add,
                pagingController: universityPagingController,
              ),
              HomeMeetingList(pagingController: universityPagingController),
              SubTitle(
                title: '주변',
                icon: 'assets/icon/home/icon_location_28.svg',
                actionIcon: Icons.add,
                pagingController: districtPagingController,
              ),
              HomeMeetingList(pagingController: districtPagingController),
              const SubTitle(
                title: '주간 달성률 Top4',
                icon: 'assets/icon/home/icon_topfour_28.svg',
              ),
              const TopRateCard(),
              SizedBox(height: 30.h),
              const EventCard(),
            ],
          ),
        ),
      ),
    );
  }
}
