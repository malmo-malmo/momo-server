import 'package:flutter/material.dart';
import 'package:momo/app/util/theme.dart';

Widget loadingCard() {
  return const SizedBox(
    height: 36,
    width: 36,
    child: Center(
      child: CircularProgressIndicator(
        color: MomoColor.main,
        strokeWidth: 2,
      ),
    ),
  );
}
