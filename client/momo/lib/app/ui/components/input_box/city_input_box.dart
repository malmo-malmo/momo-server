import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/city_result_provider.dart';
import 'package:momo/app/util/theme.dart';

class CityInputBox extends StatelessWidget {
  CityInputBox({
    Key? key,
    required this.setCity,
    required this.city,
    this.backgroundColor,
  }) : super(key: key);

  final Function(String value) setCity;
  final String city;
  final Color? backgroundColor;

  final _valueList = cityCodeNamePair.map((e) => e.name).toList();

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      height: 44,
      width: 156,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(16),
        color: backgroundColor ?? const Color(0xffffffff),
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Expanded(
            child: DropdownButton(
              value: city,
              selectedItemBuilder: (context) => _valueList
                  .map((value) => Align(
                      alignment: Alignment.centerLeft,
                      child: Text(value, style: MomoTextStyle.defaultStyleR)))
                  .toList(),
              underline: const SizedBox(),
              elevation: 0,
              isExpanded: true,
              borderRadius: BorderRadius.circular(8),
              icon: Transform.rotate(
                angle: pi * 3 / 2,
                child: Icon(CupertinoIcons.back, size: 12.w),
              ),
              items: _valueList
                  .map((value) => DropdownMenuItem<String>(
                      value: value,
                      child: Text(value, style: MomoTextStyle.defaultStyleR)))
                  .toList(),
              onChanged: (String? value) {
                setCity(value!);
              },
            ),
          ),
        ],
      ),
    );
  }
}
