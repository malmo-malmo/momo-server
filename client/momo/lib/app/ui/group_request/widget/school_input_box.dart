import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

Widget schoolInputBox({
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
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          SizedBox(
            height: 18,
            width: 240.w,
            child: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 14),
              child: TextField(
                onChanged: (text) {
                  onTextChanged(text);
                },
              ),
            ),
          ),
          InkWell(
            child: const Icon(
              CupertinoIcons.search,
            ),
            onTap: () {},
          ),
        ],
      ),
    ),
  );
}
