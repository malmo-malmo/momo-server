import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/dialog/confirm_dialog.dart';

class ConfirmActionIcon extends ConsumerWidget {
  const ConfirmActionIcon({
    Key? key,
    required this.check,
    required this.title,
    required this.onTapIcon,
    required this.isShowDialog,
    this.dialogText,
  }) : super(key: key);

  final bool check;
  final String title;
  final Function onTapIcon;
  final bool isShowDialog;
  final String? dialogText;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return InkWell(
      onTap: check
          ? () async {
              await onTapIcon();
              if (isShowDialog) {
                FocusScope.of(context).unfocus();
                await showDialog(
                  context: context,
                  builder: (context) => ConfirmDialog(
                    dialogText: dialogText ?? '완료되었습니다',
                  ),
                );
              }
            }
          : null,
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 10),
        child: Container(
          height: 36,
          width: 64,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(16),
            color: check ? MomoColor.main : MomoColor.checkBackground,
          ),
          child: Center(
            child: Text(
              title,
              style: MomoTextStyle.small.copyWith(
                color: check ? MomoColor.white : MomoColor.unSelIcon,
              ),
            ),
          ),
        ),
      ),
    );
  }
}
