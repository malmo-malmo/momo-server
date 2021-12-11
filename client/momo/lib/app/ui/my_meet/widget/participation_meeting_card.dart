import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/components/category/category_icon.dart';
import 'package:momo/app/util/theme.dart';

class ParticipationMeetingCard extends StatelessWidget {
  const ParticipationMeetingCard({
    Key? key,
    required this.title,
    required this.time,
  }) : super(key: key);

  final String title;
  final String time;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 7),
      child: Material(
        elevation: 1,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(20),
        ),
        child: Container(
          padding: const EdgeInsets.symmetric(horizontal: 24),
          height: 72.h,
          width: double.infinity,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(20),
          ),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Row(
                children: [
                  categoryIcon('건강'),
                  const SizedBox(width: 16),
                  Text(
                    title,
                    style: MomoTextStyle.defaultStyle,
                  ),
                ],
              ),
              Transform.rotate(
                angle: pi,
                child: Icon(
                  CupertinoIcons.back,
                  size: 18.w,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
