import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:intl/intl.dart';
import 'package:momo/app/util/format/day_title_format.dart';
import 'package:momo/app/util/theme.dart';

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
}
