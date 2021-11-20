import 'package:flutter/material.dart';
import 'package:momo/app/util/theme.dart';

Widget locationCard(String title) {
  return Container(
    height: 41,
    width: title.length * 7 + 70,
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(20),
      color: Colors.grey,
    ),
    child: Center(
      child: Text(title, style: MomoTextStyle.defaultStyle),
    ),
  );
}
