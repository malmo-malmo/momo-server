import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/onboarding_index_provider.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class OnboardingPage extends ConsumerWidget {
  const OnboardingPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final pageIndex = ref.watch(onboardingProvider);

    return SafeArea(
      child: Scaffold(
          body: Padding(
        padding:
            const EdgeInsets.only(right: 16, left: 16, top: 56, bottom: 24),
        child: Column(
          children: [
            Text(
              '온보딩 제목 ${pageIndex + 1}',
              style: TextStyle(
                color: MomoColor.black,
                fontSize: 28.sp,
              ),
            ),
            const SizedBox(height: 8),
            Text(
              '이곳에 설명이 들어갑니다',
              style: TextStyle(
                color: MomoColor.black,
                fontSize: 14.sp,
              ),
            ),
            const SizedBox(height: 16),
            Expanded(
              child: PageView(
                onPageChanged: (index) {
                  ref.read(onboardingStateProvider.state).state = index;
                },
                children: [
                  Image.network(
                      'https://t1.daumcdn.net/cfile/tistory/99086B3C5B9B75C431'),
                  Image.network(
                      'https://t3.daumcdn.net/thumb/R720x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/guest/image/PMDMIJW8MuTlmn0vnoXpKHp4-QQ.JPG'),
                  Image.network(
                      'https://mblogthumb-phinf.pstatic.net/20160829_126/miniberry89_1472411951976SASny_PNG/1012xiphone.png?type=w800'),
                ],
              ),
            ),
            const SizedBox(height: 16),
            _circleStatus(pageIndex),
            const SizedBox(height: 16),
            SizedBox(
              height: 48,
              width: 244,
              child: ElevatedButton(
                onPressed: () {
                  ref
                      .read(navigatorProvider)
                      .navigateToRemove(routeName: AppRoutes.main);
                },
                style: ElevatedButton.styleFrom(
                  primary: MomoColor.main,
                  elevation: 0,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(39),
                  ),
                ),
                child: const Text(
                  '시작하기',
                ),
              ),
            )
          ],
        ),
      )),
    );
  }

  Widget _circleStatus(int index) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Container(
          width: 8,
          height: 8,
          decoration: BoxDecoration(
            shape: BoxShape.circle,
            color:
                index == 0 ? const Color(0xff595959) : const Color(0xffbfbfbf),
          ),
        ),
        const SizedBox(width: 8),
        Container(
          width: 8,
          height: 8,
          decoration: BoxDecoration(
            shape: BoxShape.circle,
            color:
                index == 1 ? const Color(0xff595959) : const Color(0xffbfbfbf),
          ),
        ),
        const SizedBox(width: 8),
        Container(
          width: 8,
          height: 8,
          decoration: BoxDecoration(
            shape: BoxShape.circle,
            color:
                index == 2 ? const Color(0xff595959) : const Color(0xffbfbfbf),
          ),
        ),
      ],
    );
  }
}
