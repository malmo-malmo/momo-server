import 'package:flutter/material.dart';

part './color.dart';
part './scroll_behavior.dart';
part './text_style.dart';

abstract class MomoThemeData {
  static momoNormarTheme() => ThemeData(
        fontFamily: 'NanumSquareOTF',
        appBarTheme: const AppBarTheme(
          backgroundColor: MomoColor.flutterWhite,
          toolbarHeight: 56,
          elevation: 0,
          centerTitle: false,
          titleSpacing: 0,
        ),
        backgroundColor: MomoColor.flutterWhite,
        scaffoldBackgroundColor: MomoColor.backgroundColor,
        inputDecorationTheme: const InputDecorationTheme(
          border: InputBorder.none,
        ),
        shadowColor: MomoColor.blur,
        textSelectionTheme: const TextSelectionThemeData(
          cursorColor: MomoColor.flutterBlack,
        ),
      );

  static momoDarkTheme() => ThemeData(
        fontFamily: 'NanumSquareOTF',
        appBarTheme: const AppBarTheme(
          backgroundColor: MomoColor.flutterWhite,
          toolbarHeight: 56,
          elevation: 0,
          centerTitle: false,
          titleSpacing: 0,
        ),
        shadowColor: MomoColor.blur,
        backgroundColor: MomoColor.backgroundColor,
        inputDecorationTheme: const InputDecorationTheme(
          border: InputBorder.none,
        ),
        textSelectionTheme: const TextSelectionThemeData(
          cursorColor: MomoColor.flutterBlack,
        ),
      );
}
