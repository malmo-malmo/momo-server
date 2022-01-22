import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/district_result_provider.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/input_box/custom_dropdown_list.dart';
import 'package:momo/app/util/offset/cal_offset.dart';

final _districtInputBoxKey = GlobalKey();

class DistrictInputBox extends ConsumerWidget {
  const DistrictInputBox({
    Key? key,
    required this.district,
    required this.cityCode,
    required this.setDistrict,
    this.backgroundColor,
  }) : super(key: key);

  final String district;
  final String cityCode;
  final Function(String) setDistrict;
  final Color? backgroundColor;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    if (cityCode.isEmpty) {
      return Container(
        padding: const EdgeInsets.symmetric(horizontal: 16),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(16),
          color: backgroundColor ?? MomoColor.flutterWhite,
        ),
        height: 44,
        width: 120,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Text(
              district.isEmpty ? '강남구' : district,
              style: MomoTextStyle.defaultStyleR.copyWith(
                color: district.isEmpty ? MomoColor.unSelIcon : null,
              ),
            ),
            Transform.rotate(
              angle: pi * 3 / 2,
              child: const Icon(
                CupertinoIcons.back,
                size: 20,
              ),
            ),
          ],
        ),
      );
    }
    final districtList = ref.watch(districtResultProvider(cityCode));
    return Container(
      key: _districtInputBoxKey,
      padding: const EdgeInsets.symmetric(horizontal: 16),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(16),
        color: backgroundColor ?? MomoColor.flutterWhite,
      ),
      height: 44,
      width: 120,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          ...districtList.when(
            error: (error, stacktrace) => [const SizedBox()],
            loading: () => [const SizedBox()],
            data: (data) {
              return [
                Text(
                  district.isEmpty ? data.first : district,
                  style: MomoTextStyle.defaultStyleR.copyWith(
                    color: district.isEmpty ? MomoColor.unSelIcon : null,
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
                          values: data,
                          setValue: setDistrict,
                          curValue: district,
                          offset: getParentOffset(_districtInputBoxKey),
                          defaultValue: data.first,
                          width: 120,
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
              ];
            },
          ),
        ],
      ),
    );
  }
}
