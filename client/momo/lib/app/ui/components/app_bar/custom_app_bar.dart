import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

AppBar customAppBar({
  required IconData leadingIcon,
  required bool isAction,
  String? title,
  Widget? actionWidget,
}) {
  return AppBar(
    elevation: 0,
    backgroundColor: const Color(0xffffffff),
    toolbarHeight: 56,
    leading: Consumer(builder: (context, ref, _) {
      return InkWell(
        onTap: () {
          ref.read(navigatorProvider).pop();
        },
        child: Icon(
          leadingIcon,
          color: MomoColor.black,
        ),
      );
    }),
    title: Text(title ?? '', style: MomoTextStyle.defaultStyle),
    actions: [
      isAction
          ? Padding(
              padding: const EdgeInsets.only(right: 16),
              child: actionWidget,
            )
          : const SizedBox(),
    ],
  );
}
