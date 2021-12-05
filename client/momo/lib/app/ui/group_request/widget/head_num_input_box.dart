import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

Widget headNumInputBox({
  required Function(String text) onTextChanged,
}) {
  return Row(
    children: [
      Container(
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          color: MomoColor.backgroundColor,
        ),
        padding: const EdgeInsets.symmetric(horizontal: 16),
        height: 44,
        width: 60.w,
        child: Center(
          child: SizedBox(
            height: 18,
            width: 40.w,
            child: TextField(
              onChanged: (text) {
                onTextChanged(text);
              },
            ),
          ),
        ),
      ),
      const SizedBox(width: 16),
      Text(
        '명',
        style: TextStyle(fontSize: 16.sp),
      ),
    ],
  );
}
