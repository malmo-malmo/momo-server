import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/on_off_toggle_provider.dart';
import 'package:momo/app/util/theme.dart';

Widget onOffToggleButton({
  required Function(String onOff) tabButton,
}) {
  return Consumer(builder: (context, ref, _) {
    final checks = ref.watch(onOffToggleProvider);

    return Row(
      children: [
        InkWell(
          onTap: () {
            tabButton('온라인');
            ref.read(onOffToggleStateProvider.notifier).toggle(0);
          },
          child: Container(
            height: 38,
            width: 87,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(20),
              color: checks[0] ? MomoColor.main : const Color(0xffffffff),
            ),
            child: Center(
              child: Text(
                '온라인',
                style: TextStyle(
                  fontSize: 14.sp,
                  color: checks[0] ? MomoColor.white : MomoColor.black,
                ),
              ),
            ),
          ),
        ),
        const SizedBox(width: 16),
        InkWell(
          onTap: () {
            tabButton('오프라인');
            ref.read(onOffToggleStateProvider.notifier).toggle(1);
          },
          child: Container(
            height: 38,
            width: 97,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(20),
              color: checks[1] ? MomoColor.main : const Color(0xffffffff),
            ),
            child: Center(
              child: Text(
                '오프라인',
                style: TextStyle(
                  fontSize: 14.sp,
                  color: checks[1] ? MomoColor.white : MomoColor.black,
                ),
              ),
            ),
          ),
        ),
      ],
    );
  });
}
