import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/util/navigation_service.dart';

class MessageButton extends StatelessWidget {
  const MessageButton({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.end,
      children: [
        Consumer(builder: (context, ref, _) {
          return InkWell(
            onTap: () {
              ref
                  .read(navigatorProvider)
                  .navigateTo(routeName: AppRoutes.chatList);
            },
            child: SvgPicture.asset(
              'assets/icon/icon_msg_28.svg',
            ),
          );
        }),
      ],
    );
  }
}
