import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class ConfirmDialog extends StatelessWidget {
  const ConfirmDialog({Key? key, required this.dialogText}) : super(key: key);

  final String dialogText;

  @override
  Widget build(BuildContext context) {
    return Dialog(
        insetPadding: const EdgeInsets.all(0.1),
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
        child: Container(
            decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(20),
                color: const Color(0xffffffff)),
            height: dialogText.contains('\n') ? 172 : 148,
            width: 280,
            child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Padding(
                      padding: const EdgeInsets.only(top: 40),
                      child: Text(dialogText,
                          style: MomoTextStyle.defaultStyle.copyWith(
                              fontWeight: FontWeight.w400, height: 1.4),
                          textAlign: TextAlign.center)),
                  Consumer(
                    builder: (context, ref, _) {
                      return InkWell(
                          onTap: () => ref.read(navigatorProvider).pop(),
                          child: Container(
                              decoration: const BoxDecoration(
                                borderRadius: BorderRadius.only(
                                  bottomLeft: Radius.circular(20),
                                  bottomRight: Radius.circular(20),
                                ),
                                color: MomoColor.main,
                              ),
                              height: 56,
                              width: double.infinity,
                              child: Center(
                                  child: Text('확인',
                                      style: MomoTextStyle.defaultStyle
                                          .copyWith(color: MomoColor.white)))));
                    },
                  )
                ])));
  }
}
