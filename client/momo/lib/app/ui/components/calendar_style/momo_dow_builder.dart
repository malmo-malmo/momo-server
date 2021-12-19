import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:momo/app/util/theme.dart';

Widget momoDowBuilder(
  BuildContext context,
  DateTime date, {
  TextStyle? dowTextStyle,
}) {
  var cur = DateFormat('EEEE').format(date);
  if (cur == 'Sunday') {
    return Center(
        child: Text(cur.substring(0, 3),
            style: dowTextStyle ??
                MomoTextStyle.normal.copyWith(color: Colors.red)));
  } else if (cur == 'Saturday') {
    return Center(
        child: Text(cur.substring(0, 3),
            style: dowTextStyle ??
                MomoTextStyle.normal.copyWith(color: Colors.blue)));
  }
  return Center(
      child: Text(cur.substring(0, 3),
          style: dowTextStyle ?? MomoTextStyle.normal));
}
