import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/util/theme.dart';

Widget agreeButton({
  required bool check,
  required Function onPressButton,
  required String text,
}) {
  return Consumer(builder: (context, ref, _) {
    return SizedBox(
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
        ),
        onPressed: check
            ? () {
                onPressButton();
              }
            : null,
        child: Text(
          text,
          style: MomoTextStyle.defaultStyle.copyWith(
            color: check ? MomoColor.white : MomoColor.unSelText,
          ),
        ),
      ),
    );
  });
}