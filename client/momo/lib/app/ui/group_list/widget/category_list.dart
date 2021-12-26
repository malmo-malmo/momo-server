import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/provider/group/recommend_group_provider.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/category/category_icon.dart';

class CategoryList extends ConsumerWidget {
  const CategoryList({Key? key, required this.refresh}) : super(key: key);

  final Function refresh;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final checks = ref.watch(groupCategoryCheckStateProvider);

    return SizedBox(
      height: 80,
      child: ListView.separated(
        padding: const EdgeInsets.symmetric(vertical: 18),
        scrollDirection: Axis.horizontal,
        itemBuilder: (context, index) {
          return _CatogoryFilterCard(
            check: checks[index],
            index: index,
            name: index == 0 ? '맞춤추천' : categoryCodeNamePair[index - 1].name,
            refresh: refresh,
          );
        },
        itemCount: 9,
        separatorBuilder: (context, index) => const SizedBox(width: 8),
      ),
    );
  }
}

class _CatogoryFilterCard extends ConsumerWidget {
  const _CatogoryFilterCard({
    Key? key,
    required this.name,
    required this.index,
    required this.check,
    required this.refresh,
  }) : super(key: key);

  final String name;
  final int index;
  final bool check;
  final Function refresh;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return InkWell(
      borderRadius: BorderRadius.circular(22),
      onTap: () {
        ref
            .read(groupCategoryCheckStateProvider.notifier)
            .toggleCategory(index);
        refresh();
      },
      child: Container(
        padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 8),
        height: 44,
        width: 67 + name.length * 13,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(22),
          color: name == '맞춤추천'
              ? (check ? MomoColor.mainLight : MomoColor.checkBackground)
              : (check ? MomoColor.unSelText : MomoColor.checkBackground),
        ),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            name == '맞춤추천'
                ? SvgPicture.asset('assets/icon/home/icon_recommend_28.svg',
                    width: 28)
                : categoryIcon(name, size: 28),
            Text(
              name,
              style: MomoTextStyle.normal.copyWith(
                color: check ? MomoColor.white : null,
              ),
            ),
          ],
        ),
      ),
    );
  }
}
