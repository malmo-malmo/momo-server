import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/theme/theme.dart';

Widget headNumInputBox({required Function(String text) onTextChanged}) {
  return Row(
    children: [
      Container(
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          color: MomoColor.backgroundColor,
        ),
        padding: const EdgeInsets.only(left: 24),
        height: 44,
        width: 60.w,
        child: Center(
          child: SizedBox(
            height: 44,
            width: 40.w,
            child: TextField(
              onChanged: onTextChanged,
              decoration: const InputDecoration(counterText: ''),
              keyboardType: TextInputType.number,
              maxLength: 2,
              maxLines: 1,
            ),
          ),
        ),
      ),
      const SizedBox(width: 16),
      const Text('명', style: MomoTextStyle.defaultStyleR),
    ],
  );
}
