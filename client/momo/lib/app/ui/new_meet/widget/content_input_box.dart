import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

Widget contentInputBox({
  required Function(String text) onTextChanged,
}) {
  return Container(
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(20),
      color: MomoColor.unSelIcon,
    ),
    padding: const EdgeInsets.symmetric(horizontal: 16),
    height: 98,
    width: 306.w,
    child: Center(
      child: TextFormField(
        maxLines: 3,
        onChanged: (text) {
          onTextChanged(text);
        },
      ),
    ),
  );
}
