import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/user/user_info_provider.dart';
import 'package:momo/app/ui/login/widget/district_result_dioalog.dart';

Widget districtBox({
  required String district,
  required String cityCode,
}) {
  return Consumer(builder: (context, ref, _) {
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
          Text(district),
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
                  return districtResultDialog(
                    onSelect: ref
                        .read(userInfoStateProvider.notifier)
                        .setUserDistrict,
                    cityCode: cityCode,
                  );
                },
              );
            },
          ),
        ],
      ),
    );
  });
}
