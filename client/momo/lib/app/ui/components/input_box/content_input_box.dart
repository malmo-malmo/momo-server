import 'package:flutter/material.dart';
import 'package:momo/app/util/theme.dart';

Widget contentInputBox({
  required Function(String text) onTextChanged,
  required int maxLines,
  required double height,
  String? hintText,
}) {
  return Container(
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(20),
      color: MomoColor.backgroundColor,
    ),
    padding: const EdgeInsets.symmetric(horizontal: 24),
    height: height,
    width: double.infinity,
    child: Center(
      child: TextFormField(
        onChanged: (text) {
          onTextChanged(text);
        },
        style: MomoTextStyle.normal,
        maxLines: maxLines,
        decoration: InputDecoration(
          hintText: hintText ?? '',
          hintStyle: MomoTextStyle.normal.copyWith(
            color: MomoColor.unSelIcon,
            fontWeight: FontWeight.w400,
          ),
        ),
      ),
    ),
  );
}
