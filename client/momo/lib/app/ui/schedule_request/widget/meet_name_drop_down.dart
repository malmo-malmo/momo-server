import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/theme/theme.dart';

Widget meetNameDropBox({
  required String meetName,
  required Function(String value) setMeetName,
}) {
  final List<String> _valueList = [
    '청계천 달리기&산책',
    '밥먹기',
    '한강가기',
    '영어 회화 스터디',
  ];
  return Container(
    padding: const EdgeInsets.symmetric(horizontal: 16),
    height: 44,
    width: double.infinity,
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(16),
      color: MomoColor.backgroundColor,
    ),
    child: Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Expanded(
          child: DropdownButton(
            value: meetName,
            underline: const SizedBox(),
            elevation: 0,
            isExpanded: true,
            borderRadius: BorderRadius.circular(8),
            icon: Transform.rotate(
              angle: pi * 3 / 2,
              child: Icon(
                CupertinoIcons.back,
                size: 12.w,
              ),
            ),
            items: _valueList.map((value) {
              return DropdownMenuItem<String>(
                value: value,
                child: Text(value),
              );
            }).toList(),
            onChanged: (String? value) {
              setMeetName(value!);
            },
          ),
        ),
      ],
    ),
  );
}
