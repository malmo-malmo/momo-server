import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/navigation_service.dart';

Widget agreeButton({
  required bool check,
  required String nextPage,
  required String text,
}) {
  return Consumer(builder: (context, ref, _) {
    return SizedBox(
      height: 57,
      width: double.infinity,
      child: ElevatedButton(
        style: ButtonStyle(
          shape: MaterialStateProperty.all<RoundedRectangleBorder>(
            RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(32),
            ),
          ),
          backgroundColor: MaterialStateProperty.resolveWith(
            (states) {
              if (states.contains(MaterialState.disabled)) {
                return const Color(0xfff2f2f2);
              }
              return const Color(0xff000000);
            },
          ),
        ),
        onPressed: check
            ? () {
                ref.read(navigatorProvider).navigateTo(routeName: nextPage);
              }
            : null,
        child: Text(
          text,
          style: TextStyle(
            fontSize: 16.sp,
          ),
        ),
      ),
    );
  });
}
