import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/routes/custom_arg/group_list_arg.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/util/navigation_service.dart';

class SubTitle extends StatelessWidget {
  const SubTitle({
    Key? key,
    required this.title,
    this.icon,
    this.actionIcon,
    this.favoriteCallBack,
  }) : super(key: key);

  final String title;
  final String? icon;
  final IconData? actionIcon;
  final void Function({
    required int groupId,
    required bool favorite,
  })? favoriteCallBack;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(top: 43, bottom: 14),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          icon == null
              ? Text(
                  title,
                  style: MomoTextStyle.subTitle,
                )
              : Row(
                  children: [
                    SvgPicture.asset(icon!),
                    SizedBox(width: 10.w),
                    Text(
                      title,
                      style: MomoTextStyle.subTitle,
                    ),
                  ],
                ),
          actionIcon != null
              ? Consumer(builder: (context, ref, _) {
                  return InkWell(
                      onTap: () {
                        title != '추천'
                            ? ref.read(navigatorProvider).navigateTo(
                                  routeName: AppRoutes.groupList,
                                  arguments: GroupListArg(
                                    name: title,
                                    likeCallback: favoriteCallBack!,
                                  ),
                                )
                            : ref.read(navigatorProvider).navigateTo(
                                  routeName: AppRoutes.recommendList,
                                  arguments: favoriteCallBack,
                                );
                      },
                      child: Icon(actionIcon, size: 30));
                })
              : const SizedBox(),
        ],
      ),
    );
  }
}
