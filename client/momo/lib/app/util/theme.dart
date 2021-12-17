import 'package:flutter/material.dart';

class MyBehavior extends ScrollBehavior {
  @override
  Widget buildViewportChrome(
      BuildContext context, Widget child, AxisDirection axisDirection) {
    return child;
  }
}

final momoThemeData = ThemeData(
  fontFamily: 'NanumSquareOTF',
  appBarTheme: const AppBarTheme(
    backgroundColor: MomoColor.backgroundColor,
  ),
  backgroundColor: MomoColor.backgroundColor,
  inputDecorationTheme: const InputDecorationTheme(
    border: InputBorder.none,
  ),
  textSelectionTheme: const TextSelectionThemeData(
    cursorColor: Color(0xff000000),
  ),
);

class MomoColor {
  static const main = Color(0xff8a69f2);
  static const black = Color(0xff222222);
  static const white = Color(0xfffdfdfd);
  static const unSelIcon = Color(0xff9e9e9e);
  static const unSelText = Color(0xff616161);
  static const unRated = Color(0xffebebec);
  static const divider = Color(0xffdcd9e5);
  static const unSelButton = Color(0xffdedede);
  static const backgroundColor = Color(0xfff7f7f7);
}

class MomoTextStyle {
  static const mainTitle = TextStyle(
    fontSize: 28,
    color: MomoColor.black,
    fontWeight: FontWeight.w700,
  );

  static const onboarding = TextStyle(
    fontSize: 24,
    color: MomoColor.white,
    fontWeight: FontWeight.w700,
  );

  static const subTitle = TextStyle(
    fontSize: 20,
    color: MomoColor.black,
    fontWeight: FontWeight.w700,
  );

  static const defaultStyle = TextStyle(
    fontSize: 16,
    color: MomoColor.black,
    fontWeight: FontWeight.w700,
  );

  static const defaultStyleR = TextStyle(
    fontSize: 16,
    color: MomoColor.black,
    fontWeight: FontWeight.w400,
  );

  static const normal = TextStyle(
    fontSize: 14,
    color: MomoColor.black,
    fontWeight: FontWeight.w700,
  );

  static const small = TextStyle(
    fontSize: 12,
    color: MomoColor.black,
    fontWeight: FontWeight.w700,
  );

  static const card = TextStyle(
    fontSize: 10,
    color: MomoColor.black,
    fontWeight: FontWeight.w700,
  );
}
