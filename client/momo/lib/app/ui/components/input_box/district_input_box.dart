import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/dialog/district_result_dioalog.dart';

class DistrictInputBox extends StatelessWidget {
  const DistrictInputBox({
    Key? key,
    required this.district,
    required this.cityCode,
    required this.setDistrict,
    this.backgroundColor,
  }) : super(key: key);

  final String district;
  final String cityCode;
  final Function(String) setDistrict;
  final Color? backgroundColor;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(16),
        color: backgroundColor ?? MomoColor.flutterWhite,
      ),
      height: 44,
      width: 120,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Text(
            district,
            style: MomoTextStyle.defaultStyleR,
          ),
          InkWell(
            child: Transform.rotate(
              angle: pi * 3 / 2,
              child: Icon(
                CupertinoIcons.back,
                size: 12.w,
              ),
            ),
            onTap: () {
              showDialog(
                context: context,
                builder: (context) {
                  return DistrictResultDialog(
                    onSelect: setDistrict,
                    cityCode: cityCode,
                  );
                },
              );
            },
          ),
        ],
      ),
    );
  }
}
