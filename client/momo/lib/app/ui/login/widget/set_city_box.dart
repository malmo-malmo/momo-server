import 'dart:math';

import 'package:custom_pop_up_menu/custom_pop_up_menu.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/login/user_info_provider.dart';
import 'package:momo/app/util/theme.dart';

class SetCityBox extends ConsumerWidget {
  SetCityBox({Key? key}) : super(key: key);

  final _controller = CustomPopupMenuController();

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final userInfo = ref.watch(userInfoProvider);

    return CustomPopupMenu(
      controller: _controller,
      child: Container(
        padding: const EdgeInsets.only(left: 24, right: 16),
        height: 44,
        width: 104,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(22),
          color: MomoColor.divider,
        ),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(
              userInfo.city.isEmpty ? '모모시' : userInfo.city,
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
      ),
      menuBuilder: () {
        return Container(
          height: 80,
          width: 100,
          color: MomoColor.white,
          child: ListView.separated(
            itemCount: 10,
            itemBuilder: (context, index) {
              return InkWell(
                onTap: () {
                  _controller.toggleMenu();
                  ref.read(userInfoStateProvider.notifier).setUserCity('서울');
                },
                child: Container(
                  color: const Color(0xff555555),
                  height: 30,
                  child: const Center(
                    child: Text(
                      '서울',
                    ),
                  ),
                ),
              );
            },
            separatorBuilder: (context, index) => const SizedBox(height: 8),
          ),
        );
      },
      pressType: PressType.singleClick,
      barrierColor: Colors.transparent,
      showArrow: false,
      horizontalMargin: 20,
      verticalMargin: 0,
      position: PreferredPosition.bottom,
    );
  }
}
