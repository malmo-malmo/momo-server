import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

Widget nameInputBox({
  required Function(String text) onTextChanged,
}) {
  return Material(
    elevation: 5,
    shape: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(20),
    ),
    child: Container(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      height: 44,
      width: 304.w,
      child: Center(
        child: SizedBox(
          height: 18,
          width: 270.w,
          child: Padding(
            padding: const EdgeInsets.symmetric(horizontal: 14),
            child: TextField(
              onChanged: (text) {
                onTextChanged(text);
              },
            ),
          ),
        ),
      ),
    ),
  );
}
