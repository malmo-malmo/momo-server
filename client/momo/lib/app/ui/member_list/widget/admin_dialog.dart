import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/member/member.dart';
import 'package:momo/app/ui/components/profile_avatar.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget adminDialog({
  required Member member,
}) {
  return Dialog(
    shape: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(20),
    ),
    child: Container(
      padding: const EdgeInsets.only(right: 24, left: 24, top: 48, bottom: 24),
      height: 263,
      width: 294,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          profileAvatar(img: member.profile, rad: 24),
          Text(member.name),
          Text(
            '정말 해당 멤버에게 모임장 권한을 넘기시겠습니까?',
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
                  ref.read(navigatorProvider).pop(result: true);
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
