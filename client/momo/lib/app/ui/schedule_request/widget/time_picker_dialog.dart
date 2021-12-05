import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:intl/intl.dart';
import 'package:momo/app/provider/time_card_provider.dart';
import 'package:momo/app/util/format/time_format.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget timePickerDialog({
  required Function(String date) selcetTime,
}) {
  return Consumer(builder: (context, ref, _) {
    return Dialog(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ),
      child: Container(
        height: 221,
        width: 280,
        padding: const EdgeInsets.only(top: 24),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            const SizedBox(height: 16),
            SizedBox(
              height: 100,
              child: CupertinoDatePicker(
                minimumYear: 1900,
                maximumYear: DateTime.now().year,
                initialDateTime: DateFormat('yyyy-MM-dd').parse('2000-01-01'),
                maximumDate: DateTime.now(),
                mode: CupertinoDatePickerMode.time,
                dateOrder: DatePickerDateOrder.mdy,
                onDateTimeChanged: (value) {
                  ref.read(timeCardTextStateProvider.state).state =
                      changeTimeFormat(value.hour, value.minute);
                  selcetTime(changeTimeFormat(value.hour, value.minute));
                },
              ),
            ),
            const SizedBox(height: 16),
            InkWell(
              onTap: () {
                ref.read(navigatorProvider).pop();
              },
              child: Container(
                height: 56,
                decoration: const BoxDecoration(
                  color: MomoColor.main,
                  borderRadius: BorderRadius.only(
                    bottomRight: Radius.circular(20),
                    bottomLeft: Radius.circular(20),
                  ),
                ),
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
