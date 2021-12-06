import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/message/message_controller_provider.dart';
import 'package:momo/app/util/theme.dart';

class FloatingTextButton extends ConsumerWidget {
  const FloatingTextButton({
    required this.hintText,
    required this.onTapIcon,
    required this.check,
    required this.setMessage,
    Key? key,
  }) : super(key: key);

  final String hintText;
  final Function onTapIcon;
  final Function(String text) setMessage;
  final bool check;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final _controller = ref.watch(messageTextControllerProvider);

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
                  controller: _controller,
                  decoration: InputDecoration(
                    hintText: hintText,
                    border: InputBorder.none,
                  ),
                  onChanged: (text) => setMessage(text),
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
              onTap: check
                  ? () {
                      onTapIcon();
                      _controller.clear();
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
