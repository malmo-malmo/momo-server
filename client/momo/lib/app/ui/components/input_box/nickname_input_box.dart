import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/theme/theme.dart';

class NickNameInputBox extends StatelessWidget {
  const NickNameInputBox({
    Key? key,
    required this.onTabIcon,
    required this.onTextChange,
    required this.userNicknameCheck,
  }) : super(key: key);

  final Function onTabIcon;
  final Function(String value) onTextChange;
  final bool userNicknameCheck;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(16),
          color: MomoColor.flutterWhite),
      height: 44,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          SizedBox(
            width: 200.w,
            child: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 14),
              child: TextFormField(
                onChanged: onTextChange,
                style: MomoTextStyle.defaultStyleR,
                decoration: const InputDecoration(
                  hintText: '닉네임을 입력해주세요',
                ),
              ),
            ),
          ),
          InkWell(
            child: Container(
              height: 32,
              width: 62,
              decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(12),
                  color: userNicknameCheck
                      ? MomoColor.main
                      : MomoColor.unSelButton),
              child: Center(
                child: Text(
                  '중복확인',
                  style: MomoTextStyle.card.copyWith(
                    color: userNicknameCheck
                        ? MomoColor.white
                        : MomoColor.unSelText,
                  ),
                ),
              ),
            ),
            onTap: () {
              onTabIcon();
            },
          ),
        ],
      ),
    );
  }
}
