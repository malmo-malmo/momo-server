import 'package:flutter/material.dart';
import 'package:momo/app/util/theme.dart';

Widget requestButton() {
  return Container(
    padding: const EdgeInsets.symmetric(horizontal: 16),
    height: 56,
    width: double.infinity,
    child: ElevatedButton(
      style: ButtonStyle(
        shape: MaterialStateProperty.all<RoundedRectangleBorder>(
          RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(20),
          ),
        ),
        backgroundColor: MaterialStateProperty.resolveWith(
          (states) {
            if (states.contains(MaterialState.disabled)) {
              return MomoColor.unSelButton;
            }
            return MomoColor.main;
          },
        ),
        textStyle: MaterialStateProperty.resolveWith((states) {
          if (states.contains(MaterialState.disabled)) {
            return MomoTextStyle.defaultStyle.copyWith(
              color: MomoColor.unSelText,
            );
          }
          return MomoTextStyle.defaultStyle.copyWith(
            color: MomoColor.white,
          );
        }),
      ),
      onPressed: () {},
      child: const Text(
        '신청 가능',
      ),
    ),
  );
}
