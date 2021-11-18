import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/login/user_info_provider.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/login/widget/agree_button.dart';
import 'package:momo/app/ui/login/widget/input_box.dart';
import 'package:momo/app/ui/login/widget/set_city_box.dart';
import 'package:momo/app/ui/login/widget/set_country_box.dart';
import 'package:momo/app/ui/login/widget/title_text.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class InfoPage extends ConsumerWidget {
  const InfoPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final check = ref.watch(userInfoCheckProvider);

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
                  onTabIcon: () {
                    showDialog(
                      context: context,
                      builder: (context) => _duplicateDialog(),
                    );
                  },
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
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    SetCityBox(),
                    SetCountryBox(),
                  ],
                ),
                SizedBox(height: 180.h),
                agreeButton(
                  check: check,
                  nextPage: AppRoutes.onboarding,
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

  Widget _duplicateDialog() {
    return Dialog(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ),
      child: Container(
        padding:
            const EdgeInsets.only(right: 24, left: 24, top: 48, bottom: 24),
        height: 162,
        width: 294,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(
              '해당 이름으로 사용 가능해요',
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
                    ref.read(navigatorProvider).pop();
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
}
