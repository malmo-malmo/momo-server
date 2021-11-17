import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/login/category_check_provder.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/login/widget/agree_button.dart';
import 'package:momo/app/ui/login/widget/title_text.dart';
import 'package:momo/app/util/theme.dart';

class CategoryPage extends ConsumerWidget {
  const CategoryPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final categoryState = ref.watch(categoryProvider);
    final isCheckCategory = ref.watch(isCheckCategoryProvider);

    return SafeArea(
      child: Scaffold(
        body: Padding(
          padding:
              const EdgeInsets.only(top: 91, left: 16, right: 16, bottom: 24),
          child: Column(
            children: [
              titleText('관심 활동 모임  2/3'),
              subTitleText('참여하고 싶은 활동 모임을 선택해주세요'),
              const SizedBox(height: 24),
              Wrap(
                spacing: 16.w,
                runSpacing: 16.h,
                children: [
                  _categoryCard(categoryState[0], '건강', 0),
                  _categoryCard(categoryState[1], '취업', 1),
                  _categoryCard(categoryState[2], '자기계발', 2),
                  _categoryCard(categoryState[3], '힐링', 3),
                  _categoryCard(categoryState[4], '자산', 4),
                  _categoryCard(categoryState[5], '생활', 5),
                  _categoryCard(categoryState[6], '취미', 6),
                  _categoryCard(categoryState[7], '밥약', 7),
                ],
              ),
              const SizedBox(height: 48),
              agreeButton(
                check: isCheckCategory,
                nextPage: AppRoutes.info,
                text: '다음',
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _categoryCard(bool check, String title, int index) {
    return Column(
      children: [
        Consumer(builder: (context, ref, _) {
          return InkWell(
            onTap: () {
              ref.read(categoryStateProvider.notifier).toggleCategory(index);
            },
            child: CircleAvatar(
              radius: 38.w,
              backgroundColor: check ? MomoColor.black : MomoColor.unSelIcon,
            ),
          );
        }),
        const SizedBox(height: 8),
        Text(title),
      ],
    );
  }
}
