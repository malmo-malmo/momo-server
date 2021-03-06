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
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/button/confirm_button.dart';
import 'package:momo/app/ui/components/category/category_column.dart';
import 'package:momo/app/util/navigation_service.dart';

class FilterBottomSheet extends ConsumerWidget {
  const FilterBottomSheet({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final categoryChecks = ref.watch(searchCategoryStateProvider);
    final cityChecks = ref.watch(searchCityStateProvider);
    final check = ref.watch(searchFilterCheckProvider);

    return SafeArea(
      child: Scaffold(
        floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
        floatingActionButton: Padding(
          padding: const EdgeInsets.only(bottom: 36, left: 16, right: 16),
          child: ConfirmButton(
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
        ),
        body: SingleChildScrollView(
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
                width: double.infinity,
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const _SubTitle(title: '지역'),
                    Wrap(
                      spacing: 10,
                      runSpacing: 10,
                      children: [
                        for (int i = 0; i < cityCodeNamePair.length; i++)
                          _LocationCard(
                            title: cityCodeNamePair[i].name,
                            check: cityChecks[i],
                            index: i,
                            toggle: ref
                                .read(searchCityStateProvider.notifier)
                                .toggle,
                          ),
                      ],
                    ),
                    const _SubTitle(
                      title: '활동 카테고리',
                    ),
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
                    const SizedBox(height: 150),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class _LocationCard extends StatelessWidget {
  const _LocationCard({
    Key? key,
    required this.title,
    required this.check,
    required this.index,
    required this.toggle,
  }) : super(key: key);

  final String title;
  final bool check;
  final int index;
  final void Function(int index) toggle;

  @override
  Widget build(BuildContext context) {
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
}

class _SubTitle extends StatelessWidget {
  const _SubTitle({
    Key? key,
    required this.title,
  }) : super(key: key);

  final String title;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(top: 40, bottom: 16),
      child: Text(
        title,
        style: MomoTextStyle.subTitle,
      ),
    );
  }
}
