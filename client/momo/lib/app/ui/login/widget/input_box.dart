import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

Widget inputBox({
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
          width: 200.w,
          child: Padding(
            padding: const EdgeInsets.symmetric(horizontal: 14),
            child: TextFormField(
              onChanged: onTextChange,
              style: MomoTextStyle.defaultStyle
                  .copyWith(fontWeight: FontWeight.w400),
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
