import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/util/format/group_date_format.dart';
import 'package:momo/app/util/theme.dart';

class MemberDateRow extends StatelessWidget {
  const MemberDateRow({
    Key? key,
    required this.headNum,
    required this.startDay,
    this.color,
  }) : super(key: key);

  final int headNum;
  final String startDay;
  final Color? color;

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Row(
          children: [
            SvgPicture.asset('assets/icon/icon_member_20.svg',
                color: color ?? MomoColor.white),
            const SizedBox(width: 2),
            Text(
              '$headNum',
              style:
                  MomoTextStyle.small.copyWith(color: color ?? MomoColor.white),
            ),
          ],
        ),
        const SizedBox(width: 15),
        Row(
          children: [
            SvgPicture.asset('assets/icon/icon_daystart_20.svg',
                color: color ?? MomoColor.white),
            const SizedBox(width: 2),
            Text(
              groupDateFormat(startDay),
              style:
                  MomoTextStyle.small.copyWith(color: color ?? MomoColor.white),
            ),
          ],
        ),
      ],
    );
  }
}
