import 'package:flutter/material.dart';
import 'package:momo/app/ui/group_detail/widget/request_button.dart';
import 'package:momo/app/util/theme.dart';

Widget requestInfoCard({
  required String introduction,
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
              Text(
                '모임 설명',
                style: MomoTextStyle.subTitle,
              ),
              const SizedBox(height: 14),
              Text(
                introduction,
                style: MomoTextStyle.normal,
              ),
            ],
          ),
        ),
        const SizedBox(height: 275),
        requestButton(),
        const SizedBox(height: 36),
      ],
    ),
  );
}
