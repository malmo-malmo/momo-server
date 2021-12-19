import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:intl/intl.dart';
import 'package:momo/app/provider/date_time_provider.dart';
import 'package:momo/app/ui/components/calendar_style/calendar_header_style.dart';
import 'package:momo/app/ui/components/calendar_style/momo_default_builder.dart';
import 'package:momo/app/ui/components/calendar_style/momo_dow_builder.dart';
import 'package:momo/app/ui/components/calendar_style/momo_selected_builder.dart';
import 'package:momo/app/ui/components/calendar_style/momo_today_builder.dart';
import 'package:momo/app/util/format/day_title_format.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';
import 'package:table_calendar/table_calendar.dart';

class DateInputBox extends StatefulWidget {
  const DateInputBox({
    Key? key,
    required this.selcetDate,
  }) : super(key: key);

  final Function(DateTime dateTime) selcetDate;

  @override
  State<DateInputBox> createState() => _DateInputBoxState();
}

class _DateInputBoxState extends State<DateInputBox> {
  String dateTimeText = '';

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: () async {
        final dateTime = await showDialog(
          context: context,
          builder: (context) => _calendarDialog(),
        );

        if (dateTime != null) {
          widget.selcetDate(dateTime);
          dateTimeText = DateFormat('yyyy-MM-dd').format(dateTime) +
              ' ' +
              dayTitleToKR(dateTime) +
              '요일';
          setState(() {});
        }
      },
      child: Container(
        padding: const EdgeInsets.symmetric(horizontal: 16),
        height: 44,
        width: 190.w,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          color: dateTimeText.isEmpty
              ? MomoColor.backgroundColor
              : MomoColor.unSelIcon,
        ),
        child: Center(
          child: Text(
            dateTimeText,
            style: MomoTextStyle.defaultStyle.copyWith(
              color: MomoColor.white,
            ),
          ),
        ),
      ),
    );
  }

  Widget _calendarDialog() {
    return Consumer(builder: (context, ref, _) {
      final _selectDay = ref.watch(selectDayProvider);

      return Dialog(
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(20),
        ),
        child: SizedBox(
          height: 363,
          width: 280,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Padding(
                padding: const EdgeInsets.only(top: 8, right: 28, left: 28),
                child: TableCalendar(
                  firstDay: DateTime.utc(2021, 1, 1),
                  lastDay: DateTime.utc(2022, 12, 31),
                  focusedDay: _selectDay,
                  formatAnimationDuration: const Duration(milliseconds: 500),
                  locale: 'ko-KR',
                  rowHeight: 32,
                  headerStyle: calendarHeaderStyle(
                    iconPadding: 24,
                    titleTextStyle: MomoTextStyle.defaultStyle,
                  ),
                  onDaySelected: (selDay, foDay) =>
                      ref.read(selectdDayStateProvider.state).state = selDay,
                  selectedDayPredicate: (date) => _selectDay == date,
                  calendarBuilders: CalendarBuilders(
                    selectedBuilder: (context, date, focusedDay) =>
                        momoSelectedBuilder(date, _selectDay),
                    todayBuilder: momoTodayBuilder,
                    dowBuilder: momoDowBuilder,
                    defaultBuilder: momoDefaultBuilder,
                  ),
                ),
              ),
              InkWell(
                onTap: () =>
                    ref.read(navigatorProvider).pop(result: _selectDay),
                child: Container(
                  decoration: const BoxDecoration(
                    borderRadius: BorderRadius.only(
                      bottomLeft: Radius.circular(20),
                      bottomRight: Radius.circular(20),
                    ),
                    color: MomoColor.main,
                  ),
                  height: 56,
                  width: double.infinity,
                  child: Center(
                    child: Text(
                      '확인',
                      style: MomoTextStyle.defaultStyle.copyWith(
                        color: MomoColor.white,
                      ),
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      );
    });
  }
}
