import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/svg.dart';
import 'package:momo/app/ui/components/sub_title.dart';
import 'package:momo/app/ui/search/widget/recent_meeting_list.dart';
import 'package:momo/app/ui/search/widget/search_box.dart';
import 'package:momo/app/ui/search/widget/searched_word_cards.dart';

class SearchPage extends StatelessWidget {
  const SearchPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: CustomScrollView(
        slivers: [
          SliverToBoxAdapter(
            child: Padding(
              padding: const EdgeInsets.only(right: 16, left: 16, top: 43),
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
                    '검색',
                    style: TextStyle(
                      fontSize: 28.sp,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  SizedBox(height: 12.h),
                  const SearchBox(),
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
            ),
          ),
          const SliverPadding(
            padding: EdgeInsets.symmetric(horizontal: 16),
            sliver: RecentMeetingList(),
          ),
        ],
      ),
    );
  }
}
