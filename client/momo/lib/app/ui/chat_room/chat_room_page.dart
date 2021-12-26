import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/chat_room/widget/chat_message_row.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/button/floating_text_button.dart';

class ChatRoomPage extends StatelessWidget {
  const ChatRoomPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        appBar: const CustomAppBar(
          isAction: false,
          leadingIcon: CupertinoIcons.back,
          title: '청계천 달리기&산책',
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: Column(
            children: [
              Expanded(
                child: ListView.separated(
                  padding: const EdgeInsets.symmetric(vertical: 22),
                  itemBuilder: (context, index) {
                    if (index == 0) {
                      return Padding(
                        padding: EdgeInsets.symmetric(horizontal: 43.w),
                        child: Container(
                          height: 56,
                          decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(20),
                            color: MomoColor.chatInfo,
                          ),
                          child: Center(
                              child: Text(
                            '멤버 신청이 왔습니다. 대화를 나눠보세요.',
                            style: MomoTextStyle.small
                                .copyWith(color: MomoColor.white),
                          )),
                        ),
                      );
                    }
                    return ChatMessageRow(
                      isMine: index % 2 == 0,
                      text: '채팅 메세지입니다 ' * (index + 1),
                    );
                  },
                  separatorBuilder: (context, index) =>
                      const SizedBox(height: 20),
                  itemCount: 15,
                ),
              ),
              FloatingTextButton(
                hintText: '메세지를 입력하세요',
                onTapIcon: () {},
                sendMessage: (text) {},
              ),
            ],
          ),
        ),
      ),
    );
  }
}
