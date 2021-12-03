import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/user/location_result_provider.dart';

Widget districtInputBox({
  required Function(String value) setCountry,
  required String district,
}) {
  final List<String> _valueList =
      locationCodeNamePair.map((e) => e.name).toList();
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
            value: district,
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
