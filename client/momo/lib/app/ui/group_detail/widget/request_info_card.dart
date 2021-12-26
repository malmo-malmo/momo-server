import 'package:flutter/material.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/button/confirm_button.dart';

Widget requestInfoCard({
  required String introduction,
  required Function onTapButton,
}) {
  return SliverToBoxAdapter(
    child: Column(
      children: [
        Container(
          color: const Color(0xffffffff),
          height: 160,
          width: double.infinity,
          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 32),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const Text('모임 설명', style: MomoTextStyle.subTitle),
              const SizedBox(height: 14),
              Text(introduction, style: MomoTextStyle.normal),
            ],
          ),
        ),
        const SizedBox(height: 275),
        ConfirmButton(
          onPressButton: onTapButton,
          buttonText: '신청가능',
          check: true,
        ),
        const SizedBox(height: 36),
      ],
    ),
  );
}
