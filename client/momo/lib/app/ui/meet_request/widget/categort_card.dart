import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/group/category_check_provider.dart';
import 'package:momo/app/provider/group/group_request_provider.dart';
import 'package:momo/app/util/theme.dart';
import 'package:momo/app/util/values.dart';

Widget categoryCards() {
  return Consumer(builder: (context, ref, _) {
    final checks = ref.watch(newMeetCategoryProvider);
    return Wrap(
      spacing: 8,
      runSpacing: 8,
      children: [
        _categoryCard(checks[0], categories[0], 0),
        _categoryCard(checks[1], categories[1], 1),
        _categoryCard(checks[2], categories[2], 2),
        _categoryCard(checks[3], categories[3], 3),
        _categoryCard(checks[4], categories[4], 4),
        _categoryCard(checks[5], categories[5], 5),
        _categoryCard(checks[6], categories[6], 6),
        _categoryCard(checks[7], categories[7], 7),
      ],
    );
  });
}

Widget _categoryCard(bool check, String title, int index) {
  return Column(
    children: [
      Consumer(builder: (context, ref, _) {
        return InkWell(
          onTap: () {
            ref
                .read(newMeetCategoryStateProvider.notifier)
                .checkCategory(index);
            ref
                .read(newMeetStateProvider.notifier)
                .setCategory(categories[index]);
          },
          child: CircleAvatar(
            radius: 31.w,
            backgroundColor: check ? MomoColor.black : MomoColor.unSelIcon,
          ),
        );
      }),
      const SizedBox(height: 8),
      Text(title),
    ],
  );
}
