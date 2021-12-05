import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/util/theme.dart';

class FloatingTextButton extends StatelessWidget {
  const FloatingTextButton({
    required this.hintText,
    required this.onTextChanged,
    required this.onTapIcon,
    Key? key,
  }) : super(key: key);

  final String hintText;
  final Function(String text) onTextChanged;
  final Function onTapIcon;

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      bottom: true,
      top: false,
      child: Row(
        children: [
          Expanded(
            child: Padding(
              padding: const EdgeInsets.symmetric(vertical: 14),
              child: Container(
                height: 44,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(16),
                  color: MomoColor.backgroundColor,
                ),
                padding: const EdgeInsets.only(left: 16.0),
                child: TextFormField(
                  style: MomoTextStyle.defaultStyle,
                  decoration: InputDecoration(
                    hintText: hintText,
                    border: InputBorder.none,
                  ),
                  onChanged: (text) {
                    onTextChanged(text);
                  },
                ),
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(
              left: 12,
            ),
            child: InkWell(
              borderRadius: BorderRadius.circular(22),
              onTap: () {
                onTapIcon();
              },
              child: SvgPicture.asset('assets/icon/icon_sendgray_44.svg'),
            ),
          ),
        ],
      ),
    );
  }
}
