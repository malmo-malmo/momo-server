import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/schedule/calendar_schedule_provider.dart';
import 'package:momo/app/ui/calendar/widget/time_line_card.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';

class CalendarSchedules extends ConsumerStatefulWidget {
  const CalendarSchedules({Key? key, required this.requestDate})
      : super(key: key);

  final DateTime requestDate;

  @override
  ConsumerState<CalendarSchedules> createState() => _CalendarSchedulesState();
}

class _CalendarSchedulesState extends ConsumerState<CalendarSchedules> {
  @override
  Widget build(BuildContext context) {
    final scheduleResponse =
        ref.watch(calendarScheduleProvider(widget.requestDate));

    return scheduleResponse.when(
      error: (error, stacktrace) => const ErrorCard(),
      loading: () => const LoadingCard(),
      data: (scheduleData) {
        // ref
        //     .watch(scheduleEventStateProvider.notifier)
        //     .changeEvent(scheduleData.dateTimes);
        if (scheduleData.schedules.isEmpty) {
          return const NoItemCard();
        }
        return Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: CustomScrollView(
            slivers: [
              SliverList(
                delegate: SliverChildListDelegate(
                  List.generate(
                    scheduleData.schedules.length,
                    (index) => TimeLineCard(
                      schedules: scheduleData.schedules[index],
                    ),
                  ),
                ),
              ),
            ],
          ),
        );
      },
    );
  }
}
