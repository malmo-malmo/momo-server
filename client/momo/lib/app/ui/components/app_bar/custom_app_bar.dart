import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/util/navigation_service.dart';

class CustomAppBar extends StatelessWidget implements PreferredSizeWidget {
  const CustomAppBar({
    Key? key,
    required this.leadingIcon,
    required this.isAction,
    this.title,
    this.actionWidget,
    this.backgroundColor,
    this.leadingIconColor,
  }) : super(key: key);

  final IconData leadingIcon;
  final bool isAction;
  final String? title;
  final Widget? actionWidget;
  final Color? backgroundColor;
  final Color? leadingIconColor;
  @override
  Widget build(BuildContext context) {
    return AppBar(
        backgroundColor: backgroundColor,
        leading: Consumer(
            builder: (context, ref, _) => InkWell(
                onTap: () => ref.read(navigatorProvider).pop(),
                child: Icon(leadingIcon,
                    color: leadingIconColor ?? MomoColor.black))),
        title: Text(title ?? '', style: MomoTextStyle.defaultStyle),
        actions: [
          isAction
              ? Padding(
                  padding: const EdgeInsets.only(right: 16),
                  child: actionWidget,
                )
              : const SizedBox()
        ]);
  }

  @override
  Size get preferredSize => const Size.fromHeight(56);
}
