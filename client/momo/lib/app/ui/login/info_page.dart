import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/login/name_check_provider.dart';
import 'package:momo/app/provider/login/user_info_provider.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/login/widget/agree_button.dart';
import 'package:momo/app/ui/login/widget/input_box.dart';
import 'package:momo/app/ui/login/widget/school_result_dialog.dart';
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
    final userNameCheck = ref.watch(nameCheckProvider);
    final userInfo = ref.watch(userInfoProvider);

    return SafeArea(
      child: Scaffold(
        backgroundColor: const Color(0xfff7f7f7),
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
                _subTitle('닉네임'),
                inputBox(
                  searchIcon: Container(
                    height: 29,
                    width: 64.w,
                    decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(12),
                        color: userNameCheck
                            ? MomoColor.main
                            : MomoColor.unSelButton),
                    child: Center(
                      child: Text(
                        '중복확인',
                        style: TextStyle(
                          color: userNameCheck
                              ? MomoColor.white
                              : MomoColor.unSelText,
                        ),
                      ),
                    ),
                  ),
                  onTabIcon: userNameCheck
                      ? () {
                          showDialog(
                            context: context,
                            builder: (context) => _duplicateDialog(),
                          );
                        }
                      : () {},
                  onTextChanged:
                      ref.read(userInfoStateProvider.notifier).setUserNickname,
                ),
                _subTitle('학교'),
                inputBox(
                  searchIcon: Icon(
                    CupertinoIcons.search,
                    size: 28.w,
                  ),
                  onTabIcon: () {
                    showDialog(
                      context: context,
                      builder: (context) {
                        return schoolResultDialog();
                      },
                    );
                  },
                  onTextChanged:
                      ref.read(userInfoStateProvider.notifier).setUserSchool,
                ),
                _subTitle('지역'),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    cityInputBox(
                      city: userInfo.city,
                      setCity:
                          ref.read(userInfoStateProvider.notifier).setUserCity,
                    ),
                    countryInputBox(
                      country: userInfo.country,
                      setCountry: ref
                          .read(userInfoStateProvider.notifier)
                          .setUserCountry,
                    ),
                  ],
                ),
                SizedBox(height: 200.h),
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
      insetPadding: const EdgeInsets.all(1),
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ),
      child: Container(
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          color: const Color(0xffffffff),
        ),
        height: 148,
        width: 280,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Padding(
              padding: const EdgeInsets.only(top: 40),
              child: Text(
                '사용 가능한 닉네임이에요',
                style: TextStyle(color: MomoColor.black, fontSize: 16.sp),
              ),
            ),
            Consumer(
              builder: (context, ref, _) {
                return InkWell(
                  onTap: () {
                    ref.read(navigatorProvider).pop();
                  },
                  child: Container(
                    decoration: const BoxDecoration(
                      borderRadius: BorderRadius.only(
                        bottomLeft: Radius.circular(20),
                        bottomRight: Radius.circular(20),
                      ),
                      color: MomoColor.main,
                    ),
                    height: 44,
                    width: double.infinity,
                    child: Center(
                      child: Text(
                        '확인',
                        style: MomoTextStyle.defaultStyle.copyWith(
                          color: MomoColor.white,
                        ),
                      ),
                    ),
                  ),
                );
              },
            ),
          ],
        ),
      ),
    );
  }
}
