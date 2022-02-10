import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/category/category_icon.dart';

class ParticipationMeetingCard extends StatelessWidget {
  const ParticipationMeetingCard({
    Key? key,
    required this.id,
    required this.title,
    required this.category,
  }) : super(key: key);

  final int id;
  final String title;
  final String category;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 7),
      child: Material(
        elevation: 1,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
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
                  categoryIcon(categoryCodeNamePair
                      .where((element) => element.code == category)
                      .first
                      .name),
                  const SizedBox(width: 16),
                  Text(
                    title,
                    style: MomoTextStyle.defaultStyle,
                  ),
                ],
              ),
              Transform.rotate(
                angle: pi,
                child: InkWell(
                  onTap: () {},
                  child: Icon(
                    CupertinoIcons.back,
                    size: 18.w,
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
