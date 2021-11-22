import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

class SetMeetCountryBox extends ConsumerWidget {
  const SetMeetCountryBox({
    Key? key,
    required this.curCountry,
    required this.onSelect,
  }) : super(key: key);

  final String curCountry;
  final Function(String text) onSelect;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Container(
      padding: const EdgeInsets.only(left: 24, right: 16),
      height: 44,
      width: 192.w,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(22),
        color: MomoColor.divider,
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(
            curCountry.isEmpty ? '모모구' : curCountry,
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
