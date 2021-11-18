import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:intl/intl.dart';
import 'package:momo/app/provider/new_meet/date_card_provider.dart';
import 'package:momo/app/provider/new_meet/new_meet_provider.dart';
import 'package:momo/app/util/date_format.dart';
import 'package:momo/app/util/theme.dart';

class DateCard extends ConsumerWidget {
  const DateCard({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final dateCardText = ref.watch(dateCardTextProvider);

    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      height: 44,
      width: 302.w,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20),
        color: MomoColor.unSelIcon,
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(dateCardText),
          InkWell(
            onTap: () async {
              final dateTime = await showDatePicker(
                  context: context,
                  initialDate: DateTime.now(),
                  firstDate: DateTime.now(),
                  lastDate: DateTime(DateTime.now().year + 1),
                  helpText: '',
                  cancelText: '취소',
                  confirmText: '확인',
                  initialEntryMode: DatePickerEntryMode.calendarOnly,
                  builder: (context, child) {
                    return Theme(
                      data: ThemeData(
                        primaryColor: MomoColor.main,
                      ),
                      child: child!,
                    );
                  });
              if (dateTime != null) {
                ref
                    .read(newMeetStateProvider.notifier)
                    .setStartDay(DateFormat('yyyy-MM-dd').format(dateTime));
                ref.read(dateCardTextStateProvider.state).state =
                    DateFormat('yyyy-MM-dd').format(dateTime) +
                        ' ' +
                        calDay(dateTime) +
                        '요일';
              }
            },
            child: const Icon(
              CupertinoIcons.calendar,
            ),
          ),
        ],
      ),
    );
  }
}
