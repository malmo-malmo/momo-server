import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';

class ChatRoomPage extends StatelessWidget {
  const ChatRoomPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        appBar: CustomAppBar(
          isAction: false,
          leadingIcon: CupertinoIcons.back,
          title: '청계천 달리기&산책',
        ),
        body: Center(
          child: Text('챗룸'),
        ),
      ),
    );
  }
}
