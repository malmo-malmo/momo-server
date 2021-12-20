import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/util/theme.dart';

class FloatingTextButton extends StatefulWidget {
  const FloatingTextButton({
    required this.hintText,
    required this.onTapIcon,
    required this.sendMessage,
    Key? key,
  }) : super(key: key);

  final String hintText;
  final Function onTapIcon;
  final Function(String text) sendMessage;

  @override
  State<FloatingTextButton> createState() => _FloatingTextButtonState();
}

class _FloatingTextButtonState extends State<FloatingTextButton> {
  bool check = false;
  final _controller = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return SafeArea(
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
                  controller: _controller,
                  decoration: InputDecoration(
                    hintText: widget.hintText,
                    border: InputBorder.none,
                  ),
                  onChanged: (text) {
                    setState(() {
                      check = _controller.text.isNotEmpty;
                    });
                  },
                ),
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(left: 12),
            child: InkWell(
              borderRadius: BorderRadius.circular(22),
              onTap: check
                  ? () {
                      widget.sendMessage(_controller.text);
                      widget.onTapIcon();
                      _controller.clear();
                      FocusScope.of(context).unfocus();
                    }
                  : null,
              child: SvgPicture.asset(
                'assets/icon/icon_sendgray_44.svg',
                color: check ? MomoColor.main : null,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
