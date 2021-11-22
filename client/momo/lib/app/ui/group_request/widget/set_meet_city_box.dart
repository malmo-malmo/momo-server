import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

class SetMeetCityBox extends ConsumerWidget {
  SetMeetCityBox({
    Key? key,
    required this.curCity,
    required this.onSelect,
  }) : super(key: key);

  final String curCity;
  final Function(String text) onSelect;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Container(
      padding: const EdgeInsets.only(left: 24, right: 16),
      height: 44,
      width: 104,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(22),
        color: MomoColor.divider,
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(
            curCity.isEmpty ? '모모시' : curCity,
            style: TextStyle(
              color: MomoColor.black,
              fontSize: 16.sp,
            ),
          ),
          Transform.rotate(
            angle: pi * 3 / 2,
            child: Icon(
              CupertinoIcons.back,
              size: 12.w,
            ),
          )
        ],
      ),
    );
  }
}
