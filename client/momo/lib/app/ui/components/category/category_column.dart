import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/util/constant.dart';
import 'package:momo/app/util/theme.dart';

Widget categoryColumn({
  required bool check,
  required int index,
  required void Function(int index) onTabIcon,
  double? iconSize,
  TextStyle? textStyle,
  double? spaceHeight,
}) {
  return Column(
    children: [
      Consumer(builder: (context, ref, _) {
        return InkWell(
          borderRadius: BorderRadius.circular(iconSize ?? 32),
          onTap: () {
            onTabIcon(index);
          },
          child: SvgPicture.asset(
            check ? selIcons[index] : unSelIcons[index],
            width: iconSize,
          ),
        );
      }),
      SizedBox(height: spaceHeight ?? 10),
      Text(
        categoryCodeNamePair[index].name,
        style: textStyle ?? MomoTextStyle.normal,
      ),
    ],
  );
}
