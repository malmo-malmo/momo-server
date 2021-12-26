import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:momo/app/theme/theme.dart';

Widget momoDefaultBuilder(
  BuildContext context,
  DateTime day,
  DateTime focusedDay, {
  TextStyle? defaultTextStyle,
}) {
  var cur = DateFormat('EEEE').format(day);
  if (cur == 'Sunday') {
    return Center(
      child: Text(
        '${day.day}',
        style: defaultTextStyle ??
            MomoTextStyle.normal.copyWith(
              color: Colors.red,
            ),
      ),
    );
  } else if (cur == 'Saturday') {
    return Center(
      child: Text(
        '${day.day}',
        style: defaultTextStyle ??
            MomoTextStyle.normal.copyWith(
              color: Colors.blue,
            ),
      ),
    );
  } else {
    return Center(
      child: Text(
        '${day.day}',
        style: defaultTextStyle ?? MomoTextStyle.normal,
      ),
    );
  }
}
