import 'package:flutter/material.dart';

class MyBehavior extends ScrollBehavior {
  @override
  Widget buildViewportChrome(
      BuildContext context, Widget child, AxisDirection axisDirection) {
    return child;
  }
}

class MomoColor {
  static const main = Color(0xff8a69f2);
  static const black = Color(0xff222222);
  static const white = Color(0xfffdfdfd);
  static const unSelIcon = Color(0xff9e9e9e);
  static const unSelText = Color(0xff616161);
  static const unRated = Color(0xffebebec);
  static const divider = Color(0xffdcd9e5);
}
