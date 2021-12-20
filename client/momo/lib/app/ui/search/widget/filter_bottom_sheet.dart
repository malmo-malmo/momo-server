import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/provider/city_result_provider.dart';
import 'package:momo/app/provider/search/search_filter_provider.dart';
import 'package:momo/app/provider/search/search_request_filter_provider.dart';
import 'package:momo/app/provider/search/search_result_paiging_controller.dart';
import 'package:momo/app/provider/search/search_result_provider.dart';
import 'package:momo/app/ui/components/button/confirm_button.dart';
import 'package:momo/app/ui/components/category/category_column.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

Widget filterBottomSheet() {
  return SafeArea(
    child: Consumer(builder: (context, ref, _) {
      final categoryChecks = ref.watch(searchCategoryProvider);
      final cityChecks = ref.watch(searchCityProvider);
      final check = ref.watch(searchFilterCheckProvider);

      return Container(
        height: double.infinity,
        width: double.infinity,
        color: const Color(0xffffffff),
        child: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const SizedBox(height: 50),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 16),
                child: Row(
                  children: [
                    InkWell(
                        onTap: () => ref.read(navigatorProvider).pop(),
                        child: Icon(CupertinoIcons.xmark, size: 21.w)),
                    const SizedBox(width: 14),
                    const Text('검색필터', style: MomoTextStyle.defaultStyle),
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
                    _subTitle('지역'),
                    Wrap(
                      spacing: 10,
                      runSpacing: 10,
                      children: [
                        for (int i = 0; i < cityCodeNamePair.length; i++)
                          _locationCard(
                            title: cityCodeNamePair[i].name,
                            check: cityChecks[i],
                            index: i,
                            toggle: ref
                                .read(searchCityStateProvider.notifier)
                                .toggle,
                          ),
                      ],
                    ),
                    _subTitle('활동 카테고리'),
                    const SizedBox(height: 16),
                    Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 6),
                      child: Wrap(
                        spacing: 20,
                        runSpacing: 20,
                        children: [
                          for (int i = 0; i < categoryCodeNamePair.length; i++)
                            CategoryColumn(
                              check: categoryChecks[i],
                              index: i,
                              onTabIcon: ref
                                  .read(searchCategoryStateProvider.notifier)
                                  .toggle,
                              spaceHeight: 14,
                            ),
                        ],
                      ),
                    ),
                    const SizedBox(height: 53),
                    ConfirmButton(
                      check: check,
                      onPressButton: () async {
                        ref
                            .read(categoryFilterStateProvider.notifier)
                            .checkFilter(categoryChecks);
                        ref
                            .read(cityFilterStateProvider.notifier)
                            .checkFilter(cityChecks);
                        ref.read(isShowResultStateProvider.state).state = true;
                        ref.read(navigatorProvider).pop();
                        ref.read(searchReulstPagingController).refresh();
                      },
                      buttonText: '완료',
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

Widget _locationCard({
  required String title,
  required bool check,
  required int index,
  required void Function(int index) toggle,
}) {
  return Material(
    elevation: 2,
    shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
    child: InkWell(
      onTap: () => toggle(index),
      child: Container(
        height: 41,
        width: title.length * 7 + 70,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          color: check ? MomoColor.main : MomoColor.white,
        ),
        child: Center(
          child: Text(
            title,
            style: MomoTextStyle.defaultStyle.copyWith(
              color: check ? MomoColor.white : MomoColor.black,
              fontWeight: FontWeight.w400,
            ),
          ),
        ),
      ),
    ),
  );
}

Widget _subTitle(String title) {
  return Padding(
    padding: const EdgeInsets.only(top: 40, bottom: 16),
    child: Text(
      title,
      style: MomoTextStyle.subTitle,
    ),
  );
}
