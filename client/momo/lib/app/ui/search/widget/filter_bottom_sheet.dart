import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/search/search_filter_provider.dart';
import 'package:momo/app/ui/components/button/confirm_button.dart';
import 'package:momo/app/ui/search/widget/category_icon.dart';
import 'package:momo/app/ui/search/widget/location_card.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget filterBottomSheet() {
  return SafeArea(
    child: Consumer(builder: (context, ref, _) {
      final locationState = ref.watch(searchLocationProvider);
      final categoryState = ref.watch(searchCategoryProvider);
      final check = ref.watch(checkSearchFilterProvider);

      return Container(
        height: double.infinity,
        width: double.infinity,
        color: const Color(0xffffffff),
        child: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const SizedBox(height: 42),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 16),
                child: Row(
                  children: [
                    InkWell(
                      onTap: () {
                        ref.read(navigatorProvider).pop();
                      },
                      child: Icon(
                        CupertinoIcons.xmark,
                        size: 21.w,
                      ),
                    ),
                    const SizedBox(width: 14),
                    Text('검색필터', style: MomoTextStyle.defaultStyle),
                  ],
                ),
              ),
              const SizedBox(height: 14),
              Container(
                padding: const EdgeInsets.symmetric(horizontal: 16),
                color: const Color(0xfff7f7fd),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const SizedBox(height: 40),
                    _subTitle('지역'),
                    Wrap(
                      spacing: 10,
                      runSpacing: 10,
                      children: [
                        locationCard(
                            title: '서울', check: locationState[0], index: 0),
                        locationCard(
                            title: '경기', check: locationState[1], index: 1),
                        locationCard(
                            title: '인천', check: locationState[2], index: 2),
                        locationCard(
                            title: '강원', check: locationState[3], index: 3),
                        locationCard(
                            title: '대전/충남/세종',
                            check: locationState[4],
                            index: 4),
                        locationCard(
                            title: '충북', check: locationState[5], index: 5),
                        locationCard(
                            title: '경남/부산/울산',
                            check: locationState[6],
                            index: 6),
                        locationCard(
                            title: '경북/대구', check: locationState[7], index: 7),
                        locationCard(
                            title: '전북', check: locationState[8], index: 8),
                        locationCard(
                            title: '광주/전남', check: locationState[9], index: 9),
                        locationCard(
                            title: '제주', check: locationState[10], index: 10),
                      ],
                    ),
                    _subTitle('활동 카테고리'),
                    Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 6),
                      child: Wrap(
                        spacing: 20,
                        runSpacing: 20,
                        children: [
                          categoryIcon(
                            categoryState[0],
                            '건강',
                            0,
                            selImg: 'assets/icon/category/icon_health_64.svg',
                            unSelImg:
                                'assets/icon/category/icon_healthlightgray_64.svg',
                          ),
                          categoryIcon(
                            categoryState[1],
                            '밥약',
                            1,
                            selImg: 'assets/icon/category/icon_food_64.svg',
                            unSelImg:
                                'assets/icon/category/icon_foodlightgray_64.svg',
                          ),
                          categoryIcon(
                            categoryState[2],
                            '자기관리',
                            2,
                            selImg: 'assets/icon/category/icon_self_64.svg',
                            unSelImg:
                                'assets/icon/category/icon_selflightgray_64.svg',
                          ),
                          categoryIcon(
                            categoryState[3],
                            '생활',
                            3,
                            selImg: 'assets/icon/category/icon_life_64.svg',
                            unSelImg:
                                'assets/icon/category/icon_lifelightgray_64.svg',
                          ),
                          categoryIcon(
                            categoryState[4],
                            '취미',
                            4,
                            selImg: 'assets/icon/category/icon_hobby_64.svg',
                            unSelImg:
                                'assets/icon/category/icon_hobbylightgray_64.svg',
                          ),
                          categoryIcon(
                            categoryState[5],
                            '자산',
                            5,
                            selImg: 'assets/icon/category/icon_stock_64.svg',
                            unSelImg:
                                'assets/icon/category/icon_stockightgray_64.svg',
                          ),
                          categoryIcon(
                            categoryState[6],
                            '힐링',
                            6,
                            selImg: 'assets/icon/category/icon_healing_64.svg',
                            unSelImg:
                                'assets/icon/category/icon_healinglightgray_64.svg',
                          ),
                          categoryIcon(
                            categoryState[7],
                            '취업',
                            7,
                            selImg: 'assets/icon/category/icon_job_64.svg',
                            unSelImg:
                                'assets/icon/category/icon_joblightgray_64.svg',
                          ),
                        ],
                      ),
                    ),
                    const SizedBox(height: 53),
                    ConfirmButton(
                      check: check,
                      buttonText: '완료',
                      isShowDialog: false,
                    ),
                    const SizedBox(height: 36),
                  ],
                ),
              ),
            ],
          ),
        ),
      );
    }),
  );
}

Widget _subTitle(String title) {
  return Padding(
    padding: const EdgeInsets.only(top: 36, bottom: 16),
    child: Text(
      title,
      style: MomoTextStyle.subTitle,
    ),
  );
}
