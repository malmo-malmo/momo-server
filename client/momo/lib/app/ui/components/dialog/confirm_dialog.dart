import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget confirmDialog({
  required String dialogText,
}) {
  return Dialog(
    insetPadding: const EdgeInsets.all(0.1),
    shape: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(20),
    ),
    child: Container(
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20),
        color: const Color(0xffffffff),
      ),
      height: 148,
      width: 280,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Padding(
            padding: const EdgeInsets.only(top: 40),
            child: Text(
              dialogText,
              style: MomoTextStyle.defaultStyle.copyWith(
                fontWeight: FontWeight.w400,
              ),
            ),
          ),
          Consumer(
            builder: (context, ref, _) {
              return InkWell(
                onTap: () {
                  ref.read(navigatorProvider).pop();
                },
                child: Container(
                  decoration: const BoxDecoration(
                    borderRadius: BorderRadius.only(
                      bottomLeft: Radius.circular(20),
                      bottomRight: Radius.circular(20),
                    ),
                    color: MomoColor.main,
                  ),
                  height: 44,
                  width: double.infinity,
                  child: Center(
                    child: Text(
                      '확인',
                      style: MomoTextStyle.defaultStyle.copyWith(
                        color: MomoColor.white,
                      ),
                    ),
                  ),
                ),
              );
            },
          ),
        ],
      ),
    ),
  );
}

Widget testDialog({
  required String dialogText,
}) {
  return Dialog(
      insetPadding: const EdgeInsets.all(1),
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
      child: Container(
          decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(20),
              color: const Color(0xffffffff)),
          height: 148,
          width: 280,
          child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Padding(
                    padding: const EdgeInsets.only(top: 40),
                    child: Text(dialogText,
                        style: const TextStyle(
                          fontSize: 20,
                          color: Colors.black,
                        ))),
                InkWell(
                    onTap: () {},
                    borderRadius: BorderRadius.circular(20),
                    child: Container(
                        decoration: const BoxDecoration(
                          borderRadius: BorderRadius.only(
                            bottomLeft: Radius.circular(20),
                            bottomRight: Radius.circular(20),
                          ),
                          color: Colors.blue,
                        ),
                        height: 44,
                        width: double.infinity,
                        child: const Center(
                            child: Text('확인',
                                style: TextStyle(
                                  color: MomoColor.white,
                                )))))
              ])));
}
