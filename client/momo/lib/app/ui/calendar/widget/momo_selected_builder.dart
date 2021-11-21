import 'package:flutter/material.dart';
import 'package:momo/app/util/theme.dart';

Widget? momoSelectedBuilder(DateTime date, DateTime selectedDay) {
  if (date == selectedDay) {
    return Center(
      child: CircleAvatar(
        foregroundColor: Colors.transparent,
        backgroundColor: MomoColor.main.withOpacity(0.5),
        radius: 14,
        child: Text(
          '${date.day}',
          style: MomoTextStyle.small.copyWith(
            color: MomoColor.white,
          ),
        ),
      ),
    );
  }
}
