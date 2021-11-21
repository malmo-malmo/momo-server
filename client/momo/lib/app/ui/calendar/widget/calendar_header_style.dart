import 'package:flutter/material.dart';
import 'package:momo/app/util/theme.dart';
import 'package:table_calendar/table_calendar.dart';

HeaderStyle calendarHeaderStyle() {
  return HeaderStyle(
    titleCentered: true,
    formatButtonVisible: false,
    titleTextStyle: MomoTextStyle.subTitle,
    leftChevronMargin: const EdgeInsets.all(0),
    rightChevronMargin: const EdgeInsets.all(0),
    leftChevronPadding: const EdgeInsets.only(left: 56, bottom: 16, top: 16),
    rightChevronPadding: const EdgeInsets.only(right: 56, bottom: 16, top: 16),
  );
}
