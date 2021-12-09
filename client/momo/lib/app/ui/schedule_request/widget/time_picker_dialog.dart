import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget timePickerDialog() {
  bool isAm = true;
  int hour = 0;
  int minute = 0;

  return Consumer(builder: (context, ref, _) {
    return Dialog(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ),
      child: Container(
        height: 221,
        width: 280,
        padding: const EdgeInsets.only(top: 48),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Container(
              height: 100,
              padding: const EdgeInsets.symmetric(horizontal: 56),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  _custromPicker(
                    children: [
                      Center(child: Text('오전', style: MomoTextStyle.subTitle)),
                      Center(child: Text('오후', style: MomoTextStyle.subTitle)),
                    ],
                    onSelect: (index) => isAm = index == 0 ? true : false,
                  ),
                  Row(
                    children: [
                      _custromPicker(
                        children: List.generate(
                            12,
                            (index) => Center(
                                child: Text('$index',
                                    style: MomoTextStyle.subTitle))),
                        onSelect: (index) => hour = index,
                      ),
                      Text(
                        ':',
                        style: MomoTextStyle.subTitle,
                      ),
                      _custromPicker(
                        children: List.generate(
                            12,
                            (index) => Center(
                                child: Text(
                                    index <= 1
                                        ? '0${index * 5}'
                                        : '${index * 5}',
                                    style: MomoTextStyle.subTitle))),
                        onSelect: (index) => minute = index * 5,
                      ),
                    ],
                  ),
                ],
              ),
            ),
            const SizedBox(height: 16),
            InkWell(
              onTap: () {
                ref.read(navigatorProvider).pop(result: [hour, minute, isAm]);
              },
              child: Container(
                height: 56,
                decoration: const BoxDecoration(
                  color: MomoColor.main,
                  borderRadius: BorderRadius.only(
                    bottomRight: Radius.circular(20),
                    bottomLeft: Radius.circular(20),
                  ),
                ),
                child: Center(
                  child: Text(
                    '확인',
                    style: MomoTextStyle.defaultStyle.copyWith(
                      color: MomoColor.white,
                    ),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  });
}

Widget _custromPicker({
  required List<Widget> children,
  required void Function(int index) onSelect,
}) {
  return SizedBox(
    height: 90,
    width: 40,
    child: CupertinoPicker(
      selectionOverlay: const SizedBox(),
      squeeze: 1,
      diameterRatio: 3,
      itemExtent: 30,
      onSelectedItemChanged: onSelect,
      children: children,
    ),
  );
}
