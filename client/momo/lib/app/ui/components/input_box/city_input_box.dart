import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:momo/app/provider/city_result_provider.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/input_box/custom_dropdown_list.dart';
import 'package:momo/app/util/offset/cal_offset.dart';

final _cityInputBoxKey = GlobalKey();

class CityInputBox extends StatelessWidget {
  CityInputBox({
    Key? key,
    required this.setCity,
    required this.city,
    this.backgroundColor,
  }) : super(key: key);

  final Function(String value) setCity;
  final String city;
  final Color? backgroundColor;

  final _valueList = cityCodeNamePair.map((e) => e.name).toList();

  @override
  Widget build(BuildContext context) {
    return Container(
      key: _cityInputBoxKey,
      padding: const EdgeInsets.symmetric(horizontal: 16),
      height: 44,
      width: 156,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(16),
        color: backgroundColor ?? MomoColor.flutterWhite,
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(
            city.isEmpty ? '서울' : city,
            style: MomoTextStyle.defaultStyleR.copyWith(
              color: city.isEmpty ? MomoColor.unSelIcon : null,
            ),
          ),
          InkWell(
            onTap: () {
              Navigator.push(
                context,
                PageRouteBuilder(
                  opaque: false,
                  pageBuilder: (context, animation, secondaryAnimation) =>
                      CustomDropDownList(
                    values: _valueList,
                    setValue: setCity,
                    curValue: city,
                    offset: getParentOffset(_cityInputBoxKey),
                    defaultValue: '서울',
                  ),
                  transitionsBuilder:
                      (context, animation, secondaryAnimation, child) =>
                          FadeTransition(
                    opacity: animation,
                    child: child,
                  ),
                ),
              );
            },
            child: Transform.rotate(
              angle: pi * 3 / 2,
              child: const Icon(
                CupertinoIcons.back,
                size: 20,
              ),
            ),
          )
        ],
      ),
    );
  }
}
