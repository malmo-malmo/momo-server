import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/bottom_index_provider.dart';
import 'package:momo/app/provider/calendar/day_provider.dart';
import 'package:momo/app/provider/calendar/scroll_state_provider.dart';
import 'package:momo/app/provider/schedule/calendar_schedule_provider.dart';
import 'package:momo/app/provider/schedule/schedule_event_provider.dart';
import 'package:momo/app/ui/calendar/widget/time_line_card.dart';
import 'package:momo/app/ui/components/calendar_style/calendar_header_style.dart';
import 'package:momo/app/ui/components/calendar_style/momo_default_builder.dart';
import 'package:momo/app/ui/components/calendar_style/momo_dow_builder.dart';
import 'package:momo/app/ui/components/calendar_style/momo_selected_builder.dart';
import 'package:momo/app/ui/components/calendar_style/momo_today_builder.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';
import 'package:momo/app/ui/components/text/sub_title.dart';
import 'package:momo/app/util/theme.dart';
import 'package:table_calendar/table_calendar.dart';

class CalendarPage extends ConsumerStatefulWidget {
  const CalendarPage({Key? key}) : super(key: key);

  @override
  ConsumerState<CalendarPage> createState() => _CalendarPageState();
}

class _CalendarPageState extends ConsumerState<CalendarPage> {
  final _scrollController = ScrollController();

  @override
  void initState() {
    _scrollController.addListener(() {
      final direction = _scrollController.position.userScrollDirection;
      if (direction == ScrollDirection.forward) {
        ref.read(checkScrollStateProvider.state).state = true;
      } else {
        ref.read(checkScrollStateProvider.state).state = false;
      }

      if (_scrollController.position.pixels < 50) {
        ref.read(scrollStateProvider.state).state = 0;
      } else if (_scrollController.position.pixels < 100) {
        ref.read(scrollStateProvider.state).state = 1;
      } else {
        ref.read(scrollStateProvider.state).state = 2;
      }
    });
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    final selectedDay = ref.watch(selectdDayProvider);
    final calFormat = ref.watch(calendarFormatProvder);
    final requestDate = ref.watch(calendarTodayProvider);
    final scheduleResponse = ref.watch(calendarScheduleProvider(requestDate));
    final scheduleEvent = ref.watch(scheduleEventProvider);

    return Column(
      children: [
        Container(
            padding: const EdgeInsets.only(top: 43, right: 16, left: 16),
            color: const Color(0xffffffff),
            child: Align(
                alignment: Alignment.centerRight,
                child: SvgPicture.asset('assets/icon/icon_msg_28.svg'))),
        Container(
            padding: const EdgeInsets.symmetric(horizontal: 16),
            color: const Color(0xffffffff),
            child: const Align(
                alignment: Alignment.centerLeft,
                child: Text('캘린더', style: MomoTextStyle.mainTitle))),
        Container(height: 30, color: const Color(0xffffffff)),
        Container(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          color: const Color(0xffffffff),
          child: TableCalendar(
            firstDay: DateTime.utc(2021, 1, 1),
            lastDay: DateTime.utc(2022, 12, 31),
            focusedDay: selectedDay,
            calendarFormat: calFormat,
            formatAnimationDuration: const Duration(milliseconds: 500),
            locale: 'ko-KR',
            onFormatChanged: (format) {},
            rowHeight: 52,
            headerStyle: calendarHeaderStyle(),
            onDaySelected: (selDay, foDay) =>
                ref.read(selectdDayProvider.state).state = selDay,
            eventLoader: (date) {
              for (int i = 0; i < scheduleEvent.length; i++) {
                if (scheduleEvent[i].year == date.year &&
                    scheduleEvent[i].month == date.month &&
                    scheduleEvent[i].day == date.day) {
                  return ['event'];
                }
              }
              return [];
            },
            selectedDayPredicate: (date) => selectedDay == date,
            calendarBuilders: CalendarBuilders(
              markerBuilder: (context, date, events) {
                return events.isNotEmpty
                    ? const CircleAvatar(
                        radius: 3,
                        backgroundColor: MomoColor.main,
                      )
                    : const SizedBox();
              },
              selectedBuilder: (context, date, focusedDay) =>
                  momoSelectedBuilder(date, selectedDay),
              todayBuilder: momoTodayBuilder,
              dowBuilder: momoDowBuilder,
              defaultBuilder: momoDefaultBuilder,
            ),
            onPageChanged: (dateTime) {
              ref.read(calendarTodayStateProvider.state).state = dateTime;
              ref.read(selectdDayProvider.state).state = dateTime;
            },
          ),
        ),
        Container(height: 30, color: const Color(0xffffffff)),
        Container(
            padding: const EdgeInsets.symmetric(horizontal: 16),
            height: 96,
            width: double.infinity,
            child: const SubTitle(
                title: '타임라인',
                icon: 'assets/icon/calendar/icon_timeline_28.svg')),
        Expanded(
          child: scheduleResponse.when(
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
                  child:
                      CustomScrollView(controller: _scrollController, slivers: [
                    SliverList(
                        delegate: SliverChildListDelegate(List.generate(
                            scheduleData.schedules.length,
                            (index) => TimeLineCard(
                                schedules: scheduleData.schedules[index]))))
                  ]));
            },
          ),
        ),
      ],
    );
  }
}
