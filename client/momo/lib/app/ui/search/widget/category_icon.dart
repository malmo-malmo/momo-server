import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/search/search_filter_provider.dart';
import 'package:momo/app/util/theme.dart';

Widget categoryIcon(
  bool check,
  String title,
  int index, {
  required String selImg,
  required String unSelImg,
}) {
  return Column(
    children: [
      Consumer(builder: (context, ref, _) {
        return InkWell(
          borderRadius: BorderRadius.circular(32),
          onTap: () {
            ref
                .read(searchCategoryStateProvider.notifier)
                .toggleCategory(index);
          },
          child: SvgPicture.asset(
            check ? selImg : unSelImg,
          ),
        );
      }),
      const SizedBox(height: 8),
      Text(
        title,
        style: MomoTextStyle.normal,
      ),
    ],
  );
}
