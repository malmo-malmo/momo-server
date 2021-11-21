import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

Widget inputBox({
  required String value,
  required Widget searchIcon,
  required Function onTabIcon,
}) {
  final _controller = TextEditingController(text: value);

  return Container(
    padding: const EdgeInsets.symmetric(horizontal: 16),
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(16),
      color: const Color(0xffffffff),
    ),
    height: 44,
    child: Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        SizedBox(
          height: 18,
          width: 240.w,
          child: Padding(
            padding: const EdgeInsets.symmetric(horizontal: 14),
            child: TextFormField(
              controller: _controller,
            ),
          ),
        ),
        InkWell(
          child: searchIcon,
          onTap: () {
            onTabIcon();
          },
        ),
      ],
    ),
  );
}

Widget nameInputBox({
  required Widget searchIcon,
  required Function onTabIcon,
  required Function(String value) onTextChange,
}) {
  return Container(
    padding: const EdgeInsets.symmetric(horizontal: 16),
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(16),
      color: const Color(0xffffffff),
    ),
    height: 44,
    child: Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        SizedBox(
          height: 18,
          width: 240.w,
          child: Padding(
            padding: const EdgeInsets.symmetric(horizontal: 14),
            child: TextFormField(
              onChanged: (text) {
                onTextChange(text);
              },
            ),
          ),
        ),
        InkWell(
          child: searchIcon,
          onTap: () {
            onTabIcon();
          },
        ),
      ],
    ),
  );
}
