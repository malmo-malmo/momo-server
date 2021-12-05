import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/group_detail/widget/withdraw_dialog.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget groupDetailBottomSheetUser() {
  return Consumer(builder: (context, ref, _) {
    return Container(
      padding: const EdgeInsets.only(top: 18, left: 16, right: 16),
      height: 154,
      width: double.infinity,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Container(
            height: 4,
            width: 52,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(16),
              color: const Color(0xffd1d1d1),
            ),
          ),
          const SizedBox(height: 18),
          InkWell(
            onTap: () async {
              await ref.read(navigatorProvider).navigateTo(
                    routeName: AppRoutes.postRequest,
                    arguments: '게시물',
                  );
              ref.read(navigatorProvider).pop();
            },
            child: sheetTabButtob(
              title: '게시물 작성',
              icon: 'assets/icon/icon_write_28.svg',
            ),
          ),
          InkWell(
            onTap: () async {
              final isWithdraw = await showDialog(
                context: context,
                builder: (context) {
                  return withdrawDialog();
                },
              );
              ref.read(navigatorProvider).pop();
              if (isWithdraw) {
                ref.read(navigatorProvider).pop();
                Fluttertoast.showToast(
                  msg: "탈퇴되셨습니다.",
                  toastLength: Toast.LENGTH_LONG,
                  gravity: ToastGravity.BOTTOM,
                  timeInSecForIosWeb: 1,
                  backgroundColor: MomoColor.main,
                  textColor: Colors.white,
                  fontSize: 16.0,
                );
              }
            },
            child: sheetTabButtob(
              title: '모임 탈퇴',
              icon: 'assets/icon/icon_memberexit_28.svg',
            ),
          ),
        ],
      ),
    );
  });
}

Widget sheetTabButtob({
  required String title,
  required String icon,
}) {
  return SizedBox(
    height: 52,
    child: Row(
      children: [
        SvgPicture.asset(icon),
        const SizedBox(width: 10),
        Text(
          title,
          style: MomoTextStyle.defaultStyle,
        ),
      ],
    ),
  );
}
