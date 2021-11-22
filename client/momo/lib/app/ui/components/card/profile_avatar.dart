import 'package:flutter/material.dart';
import 'package:momo/app/util/theme.dart';

Widget profileAvatar({
  required String img,
  required double rad,
}) {
  return CircleAvatar(
    radius: rad,
    backgroundColor: MomoColor.white,
    child: CircleAvatar(
      radius: rad - 1,
      backgroundColor: Colors.transparent,
      backgroundImage: NetworkImage(img),
    ),
  );
}
