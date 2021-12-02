import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/group/category_check_provider.dart';
import 'package:momo/app/provider/group/group_request_provider.dart';
import 'package:momo/app/provider/user/category_result_provider.dart';
import 'package:momo/app/util/theme.dart';

Widget categoryCards() {
  return Consumer(builder: (context, ref, _) {
    final checks = ref.watch(newMeetCategoryProvider);
    return Wrap(
      spacing: 8,
      runSpacing: 8,
      children: [
        _categoryCard(checks[0], categoryCodeNamePair[0].name, 0),
        _categoryCard(checks[1], categoryCodeNamePair[1].name, 1),
        _categoryCard(checks[2], categoryCodeNamePair[2].name, 2),
        _categoryCard(checks[3], categoryCodeNamePair[3].name, 3),
        _categoryCard(checks[4], categoryCodeNamePair[4].name, 4),
        _categoryCard(checks[5], categoryCodeNamePair[5].name, 5),
        _categoryCard(checks[6], categoryCodeNamePair[6].name, 6),
        _categoryCard(checks[7], categoryCodeNamePair[7].name, 7),
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
                .read(groupRequestStateProvider.notifier)
                .setCategory(categoryCodeNamePair[index].name);
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
