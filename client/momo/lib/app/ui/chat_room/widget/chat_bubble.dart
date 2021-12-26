import 'package:flutter/material.dart';
import 'package:momo/app/theme/theme.dart';

class ChatBubble extends StatelessWidget {
  const ChatBubble({
    Key? key,
    required this.isMine,
    required this.text,
  }) : super(key: key);

  final bool isMine;
  final String text;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(20),
      height: 50 + 18 * ((text.length / 12) + 1),
      width: 200,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.only(
            bottomRight: isMine ? const Radius.circular(20) : Radius.zero,
            topLeft: const Radius.circular(20),
            topRight: const Radius.circular(20),
            bottomLeft: isMine ? Radius.zero : const Radius.circular(20)),
        color: isMine ? const Color(0xfff0f0f0) : MomoColor.main,
      ),
      child: Center(
        child: Text(
          text,
          style: MomoTextStyle.normal
              .copyWith(color: isMine ? null : MomoColor.white, height: 1.5),
        ),
      ),
    );
  }
}
