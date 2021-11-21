import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

Widget countryInputBox({
  required String country,
  required Function(String value) setCountry,
}) {
  final List<String> _valueList = [
    '강남구',
    '서초구',
    '마포구',
    '관악구',
  ];
  return Container(
    padding: const EdgeInsets.symmetric(horizontal: 16),
    height: 44,
    width: 174,
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(16),
      color: const Color(0xffffffff),
    ),
    child: Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Expanded(
          child: DropdownButton(
            value: country,
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
              setCountry(value!);
            },
          ),
        ),
      ],
    ),
  );
}
