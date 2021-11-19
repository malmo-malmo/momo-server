import 'dart:math';

import 'package:custom_pop_up_menu/custom_pop_up_menu.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/login/user_info_provider.dart';
import 'package:momo/app/util/theme.dart';

class SetCountryBox extends ConsumerWidget {
  SetCountryBox({Key? key}) : super(key: key);

  final _controller = CustomPopupMenuController();

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final userInfo = ref.watch(userInfoProvider);

    return CustomPopupMenu(
      controller: _controller,
      child: Container(
        padding: const EdgeInsets.only(left: 24, right: 16),
        height: 44,
        width: 192.w,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(22),
          color: const Color(0xffffffff),
        ),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(
              userInfo.country.isEmpty ? '모모구' : userInfo.country,
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
          width: 200,
          color: const Color(0xffffffff),
          child: ListView.separated(
            itemCount: 10,
            itemBuilder: (context, index) {
              return InkWell(
                onTap: () {
                  _controller.toggleMenu();
                  ref
                      .read(userInfoStateProvider.notifier)
                      .setUserCountry('강남구');
                },
                child: Container(
                  color: const Color(0xffffffff),
                  height: 30,
                  child: const Center(
                    child: Text(
                      '강남구',
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
