import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/district_result_provider.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';

Widget districtInputBox({
  required Function(String value) setDistrict,
  required String district,
  required String cityCode,
}) {
  return Consumer(builder: (context, ref, _) {
    final response = ref.watch(districtResultProvider(cityCode));

    return response.when(
      error: (error, stackTrace) => errorCard(),
      loading: () => loadingCard(),
      data: (districts) {
        return Container(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          height: 44,
          width: 174,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(16),
            color: const Color(0xffffffff),
          ),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Expanded(
                child: DropdownButton(
                  value: district,
                  underline: const SizedBox(),
                  elevation: 0,
                  isExpanded: true,
                  borderRadius: BorderRadius.circular(8),
                  icon: Transform.rotate(
                    angle: pi * 3 / 2,
                    child: Icon(
                      CupertinoIcons.back,
                      size: 12.w,
                    ),
                  ),
                  items: districts.map((value) {
                    return DropdownMenuItem<String>(
                      value: value,
                      child: Text(value),
                    );
                  }).toList(),
                  onChanged: (String? value) {
                    setDistrict(value!);
                  },
                ),
              ),
            ],
          ),
        );
      },
    );
  });
}
