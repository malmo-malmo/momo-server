import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/util/constant.dart';

class CategoryColumn extends StatelessWidget {
  const CategoryColumn({
    Key? key,
    required this.check,
    required this.index,
    required this.onTabIcon,
    this.iconSize,
    this.textStyle,
    this.spaceHeight,
  }) : super(key: key);

  final bool check;
  final int index;
  final void Function(int index) onTabIcon;
  final double? iconSize;
  final TextStyle? textStyle;
  final double? spaceHeight;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Consumer(
          builder: (context, ref, _) {
            return InkWell(
              borderRadius: BorderRadius.circular(iconSize ?? 32),
              onTap: () => onTabIcon(index),
              child: SvgPicture.asset(
                check ? selIcons[index] : unSelIcons[index],
                width: iconSize,
              ),
            );
          },
        ),
        SizedBox(height: spaceHeight ?? 10),
        Text(
          categoryCodeNamePair[index].name,
          style: textStyle ?? MomoTextStyle.normal,
        ),
      ],
    );
  }
}
