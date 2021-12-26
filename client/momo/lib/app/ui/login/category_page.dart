import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/user/category_check_provder.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/ui/components/button/confirm_button.dart';
import 'package:momo/app/ui/components/category/category_column.dart';
import 'package:momo/app/ui/login/widget/title_text.dart';
import 'package:momo/app/util/navigation_service.dart';
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
          padding: const EdgeInsets.symmetric(horizontal: 20),
          child: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const SizedBox(height: 45),
                InkWell(
                  onTap: () {
                    ref.read(navigatorProvider).pop();
                  },
                  child: Icon(
                    CupertinoIcons.back,
                    color: MomoColor.black,
                    size: 24.w,
                  ),
                ),
                const SizedBox(height: 25),
                titleText('관심 활동 모임  2/3'),
                const SizedBox(height: 20),
                subTitleText('참여하고 싶은 활동 모임을 선택해주세요.'),
                const SizedBox(height: 40),
                Container(
                  padding: EdgeInsets.symmetric(horizontal: 48, vertical: 35.w),
                  height: 442.h,
                  width: double.infinity,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(26),
                    color: const Color(0xffffffff),
                  ),
                  child: Center(
                    child: Wrap(
                      spacing: 29.w,
                      runSpacing: 64.h,
                      children: [
                        for (int i = 0; i < categoryState.length; i++)
                          CategoryColumn(
                            check: categoryState[i],
                            index: i,
                            onTabIcon: ref
                                .read(categoryStateProvider.notifier)
                                .toggleCategory,
                          ),
                      ],
                    ),
                  ),
                ),
                const SizedBox(height: 64),
                ConfirmButton(
                  check: isCheckCategory,
                  buttonText: '다음',
                  onPressButton: () async {
                    await ref
                        .read(categoryStateProvider.notifier)
                        .updateUserCategories();
                    ref
                        .read(navigatorProvider)
                        .navigateTo(routeName: AppRoutes.info);
                  },
                ),
                const SizedBox(height: 36),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
