import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/repository/group_repository.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget withdrawDialog(int groupId) {
  return Dialog(
    shape: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(20),
    ),
    child: Container(
      padding: const EdgeInsets.only(top: 42),
      height: 148,
      width: 280,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(
            '정말 모임에서 탈퇴하시겠어요?',
            style: TextStyle(color: MomoColor.black, fontSize: 16.sp),
          ),
          Consumer(builder: (context, ref, _) {
            return Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                InkWell(
                  onTap: () async {
                    await ref
                        .read(groupRepositoryProvider)
                        .withdrawalGroup(groupId);
                    ref.read(navigatorProvider).pop(result: true);
                  },
                  child: Container(
                    decoration: const BoxDecoration(
                      borderRadius: BorderRadius.only(
                        bottomLeft: Radius.circular(20),
                      ),
                      color: MomoColor.main,
                    ),
                    height: 56,
                    width: 140,
                    child: Center(
                      child: Text(
                        '  네, 나갈래요',
                        style: MomoTextStyle.defaultStyle.copyWith(
                          color: MomoColor.white,
                        ),
                      ),
                    ),
                  ),
                ),
                InkWell(
                  onTap: () => ref.read(navigatorProvider).pop(result: false),
                  child: Container(
                    decoration: const BoxDecoration(
                      borderRadius: BorderRadius.only(
                        bottomRight: Radius.circular(20),
                      ),
                      color: MomoColor.unSelButton,
                    ),
                    height: 56,
                    width: 140,
                    child: Center(
                      child: Text(
                        '아니요',
                        style: MomoTextStyle.defaultStyle.copyWith(
                          color: MomoColor.unSelText,
                        ),
                      ),
                    ),
                  ),
                ),
              ],
            );
          }),
        ],
      ),
    ),
  );
}
