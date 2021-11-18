import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget withdrawDialog() {
  return Dialog(
    shape: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(20),
    ),
    child: Container(
      padding: const EdgeInsets.only(right: 24, left: 24, top: 48, bottom: 24),
      height: 162,
      width: 294,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(
            '해당 이름으로 사용 가능해요',
            style: TextStyle(color: MomoColor.black, fontSize: 16.sp),
          ),
          SizedBox(
            height: 44,
            width: 241,
            child: Consumer(builder: (context, ref, _) {
              return ElevatedButton(
                style: ElevatedButton.styleFrom(
                  primary: MomoColor.main,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(20),
                  ),
                ),
                onPressed: () {
                  ref.read(navigatorProvider).pop();
                },
                child: Text(
                  '확인',
                  style: TextStyle(
                    fontSize: 16.sp,
                  ),
                ),
              );
            }),
          ),
        ],
      ),
    ),
  );
}
