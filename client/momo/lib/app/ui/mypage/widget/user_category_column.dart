import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/util/constant.dart';

class UserCategoryColumn extends StatelessWidget {
  const UserCategoryColumn({
    Key? key,
    required this.categoryName,
  }) : super(key: key);

  final String categoryName;

  @override
  Widget build(BuildContext context) {
    final _index = _getCategoryIcon(categoryName);

    return Column(
      children: [
        SvgPicture.asset(
          selIcons[_index],
          width: 64,
        ),
        const SizedBox(height: 8),
        Text(
          categoryName,
          style: MomoTextStyle.normalR,
        ),
      ],
    );
  }

  int _getCategoryIcon(String categoryName) {
    for (int i = 0; i < 8; i++) {
      if (categoryCodeNamePair[i].name == categoryName) {
        return i;
      }
    }
    return -1;
  }
}
