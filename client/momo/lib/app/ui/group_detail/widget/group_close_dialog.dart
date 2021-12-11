import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget groupCloseDialog() {
  return Dialog(
    shape: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(20),
    ),
    child: Container(
      padding: const EdgeInsets.only(top: 42),
      height: 220,
      width: 280,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(
            '정말 모임에서 종료하시겠어요?',
            style: MomoTextStyle.defaultStyle
                .copyWith(fontWeight: FontWeight.w400),
          ),
          Text(
            '모임을 종료하면 더 이상 기능을\n이용할 수 없어요',
            style: MomoTextStyle.defaultStyle
                .copyWith(fontWeight: FontWeight.w400),
            textAlign: TextAlign.center,
          ),
          SizedBox(
            height: 56,
            width: 280,
            child: Consumer(builder: (context, ref, _) {
              return Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  InkWell(
                    onTap: () async {
                      ref.read(navigatorProvider).pop(result: true);
                    },
                    child: Container(
                      decoration: const BoxDecoration(
                        borderRadius: BorderRadius.only(
                          bottomLeft: Radius.circular(20),
                        ),
                        color: MomoColor.main,
                      ),
                      height: 56,
                      width: 140,
                      child: Center(
                        child: Text(
                          '  네, 나갈래요',
                          style: MomoTextStyle.defaultStyle.copyWith(
                            color: MomoColor.white,
                          ),
                        ),
                      ),
                    ),
                  ),
                  InkWell(
                    onTap: () => ref.read(navigatorProvider).pop(result: false),
                    child: Container(
                      decoration: const BoxDecoration(
                        borderRadius: BorderRadius.only(
                          bottomRight: Radius.circular(20),
                        ),
                        color: MomoColor.unSelButton,
                      ),
                      height: 56,
                      width: 140,
                      child: Center(
                        child: Text(
                          '아니요',
                          style: MomoTextStyle.defaultStyle.copyWith(
                            color: MomoColor.unSelText,
                          ),
                        ),
                      ),
                    ),
                  ),
                ],
              );
            }),
          ),
        ],
      ),
    ),
  );
}
