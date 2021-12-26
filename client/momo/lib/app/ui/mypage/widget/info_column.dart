import 'package:flutter/material.dart';
import 'package:momo/app/theme/theme.dart';

class InfoColumn extends StatelessWidget {
  const InfoColumn({
    Key? key,
    required this.count,
    required this.title,
  }) : super(key: key);

  final int count;
  final String title;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        InkWell(
          onTap: () {},
          child: SizedBox(
            height: 44,
            width: 44,
            child: Center(
              child: Text(
                '$count',
                style: MomoTextStyle.onboarding.copyWith(
                  decoration: TextDecoration.underline,
                  color: MomoColor.black,
                ),
              ),
            ),
          ),
        ),
        Text(
          title,
          style: MomoTextStyle.normalR,
        ),
      ],
    );
  }
}
