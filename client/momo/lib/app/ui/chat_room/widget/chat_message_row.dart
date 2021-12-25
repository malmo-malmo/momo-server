import 'package:flutter/material.dart';
import 'package:momo/app/ui/chat_room/widget/chat_bubble.dart';
import 'package:momo/app/ui/components/card/profile_avatar.dart';

class ChatMessageRow extends StatelessWidget {
  const ChatMessageRow({
    Key? key,
    required this.isMine,
    required this.text,
  }) : super(key: key);

  final bool isMine;
  final String text;

  @override
  Widget build(BuildContext context) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.end,
      mainAxisAlignment:
          isMine ? MainAxisAlignment.start : MainAxisAlignment.end,
      children: isMine
          ? [
              const ProfileAvatar(
                  img:
                      'https://www.sisaweek.com/news/photo/202107/145526_140545_15.jpg',
                  rad: 18),
              const SizedBox(width: 14),
              ChatBubble(isMine: isMine, text: text),
            ]
          : [
              ChatBubble(isMine: isMine, text: text),
              const SizedBox(width: 14),
              const ProfileAvatar(
                  img:
                      'https://www.sisaweek.com/news/photo/202107/145526_140545_15.jpg',
                  rad: 18),
            ],
    );
  }
}
