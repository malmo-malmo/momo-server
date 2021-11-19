import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
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
              const EdgeInsets.only(top: 91, left: 20, right: 20, bottom: 24),
          child: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                titleText('관심 활동 모임  2/3'),
                const SizedBox(height: 20),
                subTitleText('참여하고 싶은 활동 모임을 선택해주세요'),
                const SizedBox(height: 40),
                Container(
                  padding: const EdgeInsets.only(top: 48, right: 35, left: 35),
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
                        _categoryCard(categoryState[0], '건강', 0,
                            selImg: 'assets/icon/icon_health_64.svg',
                            unSelImg:
                                'assets/icon/icon_healthlightgray_64.svg'),
                        _categoryCard(categoryState[1], '밥약', 1,
                            selImg: 'assets/icon/icon_food_64.svg',
                            unSelImg: 'assets/icon/icon_foodlightgray_64.svg'),
                        _categoryCard(categoryState[2], '자기관리', 2,
                            selImg: 'assets/icon/icon_self_64.svg',
                            unSelImg: 'assets/icon/icon_selflightgray_64.svg'),
                        _categoryCard(categoryState[3], '생활', 3,
                            selImg: 'assets/icon/icon_life_64.svg',
                            unSelImg: 'assets/icon/icon_lifelightgray_64.svg'),
                        _categoryCard(categoryState[4], '취미', 4,
                            selImg: 'assets/icon/icon_hobby_64.svg',
                            unSelImg: 'assets/icon/icon_hobbylightgray_64.svg'),
                        _categoryCard(categoryState[5], '자산', 5,
                            selImg: 'assets/icon/icon_stock_64.svg',
                            unSelImg: 'assets/icon/icon_stockightgray_64.svg'),
                        _categoryCard(categoryState[6], '힐링', 6,
                            selImg: 'assets/icon/icon_healing_64.svg',
                            unSelImg:
                                'assets/icon/icon_healinglightgray_64.svg'),
                        _categoryCard(categoryState[7], '취업', 7,
                            selImg: 'assets/icon/icon_job_64.svg',
                            unSelImg: 'assets/icon/icon_joblightgray_64.svg'),
                      ],
                    ),
                  ),
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
      ),
    );
  }

  Widget _categoryCard(
    bool check,
    String title,
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
        const SizedBox(height: 8),
        Text(
          title,
          style: MomoTextStyle.normal,
        ),
      ],
    );
  }
}
