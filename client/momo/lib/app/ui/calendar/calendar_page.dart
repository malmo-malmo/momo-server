import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/calendar/day_provider.dart';
import 'package:momo/app/provider/calendar/scroll_state_provider.dart';
import 'package:momo/app/provider/schedule/calendar_schedule_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/calendar/widget/time_line_card.dart';
import 'package:momo/app/ui/components/calendar_style/calendar_header_style.dart';
import 'package:momo/app/ui/components/calendar_style/momo_default_builder.dart';
import 'package:momo/app/ui/components/calendar_style/momo_dow_builder.dart';
import 'package:momo/app/ui/components/calendar_style/momo_selected_builder.dart';
import 'package:momo/app/ui/components/calendar_style/momo_today_builder.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';
import 'package:momo/app/ui/components/text/sub_title.dart';
import 'package:momo/app/util/navigation_service.dart';
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
      if (_scrollController.position.pixels < 50) {
        ref.read(scrollStateProvider.state).state = 0;
      } else if (_scrollController.position.pixels < 100) {
        ref.read(scrollStateProvider.state).state = 1;
      } else {
        ref.read(scrollStateProvider.state).state = 2;
      }
    });
    super.initState();
    Future.delayed(Duration.zero, () {
      ref.read(calendarScheduleProvider.notifier).getSchedules();
    });
  }

  @override
  Widget build(BuildContext context) {
    final calFormat = ref.watch(calendarFormatProvder);
    final calendarData = ref.watch(calendarScheduleProvider);

    return Scaffold(
      floatingActionButton: InkWell(
        onTap: () async {
          await ref.read(navigatorProvider).navigateTo(
                routeName: AppRoutes.scheduleRequest,
              );

          ref.read(calendarScheduleProvider.notifier).getSchedules();
        },
        child: SvgPicture.asset(
            'assets/icon/calendar/floatingbtn_addschedule_84.svg'),
      ),
      body: Column(
        children: [
          Container(
              padding: const EdgeInsets.only(top: 43, right: 16, left: 16),
              color: MomoColor.flutterWhite,
              child: Align(
                  alignment: Alignment.centerRight,
                  child: SvgPicture.asset('assets/icon/icon_msg_28.svg'))),
          Container(
              padding: const EdgeInsets.symmetric(horizontal: 16),
              color: MomoColor.flutterWhite,
              child: const Align(
                  alignment: Alignment.centerLeft,
                  child: Text('캘린더', style: MomoTextStyle.mainTitle))),
          Container(height: 30, color: MomoColor.flutterWhite),
          Container(
            padding: const EdgeInsets.symmetric(horizontal: 16),
            color: MomoColor.flutterWhite,
            child: TableCalendar(
              firstDay: DateTime.utc(2021, 1, 1),
              lastDay: DateTime.utc(2022, 12, 31),
              focusedDay: calendarData.selectedDate,
              calendarFormat: calFormat,
              formatAnimationDuration: const Duration(milliseconds: 500),
              locale: 'ko-KR',
              onFormatChanged: (format) {},
              rowHeight: 52,
              headerStyle: calendarHeaderStyle(),
              onDaySelected: (selDay, foDay) => ref
                  .read(calendarScheduleProvider.notifier)
                  .changeSeletedDate(selDay),
              eventLoader: (date) {
                if (calendarData.isLoading) {
                  return [];
                }
                for (int i = 0;
                    i < calendarData.scheduleDateTimes.length;
                    i++) {
                  if (calendarData.scheduleDateTimes[i].year == date.year &&
                      calendarData.scheduleDateTimes[i].month == date.month &&
                      calendarData.scheduleDateTimes[i].day == date.day) {
                    return ['event'];
                  }
                }
                return [];
              },
              selectedDayPredicate: (date) => calendarData.selectedDate == date,
              calendarBuilders: CalendarBuilders(
                markerBuilder: (context, date, events) {
                  return events.isNotEmpty
                      ? const CircleAvatar(
                          radius: 3, backgroundColor: MomoColor.main)
                      : const SizedBox();
                },
                selectedBuilder: (context, date, focusedDay) =>
                    momoSelectedBuilder(date, calendarData.selectedDate),
                todayBuilder: momoTodayBuilder,
                dowBuilder: momoDowBuilder,
                defaultBuilder: momoDefaultBuilder,
              ),
              onPageChanged: (dateTime) {
                ref
                    .read(calendarScheduleProvider.notifier)
                    .changeRequestDate(dateTime);
                ref
                    .read(calendarScheduleProvider.notifier)
                    .changeSeletedDate(dateTime);
                ref.read(calendarScheduleProvider.notifier).getSchedules();
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
            child: calendarData.isLoading
                ? const LoadingCard()
                : (calendarData.schedules.isEmpty
                    ? const NoItemCard()
                    : Padding(
                        padding: const EdgeInsets.symmetric(horizontal: 16),
                        child: CustomScrollView(
                          controller: _scrollController,
                          slivers: [
                            SliverList(
                              delegate: SliverChildListDelegate(
                                List.generate(
                                  calendarData.schedules.length,
                                  (index) => TimeLineCard(
                                    schedules: calendarData.schedules[index],
                                  ),
                                ),
                              ),
                            )
                          ],
                        ),
                      )),
          ),
        ],
      ),
    );
  }
}
