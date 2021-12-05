import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget groupCloseDialog() {
  return Dialog(
    shape: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(20),
    ),
    child: Container(
      padding: const EdgeInsets.only(right: 24, left: 24, top: 48, bottom: 24),
      height: 203,
      width: 294,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(
            '정말 모임에서 종료하시겠어요?',
            style: TextStyle(color: MomoColor.black, fontSize: 16.sp),
          ),
          Text(
            '모임이 종료되면 더이상 기능을 이용하실 수 없습니다',
            style: TextStyle(color: MomoColor.black, fontSize: 16.sp),
          ),
          SizedBox(
            height: 44,
            width: 241,
            child: Consumer(builder: (context, ref, _) {
              return Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      primary: MomoColor.main,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(20),
                      ),
                    ),
                    onPressed: () {
                      ref.read(navigatorProvider).pop(result: true);
                    },
                    child: Text(
                      '네, 종료!',
                      style: TextStyle(
                        fontSize: 16.sp,
                      ),
                    ),
                  ),
                  ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      primary: MomoColor.main,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(20),
                      ),
                    ),
                    onPressed: () {
                      ref.read(navigatorProvider).pop(result: false);
                    },
                    child: Text(
                      '아니요',
                      style: TextStyle(
                        fontSize: 16.sp,
                      ),
                    ),
                  ),
                ],
              );
            }),
          ),
        ],
      ),
    ),
  );
}
