import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/chat_list/widget/chat_list_card.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';

class ChatListPage extends StatelessWidget {
  const ChatListPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: MomoColor.backgroundColor,
        appBar: const CustomAppBar(
          isAction: false,
          leadingIcon: CupertinoIcons.back,
          title: '쪽지함',
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: ListView.separated(
            padding: const EdgeInsets.symmetric(vertical: 22),
            itemBuilder: (context, index) => const ChatListCard(),
            itemCount: 30,
            separatorBuilder: (context, index) => const SizedBox(height: 14),
          ),
        ),
      ),
    );
  }
}
