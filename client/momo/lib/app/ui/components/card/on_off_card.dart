import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/theme/theme.dart';

class OnOffCard extends StatelessWidget {
  const OnOffCard({Key? key, required this.onOff}) : super(key: key);

  final bool onOff;

  @override
  Widget build(BuildContext context) {
    return Container(
      width: onOff ? 33 + 21.w : 44 + 21.w,
      height: 25.h,
      child: Center(
        child: Text(
          onOff ? '온라인' : '오프라인',
          style: MomoTextStyle.small.copyWith(
            color: onOff ? MomoColor.main : MomoColor.white,
          ),
        ),
      ),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(25),
        color: onOff ? MomoColor.white : MomoColor.main,
      ),
    );
  }
}
