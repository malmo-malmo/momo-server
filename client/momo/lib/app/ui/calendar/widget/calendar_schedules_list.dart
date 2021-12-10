import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/schedule/calendar_schedule_provider.dart';
import 'package:momo/app/ui/calendar/widget/time_line_card.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';

Widget calendarSchdules({required DateTime requestDate}) {
  return Consumer(
    builder: (context, ref, _) {
      final scheduleResponse = ref.watch(calendarScheduleProvider(requestDate));

      return scheduleResponse.when(
        error: (error, stacktrace) => errorCard(),
        loading: () => loadingCard(),
        data: (scheduleData) {
          // ref
          //     .watch(scheduleEventStateProvider.notifier)
          //     .changeEvent(scheduleData.dateTimes);
          if (scheduleData.schedules.isEmpty) {
            return noItemCard();
          }
          return Padding(
              padding: const EdgeInsets.symmetric(horizontal: 16),
              child: CustomScrollView(slivers: [
                SliverList(
                    delegate: SliverChildListDelegate(List.generate(
                        scheduleData.schedules.length,
                        (index) => TimeLineCard(
                            schedules: scheduleData.schedules[index]))))
              ]));
        },
      );
    },
  );
}
