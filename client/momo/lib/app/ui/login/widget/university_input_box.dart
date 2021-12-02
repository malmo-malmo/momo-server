import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/user/university_controller_provider.dart';

Widget universityInputBox({
  required Widget searchIcon,
  required Function onTabIcon,
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
          SizedBox(
            height: 18,
            width: 240.w,
            child: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 14),
              child: TextFormField(
                controller: ref.watch(universityTextController),
              ),
            ),
          ),
          InkWell(
            child: searchIcon,
            onTap: () {
              onTabIcon();
            },
          ),
        ],
      ),
    );
  });
}
