import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/ui/components/card/on_off_card.dart';
import 'package:momo/app/ui/components/text/member_date_row.dart';
import 'package:momo/app/util/theme.dart';

class GroupDetailTitle extends StatelessWidget {
  GroupDetailTitle({
    Key? key,
    required this.onOff,
    required this.title,
    required this.count,
    required this.startDate,
    required this.city,
    required this.university,
    required this.district,
    required this.img,
  }) : super(key: key);

  final bool onOff;
  final String title;
  final int count;
  final String startDate;
  final String city;
  final String district;
  final String university;
  String? img;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 290,
      width: double.infinity,
      child: Stack(
        children: [
          Image.network(
            img ??
                'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSo2lT2My2ZPXDPGCTKi6DvpSDEXB5woZPNDw&usqp=CAU',
            height: 290,
            width: double.infinity,
            fit: BoxFit.fill,
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 16),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                const SizedBox(height: 88),
                onOffCard(onOff),
                const SizedBox(height: 10),
                Text(
                  title,
                  style: MomoTextStyle.mainTitle.copyWith(
                    color: MomoColor.white,
                  ),
                ),
                const SizedBox(height: 46),
                _titleRow(
                  icon: 'assets/icon/icon_locationwhite_20.svg',
                  text: city + ' ' + district,
                  textSize: 16.sp,
                ),
                const SizedBox(height: 6),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    _titleRow(
                      icon: 'assets/icon/icon_schoolwhite_20.svg',
                      text: university,
                      textSize: 16.sp,
                    ),
                    memberDateRow(count, startDate),
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