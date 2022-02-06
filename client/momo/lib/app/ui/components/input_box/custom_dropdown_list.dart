import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:momo/app/theme/theme.dart';

class CustomDropDownList extends StatelessWidget {
  const CustomDropDownList({
    Key? key,
    required this.values,
    required this.setValue,
    required this.offset,
    required this.curValue,
    required this.defaultValue,
    this.backgroundColor,
    this.width,
  }) : super(key: key);

  final List<String> values;
  final String curValue;
  final Function(String value) setValue;
  final double? width;
  final Offset offset;
  final String defaultValue;
  final Color? backgroundColor;

  @override
  Widget build(BuildContext context) {
    return Material(
      color: Colors.transparent,
      child: SizedBox(
        child: Stack(
          children: [
            Positioned(
              left: 0,
              right: 0,
              top: 0,
              bottom: 0,
              child: GestureDetector(
                onTap: () => Navigator.pop(context),
                child: Container(
                  color: Colors.transparent,
                ),
              ),
            ),
            Positioned(
              left: offset.dx,
              top: offset.dy - 12,
              child: Container(
                padding: const EdgeInsets.symmetric(vertical: 16),
                height: 184,
                width: width ?? 156,
                decoration: BoxDecoration(
                  borderRadius: const BorderRadius.only(
                    bottomLeft: Radius.circular(16),
                    bottomRight: Radius.circular(16),
                  ),
                  color: backgroundColor ?? Colors.white,
                ),
                child: Scrollbar(
                  isAlwaysShown: true,
                  child: ListView.builder(
                    padding: const EdgeInsets.only(top: 8, bottom: 8),
                    itemBuilder: (context, index) {
                      return InkWell(
                        onTap: () {
                          Navigator.pop(context);
                          setValue(values[index]);
                        },
                        child: Container(
                          color: values[index] == curValue
                              ? MomoColor.main
                              : backgroundColor ?? Colors.white,
                          height: 42,
                          child: Center(
                            child: Text(
                              values[index],
                              style: MomoTextStyle.defaultStyleR.copyWith(
                                color: values[index] == curValue
                                    ? MomoColor.white
                                    : null,
                              ),
                            ),
                          ),
                        ),
                      );
                    },
                    itemCount: values.length,
                  ),
                ),
              ),
            ),
            Positioned(
              left: offset.dx,
              top: offset.dy - 44,
              child: Container(
                height: 44,
                width: width ?? 156,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(16),
                  color: MomoColor.main,
                ),
                child: Center(
                  child: Container(
                    padding: const EdgeInsets.symmetric(horizontal: 15),
                    height: 42,
                    width: width == null ? 154 : width! - 2,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(15),
                      color: backgroundColor ?? Colors.white,
                    ),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text(
                          curValue.isEmpty ? defaultValue : curValue,
                          style: MomoTextStyle.defaultStyleR.copyWith(
                            color:
                                curValue.isEmpty ? MomoColor.unSelIcon : null,
                          ),
                        ),
                        Transform.rotate(
                          angle: pi * 3 / 2,
                          child: const Icon(
                            CupertinoIcons.back,
                            size: 20,
                          ),
                        )
                      ],
                    ),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
