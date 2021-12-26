import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/user/user_data_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/util/navigation_service.dart';

Widget startCard() {
  return Container(
    color: MomoColor.white,
    child: SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          const SizedBox(height: 138),
          SizedBox(
            height: 257,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                SizedBox(
                  height: 216,
                  child: AspectRatio(
                    aspectRatio: 2 / 5,
                    child: Container(
                      decoration: const BoxDecoration(
                        image: DecorationImage(
                          fit: BoxFit.fitHeight,
                          alignment: FractionalOffset.centerRight,
                          image: AssetImage('assets/image/img_onboard_4_1.png'),
                        ),
                      ),
                    ),
                  ),
                ),
                Image.asset('assets/image/img_onboard_4_2.png'),
                SizedBox(
                  height: 216,
                  child: AspectRatio(
                    aspectRatio: 2 / 5,
                    child: Container(
                      decoration: const BoxDecoration(
                        image: DecorationImage(
                          fit: BoxFit.fitHeight,
                          alignment: FractionalOffset.centerLeft,
                          image: AssetImage('assets/image/img_onboard_4_3.png'),
                        ),
                      ),
                    ),
                  ),
                ),
              ],
            ),
          ),
          const SizedBox(height: 69),
          Image.asset(
            'assets/image/icon_onboard_4.png',
            width: 189.w,
          ),
          const SizedBox(height: 20),
          Text(
            '추천, 내 주변, 학교\n관련 모임까지 한 눈에',
            style: MomoTextStyle.onboarding
                .copyWith(height: 1.3, color: MomoColor.main),
            textAlign: TextAlign.center,
          ),
          const SizedBox(height: 88),
          SizedBox(
            height: 56,
            width: 320.w,
            child: Consumer(builder: (context, ref, _) {
              return ElevatedButton(
                onPressed: () async {
                  await ref.watch(userDataProvider.future);
                  ref
                      .read(navigatorProvider)
                      .navigateToRemove(routeName: AppRoutes.main);
                },
                style: ElevatedButton.styleFrom(
                  primary: MomoColor.main,
                  elevation: 0,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(20),
                  ),
                ),
                child: Text(
                  'MOMO 시작하기',
                  style: MomoTextStyle.defaultStyle
                      .copyWith(color: MomoColor.white),
                ),
              );
            }),
          ),
          const SizedBox(height: 36),
        ],
      ),
    ),
  );
}
