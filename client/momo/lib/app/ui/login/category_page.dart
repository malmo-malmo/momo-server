import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/user/category_check_provder.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/button/confirm_button.dart';
import 'package:momo/app/ui/components/category/category_column.dart';
import 'package:momo/app/ui/login/widget/title_text.dart';
import 'package:momo/app/util/navigation_service.dart';

class CategoryPage extends ConsumerWidget {
  const CategoryPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final categoryState = ref.watch(categoryChecksProvider);
    final isCheckCategory = ref.watch(isCheckCategoryProvider);

    return SafeArea(
      child: Scaffold(
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 20),
          child: LayoutBuilder(builder: (context, constraint) {
            return SingleChildScrollView(
              child: ConstrainedBox(
                constraints: BoxConstraints(minHeight: constraint.maxHeight),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        const SizedBox(height: 45),
                        InkWell(
                          onTap: () => ref.read(navigatorProvider).pop(),
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
                          padding: EdgeInsets.symmetric(
                              horizontal: 48, vertical: 35.w),
                          height: 442,
                          width: double.infinity,
                          decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(26),
                            color: MomoColor.flutterWhite,
                          ),
                          child: Center(
                            child: Wrap(
                              spacing: 29,
                              runSpacing: 38,
                              children: [
                                for (int i = 0; i < categoryState.length; i++)
                                  CategoryColumn(
                                    check: categoryState[i],
                                    index: i,
                                    onTabIcon: ref
                                        .read(categoryChecksProvider.notifier)
                                        .toggleCategory,
                                  ),
                              ],
                            ),
                          ),
                        ),
                        const SizedBox(height: 64),
                      ],
                    ),
                    Padding(
                      padding: const EdgeInsets.only(bottom: 36),
                      child: ConfirmButton(
                        check: isCheckCategory,
                        buttonText: '다음',
                        onPressButton: () async {
                          await ref
                              .read(categoryChecksProvider.notifier)
                              .updateUserCategories();
                          ref
                              .read(navigatorProvider)
                              .navigateTo(routeName: AppRoutes.info);
                        },
                      ),
                    ),
                  ],
                ),
              ),
            );
          }),
        ),
      ),
    );
  }
}
