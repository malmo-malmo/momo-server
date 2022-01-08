import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:momo/app/theme/theme.dart';

class SettingMenuCard extends StatelessWidget {
  const SettingMenuCard({
    Key? key,
    required this.title,
    required this.onTap,
  }) : super(key: key);

  final String title;
  final Function onTap;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      color: MomoColor.flutterWhite,
      height: 50,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Text(
            title,
            style: MomoTextStyle.defaultStyle,
          ),
          InkWell(
            onTap: () {
              onTap();
            },
            child: Transform.rotate(
              angle: pi,
              child: const Icon(
                CupertinoIcons.back,
                size: 20,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
