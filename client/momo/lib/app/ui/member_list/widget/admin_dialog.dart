import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/user/participant_user.dart';
import 'package:momo/app/ui/components/card/profile_avatar.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget adminDialog({required ParticipantUser participantUser}) {
  return Dialog(
    shape: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(20),
    ),
    child: SizedBox(
      height: 273,
      width: 280,
      child: Padding(
        padding: const EdgeInsets.only(top: 42),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            ProfileAvatar(
                img: participantUser.image ??
                    'https://www.nemopan.com/files/attach/images/6294/004/387/013/63dac7acb2889fd9d34b68a338f9af8c.jpg',
                rad: 24),
            Text(participantUser.nickname, style: MomoTextStyle.normal),
            Text('달성률 ${participantUser.attendanceRate}%',
                style:
                    MomoTextStyle.small.copyWith(color: MomoColor.unSelText)),
            Text(
              '해당 멤버에게 관리자 권한을\n넘기시겠어요?',
              style: MomoTextStyle.defaultStyle.copyWith(height: 1.2),
              textAlign: TextAlign.center,
            ),
            Consumer(
              builder: (context, ref, _) {
                return InkWell(
                  onTap: () {
                    ref.read(navigatorProvider).pop(result: true);
                  },
                  child: Container(
                    decoration: const BoxDecoration(
                      borderRadius: BorderRadius.only(
                        bottomLeft: Radius.circular(20),
                        bottomRight: Radius.circular(20),
                      ),
                      color: MomoColor.main,
                    ),
                    height: 56,
                    width: double.infinity,
                    child: Center(
                      child: Text(
                        '확인',
                        style: MomoTextStyle.defaultStyle.copyWith(
                          color: MomoColor.white,
                        ),
                      ),
                    ),
                  ),
                );
              },
            ),
          ],
        ),
      ),
    ),
  );
}
