import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/util/theme.dart';

Widget onboardingCard({
  required String topIcon,
  required String title,
  required String img,
}) {
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
