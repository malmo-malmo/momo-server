import 'package:flutter/material.dart';
import 'package:momo/app/util/theme.dart';

Widget nameInputBox({
  required Function(String text) onTextChanged,
  String? hintText,
}) {
  return Container(
    padding: const EdgeInsets.symmetric(horizontal: 24),
    height: 44,
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(16),
      color: MomoColor.backgroundColor,
    ),
    width: double.infinity,
    child: TextField(
      onChanged: (text) {
        onTextChanged(text);
      },
      style: MomoTextStyle.defaultStyle,
      decoration: InputDecoration(
        hintText: hintText ?? '',
        hintStyle: MomoTextStyle.defaultStyle.copyWith(
          color: MomoColor.unSelIcon,
          fontWeight: FontWeight.w400,
        ),
      ),
    ),
  );
}
