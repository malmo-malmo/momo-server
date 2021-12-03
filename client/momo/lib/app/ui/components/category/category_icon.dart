import 'package:flutter/cupertino.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/util/constant.dart';

Widget categoryIcon(String categoryName) {
  switch (categoryName) {
    case '취미':
      return SvgPicture.asset(iconHobby);
    case '건강':
      return SvgPicture.asset(iconHealth);
    case '취업':
      return SvgPicture.asset(iconJob);
    case '밥약':
      return SvgPicture.asset(iconFood);
    case '자산':
      return SvgPicture.asset(iconStock);
    case '자기계발':
      return SvgPicture.asset(iconSelf);
    case '힐링':
      return SvgPicture.asset(iconHealing);
    case '생활':
      return SvgPicture.asset(iconLife);
    default:
      return const Icon(CupertinoIcons.heart);
  }
}
