import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/provider/group/category_list_provider.dart';
import 'package:momo/app/ui/components/category/category_icon.dart';
import 'package:momo/app/util/theme.dart';

class CategoryList extends ConsumerWidget {
  const CategoryList({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final checks = ref.watch(groupCategoryStateProvider);

    return SizedBox(
      height: 80,
      child: ListView.separated(
        padding: const EdgeInsets.symmetric(vertical: 18),
        scrollDirection: Axis.horizontal,
        itemBuilder: (context, index) {
          if (index == 0) {
            return _categoryFilterCard('맞춤추천', index, checks[index]);
          }
          return _categoryFilterCard(
              categoryCodeNamePair[index - 1].name, index, checks[index]);
        },
        itemCount: 9,
        separatorBuilder: (context, index) => const SizedBox(width: 8),
      ),
    );
  }

  Widget _categoryFilterCard(String name, int index, bool check) {
    return Consumer(builder: (context, ref, _) {
      return InkWell(
        borderRadius: BorderRadius.circular(22),
        onTap: () {
          ref
              .read(groupCategoryCheckStateProvider.notifier)
              .toggleCategory(index);
        },
        child: Container(
          padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 8),
          height: 44,
          width: 67 + name.length * 13,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(22),
            color: name == '맞춤추천'
                ? (check ? const Color(0xffbca9f7) : const Color(0xfff0f0f0))
                : (check ? const Color(0xff616161) : const Color(0xfff0f0f0)),
          ),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            children: [
              name == '맞춤추천'
                  ? SvgPicture.asset(
                      'assets/icon/home/icon_recommend_28.svg',
                      width: 28,
                    )
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
    });
  }
}
