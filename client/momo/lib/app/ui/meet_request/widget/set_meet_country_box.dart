import 'dart:math';

import 'package:custom_pop_up_menu/custom_pop_up_menu.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/theme.dart';

class SetMeetCountryBox extends ConsumerWidget {
  SetMeetCountryBox({
    Key? key,
    required this.curCountry,
    required this.onSelect,
  }) : super(key: key);

  final String curCountry;
  final Function(String text) onSelect;
  final _controller = CustomPopupMenuController();

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return CustomPopupMenu(
      controller: _controller,
      child: Container(
        padding: const EdgeInsets.only(left: 24, right: 16),
        height: 44,
        width: 192.w,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(22),
          color: MomoColor.divider,
        ),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(
              curCountry.isEmpty ? '모모구' : curCountry,
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
                  onSelect('강남구');
                },
                child: Container(
                  color: const Color(0xff555555),
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
