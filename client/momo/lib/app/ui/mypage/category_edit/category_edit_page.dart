import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/user/category_check_provder.dart';
import 'package:momo/app/provider/user/user_data_provider.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/button/confirm_action_icon.dart';
import 'package:momo/app/ui/components/category/category_column.dart';

class CategoryEditPage extends ConsumerWidget {
  const CategoryEditPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final check = ref.watch(isCheckCategoryProvider);
    final checks = ref.watch(categoryChecksProvider);

    return SafeArea(
      child: Scaffold(
        backgroundColor: MomoColor.flutterWhite,
        appBar: CustomAppBar(
          leadingIcon: CupertinoIcons.back,
          title: '관심 카테고리',
          isAction: true,
          backgroundColor: MomoColor.flutterWhite,
          actionWidget: ConfirmActionIcon(
            check: check,
            isShowDialog: false,
            onTapIcon: () async {
              final _categories = ref
                  .read(categoryChecksProvider.notifier)
                  .makeUpdateCategories();
              await ref
                  .read(userDataProvider.notifier)
                  .updateUserCategories(_categories);
            },
            title: '수정',
          ),
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(vertical: 24, horizontal: 16),
          child: Container(
            padding: EdgeInsets.symmetric(horizontal: 48, vertical: 35.w),
            width: double.infinity,
            height: 442,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(26),
              color: MomoColor.mainWithOpacity,
            ),
            child: Center(
              child: Wrap(
                spacing: 29,
                runSpacing: 38,
                children: [
                  for (int i = 0; i < checks.length; i++)
                    CategoryColumn(
                      check: checks[i],
                      index: i,
                      onTabIcon: ref
                          .read(categoryChecksProvider.notifier)
                          .toggleCategory,
                    ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
