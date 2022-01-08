import 'package:flutter/material.dart';
import 'package:momo/app/theme/theme.dart';

class TextInputBox extends StatelessWidget {
  const TextInputBox({
    Key? key,
    required this.onTextChanged,
    required this.height,
    required this.hintText,
    this.maxLines,
  }) : super(key: key);

  final Function(String text) onTextChanged;
  final int? maxLines;
  final double height;
  final String hintText;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 24),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(16),
        color: MomoColor.backgroundColor,
      ),
      height: height,
      width: double.infinity,
      child: Center(
        child: TextFormField(
          onChanged: onTextChanged,
          textInputAction: maxLines == null ? TextInputAction.done : null,
          style: MomoTextStyle.normal,
          maxLines: maxLines,
          decoration: InputDecoration(
            hintText: hintText,
            hintStyle: MomoTextStyle.normalR.copyWith(
              color: MomoColor.unSelIcon,
            ),
          ),
        ),
      ),
    );
  }
}
