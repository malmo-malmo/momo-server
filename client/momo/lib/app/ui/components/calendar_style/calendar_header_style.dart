import 'package:flutter/material.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:table_calendar/table_calendar.dart';

HeaderStyle calendarHeaderStyle({
  TextStyle? titleTextStyle,
  double? iconPadding,
}) {
  return HeaderStyle(
    titleCentered: true,
    formatButtonVisible: false,
    titleTextStyle: titleTextStyle ?? MomoTextStyle.subTitle,
    leftChevronMargin: const EdgeInsets.all(0),
    rightChevronMargin: const EdgeInsets.all(0),
    leftChevronPadding:
        EdgeInsets.only(left: iconPadding ?? 56, bottom: 16, top: 16),
    rightChevronPadding:
        EdgeInsets.only(right: iconPadding ?? 56, bottom: 16, top: 16),
  );
}
