import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/ui/components/card/on_off_card.dart';
import 'package:momo/app/ui/components/text/member_date_row.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class GroupDetailTitle extends StatelessWidget {
  const GroupDetailTitle({
    Key? key,
    required this.onOff,
    required this.title,
    required this.count,
    required this.startDate,
    required this.city,
    this.university,
    required this.district,
    required this.img,
  }) : super(key: key);

  final bool onOff;
  final String title;
  final int count;
  final String startDate;
  final String city;
  final String district;
  final String? university;
  final String? img;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 290,
      width: double.infinity,
      child: Stack(
        children: [
          Consumer(builder: (context, ref, _) {
            return InkWell(
              onTap: () {
                ref.read(navigatorProvider).navigateTo(
                      routeName: AppRoutes.fullImage,
                      arguments: img ??
                          'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSo2lT2My2ZPXDPGCTKi6DvpSDEXB5woZPNDw&usqp=CAU',
                    );
              },
              child: Image.network(
                img ??
                    'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSo2lT2My2ZPXDPGCTKi6DvpSDEXB5woZPNDw&usqp=CAU',
                height: 290,
                width: double.infinity,
                fit: BoxFit.fill,
              ),
            );
          }),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 16),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                const SizedBox(height: 88),
                OnOffCard(onOff: onOff),
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
                  textSize: 16,
                ),
                const SizedBox(height: 6),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    _titleRow(
                      icon: 'assets/icon/icon_schoolwhite_20.svg',
                      text: university ?? '대학교 없음',
                      textSize: 16,
                    ),
                    MemberDateRow(headNum: count, startDay: startDate),
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
        SvgPicture.asset(icon),
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
