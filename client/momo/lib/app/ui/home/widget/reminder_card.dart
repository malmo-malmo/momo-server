import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/schedule/reminder_schedule_check_provider.dart';
import 'package:momo/app/provider/schedule/user_schedule_provider.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';
import 'package:momo/app/ui/home/widget/schedule_page_view.dart';
import 'package:momo/app/util/format/calendar_max_day.dart';
import 'package:momo/app/util/format/day_title_format.dart';

class ReminderCard extends ConsumerWidget {
  ReminderCard({Key? key}) : super(key: key);

  final _controller = ScrollController(
    initialScrollOffset: 53.w * (DateTime.now().day - 3),
  );

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final scheduleResponses = ref.watch(homeUserScheduleProvider);
    final _dayCount = calMaxDay(DateTime.now().year, DateTime.now().month);
    final dayChecks = ref.watch(reminderScheduleCheckProvider(_dayCount));
    final today = ref.watch(todayProvider);

    return Material(
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(26)),
      elevation: 2,
      child: Container(
        padding: const EdgeInsets.symmetric(vertical: 20),
        height: 342,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(26),
          color: MomoColor.white,
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            SizedBox(
              height: 89,
              child: ListView.builder(
                controller: _controller,
                scrollDirection: Axis.horizontal,
                itemCount: _dayCount,
                itemBuilder: (_, index) {
                  return _dateCard(
                    title: dayTitle(DateTime.now().year, DateTime.now().month,
                        DateTime.now().day + index),
                    check: dayChecks[index],
                    index: index,
                    selectDay: ref
                        .read(reminderScheduleCheckStateProvider(_dayCount)
                            .notifier)
                        .check,
                    changeDay: (day) =>
                        ref.read(todayStateProvider.state).state = day,
                  );
                },
              ),
            ),
            const SizedBox(height: 20),
            Expanded(
              child: scheduleResponses.when(
                error: (error, stackTrace) => const ErrorCard(),
                loading: () => const LoadingCard(),
                data: (schedules) {
                  final curSchedule = schedules
                      .where((e) =>
                          DateTime.parse(e.first.startDateTime).day == today)
                      .toList();
                  if (curSchedule.isEmpty) {
                    return const NoItemCard();
                  }

                  return schedulePageView(schedules: curSchedule.first);
                },
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _dateCard({
    required String title,
    required int index,
    required bool check,
    required void Function(int index) selectDay,
    required void Function(int day) changeDay,
  }) {
    return InkWell(
      onTap: () {
        selectDay(index);
        changeDay(index + 1);
      },
      child: Container(
        padding: const EdgeInsets.only(top: 27, bottom: 19),
        height: 89,
        width: 53.w,
        decoration: BoxDecoration(
          color: check ? MomoColor.main : MomoColor.white,
          borderRadius: BorderRadius.circular(16),
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(
              title,
              style: MomoTextStyle.normal.copyWith(
                color: check ? MomoColor.white : MomoColor.black,
              ),
            ),
            Text('${index + 1}',
                style: MomoTextStyle.normal.copyWith(
                    color: check ? MomoColor.white : MomoColor.black)),
          ],
        ),
      ),
    );
  }
}
