import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/login/user_info_provider.dart';
import 'package:momo/app/ui/login/widget/agree_button.dart';
import 'package:momo/app/ui/login/widget/input_box.dart';
import 'package:momo/app/ui/login/widget/title_text.dart';
import 'package:momo/app/util/theme.dart';
import 'package:custom_pop_up_menu/custom_pop_up_menu.dart';

class InfoPage extends ConsumerWidget {
  const InfoPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return SafeArea(
      child: Scaffold(
        body: SingleChildScrollView(
          child: Padding(
            padding:
                const EdgeInsets.only(top: 91, left: 16, right: 16, bottom: 24),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                titleText('내 정보 설정  3/3'),
                SizedBox(height: 16.h),
                subTitleText('내 정보를 입력해주세요'),
                _subTitle('이름 (닉네임)'),
                inputBox(
                  searchIcon: Container(
                      height: 29,
                      width: 58.w,
                      decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(16),
                          color: MomoColor.main),
                      child: const Center(
                          child: Text('중복확인',
                              style: TextStyle(
                                color: MomoColor.white,
                              )))),
                  onTabIcon: () {},
                  onTextChanged:
                      ref.read(userInfoStateProvider.notifier).setUserNickname,
                ),
                _subTitle('학교'),
                inputBox(
                  searchIcon: Icon(
                    CupertinoIcons.search,
                    size: 28.w,
                  ),
                  onTabIcon: () {},
                  onTextChanged:
                      ref.read(userInfoStateProvider.notifier).setUserSchool,
                ),
                _subTitle('지역'),
                Row(
                  children: [
                    CustomPopupMenu(
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
                              '지역',
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
                          color: Colors.red,
                          child: ListView.separated(
                            itemCount: 10,
                            itemBuilder: (context, index) {
                              return SizedBox(
                                height: 30,
                                child: Center(
                                  child: Text(
                                    '$index',
                                  ),
                                ),
                              );
                            },
                            separatorBuilder: (context, index) =>
                                const SizedBox(height: 8),
                          ),
                        );
                      },
                      pressType: PressType.singleClick,
                      barrierColor: Colors.transparent,
                      showArrow: false,
                      horizontalMargin: 20,
                      verticalMargin: 0,
                      position: PreferredPosition.bottom,
                    )
                  ],
                ),
                SizedBox(height: 180.h),
                agreeButton(
                  check: false,
                  nextPage: 'adf',
                  text: '다음',
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget _subTitle(String title) {
    return Padding(
      padding: EdgeInsets.only(top: 48.h, bottom: 16.h),
      child: Text(
        title,
        style: TextStyle(
          color: MomoColor.black,
          fontSize: 20.sp,
        ),
      ),
    );
  }
}
