import 'package:flutter/cupertino.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/util/constant.dart';

Widget categoryIcon(String categoryName, {double? size = 36}) {
  switch (categoryName) {
    case '취미':
      return SvgPicture.asset(
        iconHobby,
        width: size,
        height: size,
      );
    case '건강':
      return SvgPicture.asset(
        iconHealth,
        width: size,
        height: size,
      );
    case '취업':
      return SvgPicture.asset(
        iconJob,
        width: size,
        height: size,
      );
    case '밥약':
      return SvgPicture.asset(
        iconFood,
        width: size,
        height: size,
      );
    case '자산':
      return SvgPicture.asset(
        iconStock,
        width: size,
        height: size,
      );
    case '자기계발':
      return SvgPicture.asset(
        iconSelf,
        width: size,
        height: size,
      );
    case '힐링':
      return SvgPicture.asset(
        iconHealing,
        width: size,
        height: size,
      );
    case '생활':
      return SvgPicture.asset(
        iconLife,
        width: size,
        height: size,
      );
    default:
      return const Icon(CupertinoIcons.heart);
  }
}
