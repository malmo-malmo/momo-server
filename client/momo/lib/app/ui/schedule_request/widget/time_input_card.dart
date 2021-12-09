import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/schedule_request/widget/time_picker_dialog.dart';
import 'package:momo/app/util/format/time_format.dart';
import 'package:momo/app/util/theme.dart';

class TimeInputCard extends StatefulWidget {
  const TimeInputCard({
    Key? key,
    required this.selcetTime,
  }) : super(key: key);

  final Function(int hour, int minute) selcetTime;

  @override
  State<TimeInputCard> createState() => _TimeInputCardState();
}

class _TimeInputCardState extends State<TimeInputCard> {
  String timeText = '';

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: () async {
        final result = await showDialog(
          context: context,
          builder: (context) => timePickerDialog(),
        );

        if (result != null) {
          result[0] = result[2] ? result[0] : result[0] + 12;
          widget.selcetTime(result[0], result[1]);
          timeText = changeTimeFormat(result[0], result[1]);
          setState(() {});
        }
      },
      child: Container(
        padding: const EdgeInsets.symmetric(horizontal: 16),
        height: 44,
        width: 123.w,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          color: timeText.isEmpty
              ? MomoColor.backgroundColor
              : MomoColor.unSelIcon,
        ),
        child: Center(
          child: Text(
            timeText,
            style: MomoTextStyle.defaultStyle.copyWith(
              color: MomoColor.white,
            ),
          ),
        ),
      ),
    );
  }
}
