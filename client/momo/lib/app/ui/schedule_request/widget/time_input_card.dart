import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/time_card_provider.dart';
import 'package:momo/app/ui/schedule_request/widget/time_picker_dialog.dart';
import 'package:momo/app/util/theme.dart';

class TimeInputCard extends ConsumerWidget {
  const TimeInputCard({
    Key? key,
    required this.selcetTime,
  }) : super(key: key);

  final Function(String date) selcetTime;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final timeCardText = ref.watch(timeCardTextProvider);

    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      height: 44,
      width: 114.w,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20),
        color: MomoColor.unSelIcon,
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(timeCardText),
          InkWell(
            onTap: () async {
              await showDialog(
                context: context,
                builder: (context) {
                  return timePickerDialog();
                },
              );
              selcetTime(timeCardText);
            },
            child: const Icon(
              CupertinoIcons.clock,
            ),
          ),
        ],
      ),
    );
  }
}
