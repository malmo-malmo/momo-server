import 'package:flutter/material.dart';
import 'package:momo/app/util/theme.dart';

Widget profileAvatar({
  required String img,
  required double rad,
  Color? backgroundColor,
}) {
  return CircleAvatar(
    radius: rad,
    backgroundColor: backgroundColor ?? MomoColor.white,
    child: CircleAvatar(
      radius: rad - 1,
      backgroundColor: Colors.transparent,
      backgroundImage: NetworkImage(img),
    ),
  );
}
