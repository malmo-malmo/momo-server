import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/svg.dart';
import 'package:momo/app/provider/search/search_result_provider.dart';
import 'package:momo/app/ui/components/text/sub_title.dart';
import 'package:momo/app/ui/search/widget/recent_meeting_list.dart';
import 'package:momo/app/ui/search/widget/search_box.dart';
import 'package:momo/app/ui/search/widget/search_result_list.dart';
import 'package:momo/app/ui/search/widget/searched_word_cards.dart';
import 'package:momo/app/util/theme.dart';

class SearchPage extends ConsumerWidget {
  const SearchPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final isSearch = ref.watch(isShowResultProvider);

    return SafeArea(
      child: Padding(
        padding: const EdgeInsets.only(right: 16, left: 16, top: 43),
        child: CustomScrollView(
          slivers: [
            SliverToBoxAdapter(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.end,
                    children: [
                      SvgPicture.asset('assets/icon/icon_msg_28.svg'),
                    ],
                  ),
                  SizedBox(height: 12.h),
                  Text(
                    !isSearch ? '검색' : '검색결과',
                    style: MomoTextStyle.mainTitle,
                  ),
                  SizedBox(height: 12.h),
                  SearchBox(
                    onSearch:
                        ref.read(isShowResultStateProvider.notifier).search,
                  ),
                ],
              ),
            ),
            !isSearch
                ? SliverToBoxAdapter(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        subTitle(
                          title: '최근검색어',
                          icon: 'assets/icon/search/icon_recentsearch_28.svg',
                        ),
                        const SearchedWordCards(),
                        subTitle(
                          title: '최근 본 모임',
                          icon: 'assets/icon/search/icon_recentsee_28.svg',
                        ),
                      ],
                    ),
                  )
                : const SliverPadding(
                    padding: EdgeInsets.only(top: 30),
                    sliver: SearchResultList(),
                  ),
            !isSearch ? const RecentMeetingList() : const SliverToBoxAdapter(),
          ],
        ),
      ),
    );
  }
}
