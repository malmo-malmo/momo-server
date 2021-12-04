import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/schedule/user_schedule_provider.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/home/widget/schedule_page_view.dart';
import 'package:momo/app/util/date_format.dart';
import 'package:momo/app/util/theme.dart';

class ReminderCard extends ConsumerWidget {
  ReminderCard({Key? key}) : super(key: key);

  final _controller = ScrollController(
    initialScrollOffset: 53.w * (DateTime.now().day - 3),
  );

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final scheduleResponses = ref.watch(homeUserScheduleProvider);

    return Material(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(26),
      ),
      elevation: 5,
      child: Container(
        padding: const EdgeInsets.only(top: 20),
        height: 360,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(26),
          color: MomoColor.white,
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            SizedBox(
              height: 89.h,
              child: ListView.builder(
                controller: _controller,
                scrollDirection: Axis.horizontal,
                itemCount:
                    calendarDay(DateTime.now().year, DateTime.now().month),
                itemBuilder: (_, index) {
                  final title = dayTitle(
                    DateTime.now().year,
                    DateTime.now().month,
                    DateTime.now().day + index,
                  );
                  return _dateCard(
                    title: title,
                    day: index + 1,
                    index: index,
                  );
                },
              ),
            ),
            scheduleResponses.when(
              error: (error, stackTrace) => errorCard(),
              loading: () => loadingCard(),
              data: (schedules) {
                final curSchedule = schedules
                    .where((e) =>
                        DateTime.parse(e.first.startDateTime).day ==
                        DateTime.now().day)
                    .toList();

                return Expanded(
                  child: schedulePageView(schedules: curSchedule.first),
                );
              },
            ),
          ],
        ),
      ),
    );
  }

  Widget _dateCard({
    required String title,
    required int day,
    required int index,
  }) {
    return Consumer(builder: (context, ref, _) {
      final check = DateTime.now().day == day;
      return Container(
        height: 89.h,
        width: 53.w,
        decoration: BoxDecoration(
          color: check ? MomoColor.main : MomoColor.white,
          borderRadius: BorderRadius.circular(16),
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            Text(
              title,
              style: MomoTextStyle.normal.copyWith(
                color: check ? MomoColor.white : MomoColor.black,
              ),
            ),
            Text('$day',
                style: MomoTextStyle.normal.copyWith(
                    color: check ? MomoColor.white : MomoColor.black)),
          ],
        ),
      );
    });
  }
}
