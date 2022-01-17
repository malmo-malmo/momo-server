import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/theme/theme.dart';

class OnboardingCard extends StatelessWidget {
  const OnboardingCard({
    Key? key,
    required this.topIcon,
    required this.title,
    required this.img,
  }) : super(key: key);

  final String topIcon;
  final String title;
  final String img;

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          const SizedBox(height: 70),
          SvgPicture.asset(topIcon),
          const SizedBox(height: 23),
          Text(
            title,
            style: MomoTextStyle.onboarding.copyWith(height: 1.3),
            textAlign: TextAlign.center,
          ),
          const SizedBox(height: 56),
          Image.asset(img),
        ],
      ),
    );
  }
}
