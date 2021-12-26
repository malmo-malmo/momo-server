import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/ui/components/card/profile_avatar.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class ChatListCard extends ConsumerWidget {
  const ChatListCard({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return InkWell(
      onTap: () {
        ref.read(navigatorProvider).navigateTo(routeName: AppRoutes.chatRoom);
      },
      child: Material(
        elevation: 2,
        shadowColor: MomoColor.blur,
        borderRadius: BorderRadius.circular(20),
        child: Container(
          decoration: BoxDecoration(borderRadius: BorderRadius.circular(20)),
          padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 20),
          height: 76,
          width: double.infinity,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                mainAxisSize: MainAxisSize.max,
                children: [
                  const ProfileAvatar(
                      img:
                          'https://file2.nocutnews.co.kr/newsroom/image/2018/07/31/20180731101720215498_0_750_937.jpg',
                      rad: 18),
                  const SizedBox(width: 10),
                  Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      const Text('런닝맨', style: MomoTextStyle.normal),
                      const SizedBox(height: 6),
                      SizedBox(
                        width: 200,
                        child: Text(
                          '모임에 참가하고 싶어요!! 모임에 참가하고 싶어요!! 모임에 참가하고 싶어요!! 모임에 참가하고 싶어요!!',
                          style: MomoTextStyle.small.copyWith(
                            color: MomoColor.unSelText,
                          ),
                          overflow: TextOverflow.ellipsis,
                          maxLines: 1,
                        ),
                      )
                    ],
                  ),
                ],
              ),
              Text(
                '오후 9:00',
                style: MomoTextStyle.small.copyWith(color: MomoColor.unSelIcon),
              )
            ],
          ),
        ),
      ),
    );
  }
}
