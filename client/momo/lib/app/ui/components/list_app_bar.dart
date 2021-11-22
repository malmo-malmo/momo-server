import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

AppBar listAppBar() {
  return AppBar(
    elevation: 0,
    backgroundColor: const Color(0xffffffff),
    leading: Consumer(builder: (context, ref, _) {
      return InkWell(
        onTap: () {
          ref.read(navigatorProvider).pop();
        },
        child: const Icon(
          CupertinoIcons.back,
          color: MomoColor.black,
        ),
      );
    }),
    actions: [
      Padding(
        padding: const EdgeInsets.only(right: 16),
        child: SvgPicture.asset(
          'assets/icon/icon_msg_28.svg',
        ),
      ),
    ],
  );
}
