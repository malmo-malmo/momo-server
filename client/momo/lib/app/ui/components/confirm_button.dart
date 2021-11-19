import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class ConfirmButton extends ConsumerWidget {
  const ConfirmButton({
    Key? key,
    required this.check,
    required this.buttonText,
    this.dialogText,
    required this.isShowDialog,
  }) : super(key: key);

  final bool check;
  final String buttonText;
  final String? dialogText;
  final bool isShowDialog;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return SizedBox(
      height: 57,
      width: double.infinity,
      child: ElevatedButton(
        style: ButtonStyle(
          shape: MaterialStateProperty.all<RoundedRectangleBorder>(
            RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(32),
            ),
          ),
          backgroundColor: MaterialStateProperty.resolveWith(
            (states) {
              if (states.contains(MaterialState.disabled)) {
                return const Color(0xfff2f2f2);
              }
              return const Color(0xff000000);
            },
          ),
        ),
        onPressed: check
            ? () async {
                if (isShowDialog) {
                  await showDialog(
                    context: context,
                    builder: (context) {
                      return _confirmDialog(dialogText!);
                    },
                  );
                }

                ref.read(navigatorProvider).pop();
              }
            : null,
        child: Text(
          buttonText,
          style: TextStyle(
            fontSize: 16.sp,
          ),
        ),
      ),
    );
  }

  Widget _confirmDialog(String title) {
    return Dialog(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ),
      child: Container(
        padding:
            const EdgeInsets.only(right: 24, left: 24, top: 48, bottom: 24),
        height: 162,
        width: 294,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(
              title,
              style: TextStyle(color: MomoColor.black, fontSize: 16.sp),
            ),
            SizedBox(
              height: 44,
              width: 241,
              child: Consumer(builder: (context, ref, _) {
                return ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    primary: MomoColor.main,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(20),
                    ),
                  ),
                  onPressed: () {
                    ref.read(navigatorProvider).pop();
                  },
                  child: Text(
                    '확인',
                    style: TextStyle(
                      fontSize: 16.sp,
                    ),
                  ),
                );
              }),
            ),
          ],
        ),
      ),
    );
  }
}
