import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/ui/components/dialog/confirm_dialog.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget confirmActionIcon({
  required bool check,
  required String title,
  required Function onTapIcon,
  required bool isShowDialog,
  String? dialogText,
  Object? result,
}) {
  return Consumer(builder: (context, ref, _) {
    return InkWell(
      onTap: check
          ? () async {
              onTapIcon();
              if (isShowDialog) {
                await showDialog(
                    context: context,
                    builder: (context) =>
                        confirmDialog(dialogText: dialogText ?? '완료되었습니다'));
              }
              ref.read(navigatorProvider).pop(result: result);
            }
          : null,
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 10),
        child: Container(
          height: 36,
          width: 64,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(16),
            color: check ? MomoColor.main : const Color(0xfff0f0f0),
          ),
          child: Center(
              child: Text(
            title,
            style: MomoTextStyle.small.copyWith(
              color: check ? MomoColor.white : MomoColor.unSelIcon,
            ),
          )),
        ),
      ),
    );
  });
}
