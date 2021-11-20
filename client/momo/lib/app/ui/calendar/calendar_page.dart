import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:intl/intl.dart';
import 'package:momo/app/provider/bottom_index_provider.dart';
import 'package:momo/app/provider/calendar/day_provider.dart';
import 'package:momo/app/provider/calendar/scroll_state_provider.dart';
import 'package:momo/app/ui/calendar/widget/time_line_list.dart';
import 'package:momo/app/ui/components/sub_title.dart';
import 'package:momo/app/util/theme.dart';
import 'package:table_calendar/table_calendar.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

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

      if (_scrollController.position.pixels < 10) {
        ref.read(scrollStateProvider.state).state = 0;
      } else if (_scrollController.position.pixels < 50) {
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
            child: Icon(
              CupertinoIcons.chat_bubble_fill,
              size: 28.w,
              color: const Color(0xff846eaa),
            ),
          ),
          Align(
            alignment: Alignment.centerLeft,
            child: Text(
              '캘린더',
              style: TextStyle(
                fontSize: 28.sp,
              ),
            ),
          ),
          TableCalendar(
            firstDay: DateTime.utc(2021, 1, 1),
            lastDay: DateTime.utc(2022, 12, 31),
            focusedDay: focusedDay,
            calendarFormat: calFormat,
            locale: 'ko-KR',
            rowHeight: 48,
            headerStyle: HeaderStyle(
              titleCentered: true,
              formatButtonVisible: false,
              titleTextStyle: TextStyle(
                fontSize: 20.sp,
              ),
              leftChevronMargin: const EdgeInsets.all(0),
              rightChevronMargin: const EdgeInsets.all(0),
              leftChevronPadding:
                  const EdgeInsets.only(left: 56, bottom: 16, top: 16),
              rightChevronPadding:
                  const EdgeInsets.only(right: 56, bottom: 16, top: 16),
            ),
            onDaySelected: (selDay, foDay) =>
                ref.read(selectdDayProvider.state).state = selDay,
            // eventLoader: (date) {
            //   if (date.weekday == DateTime.monday) {
            //     return ['123'];
            //   }

            //   return [];
            // },
            selectedDayPredicate: (date) => selectedDay == date,
            calendarBuilders: CalendarBuilders(
              markerBuilder: (context, date, events) {},
              selectedBuilder: (context, day, focusedDay) {
                if (day == selectedDay) {
                  return Center(
                    child: CircleAvatar(
                      foregroundColor: Colors.transparent,
                      backgroundColor: Colors.lightBlueAccent.shade100,
                      radius: 13,
                      child: Text(
                        '${day.day}',
                        style: TextStyle(
                          color: MomoColor.white,
                          fontSize: 12.sp,
                        ),
                      ),
                    ),
                  );
                }
              },
              todayBuilder: (context, day, focusedDay) {
                if (day == focusedDay) {
                  return Center(
                    child: CircleAvatar(
                      foregroundColor: Colors.transparent,
                      backgroundColor: MomoColor.main,
                      radius: 13,
                      child: Text(
                        '${day.day}',
                        style: TextStyle(
                          color: MomoColor.white,
                          fontSize: 12.sp,
                        ),
                      ),
                    ),
                  );
                }
              },
              dowBuilder: (context, date) {
                var cur = DateFormat('EEEE').format(date);
                if (cur == 'Sunday') {
                  return Center(
                      child: Text(cur.substring(0, 3),
                          style: const TextStyle(color: Colors.red)));
                } else if (cur == 'Saturday') {
                  return Center(
                      child: Text(cur.substring(0, 3),
                          style: const TextStyle(color: Colors.blue)));
                }
                return Center(
                    child: Text(cur.substring(0, 3),
                        style: const TextStyle(color: Colors.black)));
              },
              defaultBuilder: (context, day, _) {
                var cur = DateFormat('EEEE').format(day);
                if (cur == 'Sunday') {
                  return Center(
                      child: Text('${day.day}',
                          style: const TextStyle(color: Colors.red)));
                } else if (cur == 'Saturday') {
                  return Center(
                      child: Text('${day.day}',
                          style: const TextStyle(color: Colors.blue)));
                }
              },
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
                SliverList(
                  delegate: SliverChildListDelegate(
                    [
                      const TimeLineList(),
                    ],
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
