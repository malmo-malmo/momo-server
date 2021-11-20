import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/util/theme.dart';

class MeetingTitle extends StatelessWidget {
  const MeetingTitle({
    Key? key,
    required this.onOff,
    required this.meetingTitle,
    required this.count,
    required this.startDate,
    required this.location,
    required this.school,
    required this.img,
  }) : super(key: key);

  final String onOff;
  final String meetingTitle;
  final int count;
  final String startDate;
  final String location;
  final String school;
  final String img;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 280,
      width: double.infinity,
      child: Stack(
        children: [
          Image.network(
            img,
            height: 280,
            width: double.infinity,
            fit: BoxFit.fill,
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 16),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                const SizedBox(height: 88),
                Container(
                  width: onOff.length * 11.0 + 21.w,
                  height: 25.h,
                  child: Center(
                    child: Text(
                      onOff,
                      style: TextStyle(
                        fontSize: 12.sp,
                        color: MomoColor.white,
                      ),
                    ),
                  ),
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(20),
                    color: MomoColor.main,
                  ),
                ),
                const SizedBox(height: 16),
                Text(
                  meetingTitle,
                  style: MomoTextStyle.mainTitle.copyWith(
                    color: MomoColor.white,
                  ),
                ),
                const SizedBox(height: 24),
                _titleRow(
                  icon: 'assets/icon/icon_locationwhite_20.svg',
                  text: location,
                  textSize: 16.sp,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    _titleRow(
                      icon: 'assets/icon/icon_schoolwhite_20.svg',
                      text: school,
                      textSize: 16.sp,
                    ),
                    Row(
                      children: [
                        _titleRow(
                          icon: 'assets/icon/icon_member_20.svg',
                          text: '$count',
                          textSize: 12.sp,
                        ),
                        const SizedBox(width: 16),
                        _titleRow(
                          icon: 'assets/icon/icon_daystart_20.svg',
                          text: startDate,
                          textSize: 12.sp,
                        ),
                      ],
                    ),
                  ],
                )
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget _titleRow({
    required String icon,
    required String text,
    required double textSize,
  }) {
    return Row(
      children: [
        SvgPicture.asset(
          icon,
        ),
        const SizedBox(width: 8),
        Text(
          text,
          style: TextStyle(
            fontSize: textSize,
            color: MomoColor.white,
          ),
        ),
      ],
    );
  }
}
