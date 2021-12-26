import 'package:flutter/material.dart';
import 'package:momo/app/theme/theme.dart';

class ConfirmButton extends StatelessWidget {
  const ConfirmButton({
    Key? key,
    required this.check,
    required this.buttonText,
    required this.onPressButton,
  }) : super(key: key);

  final bool check;
  final String buttonText;
  final Function onPressButton;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
        height: 56,
        width: double.infinity,
        child: ElevatedButton(
            style: ButtonStyle(
                shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                    RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(20))),
                elevation: MaterialStateProperty.all(0),
                textStyle: MaterialStateProperty.resolveWith((states) {
                  if (states.contains(MaterialState.disabled)) {
                    return MomoTextStyle.defaultStyle
                        .copyWith(color: MomoColor.unSelText);
                  }
                  return MomoTextStyle.defaultStyle
                      .copyWith(color: MomoColor.white);
                }),
                backgroundColor: MaterialStateProperty.resolveWith((states) {
                  if (states.contains(MaterialState.disabled)) {
                    return MomoColor.unSelButton;
                  }
                  return MomoColor.main;
                })),
            onPressed: check ? () => onPressButton() : null,
            child: Text(buttonText)));
  }
}
