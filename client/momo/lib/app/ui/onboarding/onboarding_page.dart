import 'package:flutter/material.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/onboarding/widget/onboard_card.dart';
import 'package:momo/app/ui/onboarding/widget/start_card.dart';

class OnboardingPage extends StatelessWidget {
  const OnboardingPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: MomoColor.main,
        body: PageView(
          children: const [
            OnboardingCard(
              topIcon: 'assets/icon/onboarding/icon_onboard_1.svg',
              title: '리마인더로\n오늘의 모임을 확인하고',
              img: 'assets/image/img_onboard_1.png',
            ),
            OnboardingCard(
              topIcon: 'assets/icon/onboarding/icon_onboard_2.svg',
              title: '내 모임에서 직접 확인하고\n새로운 모임도 만들어요',
              img: 'assets/image/img_onboard_2.png',
            ),
            OnboardingCard(
              topIcon: 'assets/icon/onboarding/icon_onboard_3.svg',
              title: '모든 일정 관리는\n캘린더 & 타임라인에서!',
              img: 'assets/image/img_onboard_3.png',
            ),
            StartCard(),
          ],
        ),
      ),
    );
  }
}
