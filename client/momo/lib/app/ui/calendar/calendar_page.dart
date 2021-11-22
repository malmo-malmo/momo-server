import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/bottom_index_provider.dart';
import 'package:momo/app/provider/calendar/day_provider.dart';
import 'package:momo/app/provider/calendar/scroll_state_provider.dart';
import 'package:momo/app/ui/calendar/widget/calendar_header_style.dart';
import 'package:momo/app/ui/calendar/widget/momo_default_builder.dart';
import 'package:momo/app/ui/calendar/widget/momo_dow_builder.dart';
import 'package:momo/app/ui/calendar/widget/momo_selected_builder.dart';
import 'package:momo/app/ui/calendar/widget/momo_today_builder.dart';
import 'package:momo/app/ui/calendar/widget/time_line_list.dart';
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
    final focusedDay = ref.watch(focusedDayProvider);
    final calFormat = ref.watch(calendarFormatProvder);

    return Padding(
      padding: const EdgeInsets.only(left: 16, right: 16, top: 24),
      child: Column(
        children: [
          Align(
            alignment: Alignment.centerRight,
            child: SvgPicture.asset(
              'assets/icon/icon_msg_28.svg',
            ),
          ),
          Align(
            alignment: Alignment.centerLeft,
            child: Text(
              '캘린더',
              style: MomoTextStyle.mainTitle,
            ),
          ),
          const SizedBox(height: 30),
          TableCalendar(
            firstDay: DateTime.utc(2021, 1, 1),
            lastDay: DateTime.utc(2022, 12, 31),
            focusedDay: focusedDay,
            calendarFormat: calFormat,
            formatAnimationDuration: const Duration(milliseconds: 500),
            locale: 'ko-KR',
            onFormatChanged: (format) {},
            rowHeight: 56,
            headerStyle: calendarHeaderStyle(),
            onDaySelected: (selDay, foDay) =>
                ref.read(selectdDayProvider.state).state = selDay,
            eventLoader: (date) {
              if (date.weekday == DateTime.monday) {
                return ['1'];
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
          ),
          Expanded(
            child: CustomScrollView(
              controller: _scrollController,
              slivers: [
                SliverToBoxAdapter(
                  child: subTitle(
                    title: '타임라인',
                    icon: 'assets/icon/calendar/icon_timeline_28.svg',
                  ),
                ),
                SliverPadding(
                  padding: const EdgeInsets.only(right: 2),
                  sliver: SliverList(
                    delegate: SliverChildListDelegate(
                      [
                        const TimeLineList(),
                      ],
                    ),
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
