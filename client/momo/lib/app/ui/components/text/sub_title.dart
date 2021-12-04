import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/routes/custom_arg/group_list_arg.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget subTitle({
  required String title,
  required String icon,
  IconData? actionIcon,
  PagingController<int, GroupInfo>? pagingController,
}) {
  return Padding(
    padding: const EdgeInsets.only(top: 43, bottom: 14),
    child: Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Row(
          children: [
            SvgPicture.asset(icon),
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
                    ref.read(navigatorProvider).navigateTo(
                          routeName: AppRoutes.groupList,
                          arguments: GroupListArg(
                            name: title,
                            pagingController: pagingController!,
                          ),
                        );
                  },
                  child: Icon(
                    actionIcon,
                    size: 30,
                  ),
                );
              })
            : const SizedBox(),
      ],
    ),
  );
}
