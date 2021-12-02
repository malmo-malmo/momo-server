import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/user/category_check_provder.dart';
import 'package:momo/app/provider/user/category_result_provider.dart';
import 'package:momo/app/routes/routes.dart';
import 'package:momo/app/ui/login/widget/agree_button.dart';
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
                        _categoryCard(categoryState[0], 0,
                            selImg: 'assets/icon/category/icon_health_64.svg',
                            unSelImg:
                                'assets/icon/category/icon_healthlightgray_64.svg'),
                        _categoryCard(categoryState[1], 1,
                            selImg: 'assets/icon/category/icon_food_64.svg',
                            unSelImg:
                                'assets/icon/category/icon_foodlightgray_64.svg'),
                        _categoryCard(categoryState[2], 2,
                            selImg: 'assets/icon/category/icon_self_64.svg',
                            unSelImg:
                                'assets/icon/category/icon_selflightgray_64.svg'),
                        _categoryCard(categoryState[3], 3,
                            selImg: 'assets/icon/category/icon_life_64.svg',
                            unSelImg:
                                'assets/icon/category/icon_lifelightgray_64.svg'),
                        _categoryCard(categoryState[4], 4,
                            selImg: 'assets/icon/category/icon_hobby_64.svg',
                            unSelImg:
                                'assets/icon/category/icon_hobbylightgray_64.svg'),
                        _categoryCard(categoryState[5], 5,
                            selImg: 'assets/icon/category/icon_stock_64.svg',
                            unSelImg:
                                'assets/icon/category/icon_stockightgray_64.svg'),
                        _categoryCard(categoryState[6], 6,
                            selImg: 'assets/icon/category/icon_healing_64.svg',
                            unSelImg:
                                'assets/icon/category/icon_healinglightgray_64.svg'),
                        _categoryCard(categoryState[7], 7,
                            selImg: 'assets/icon/category/icon_job_64.svg',
                            unSelImg:
                                'assets/icon/category/icon_joblightgray_64.svg'),
                      ],
                    ),
                  ),
                ),
                const SizedBox(height: 64),
                agreeButton(
                  check: isCheckCategory,
                  text: '다음',
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

  Widget _categoryCard(
    bool check,
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
              ref.read(categoryStateProvider.notifier).toggleCategory(index);
            },
            child: SvgPicture.asset(
              check ? selImg : unSelImg,
            ),
          );
        }),
        const SizedBox(height: 10),
        Text(
          categoryCodeNamePair[index].name,
          style: MomoTextStyle.normal.copyWith(
            fontWeight: FontWeight.w400,
          ),
        ),
      ],
    );
  }
}
