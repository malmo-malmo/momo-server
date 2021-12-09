import 'package:flutter/material.dart';
import 'package:momo/app/util/theme.dart';

Widget? momoTodayBuilder(
    BuildContext context, DateTime day, DateTime focusedDay) {
  if (day == focusedDay) {
    return Center(
      child: CircleAvatar(
        foregroundColor: Colors.transparent,
        backgroundColor: MomoColor.main,
        radius: 14,
        child: Text(
          '${day.day}',
          style: MomoTextStyle.small.copyWith(
            color: MomoColor.white,
          ),
        ),
      ),
    );
  }
}
