import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/search/widget/recent_meeting_list.dart';
import 'package:momo/app/ui/search/widget/search_box.dart';
import 'package:momo/app/ui/search/widget/searched_word_cards.dart';
import 'package:momo/app/util/theme.dart';

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
                      Icon(
                        CupertinoIcons.chat_bubble_fill,
                        size: 28.w,
                        color: MomoColor.main,
                      ),
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
                  _subTitle(title: '최근검색어', icon: CupertinoIcons.search),
                  const SearchedWordCards(),
                  _subTitle(title: '최근 본 모임', icon: CupertinoIcons.eye),
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

  Widget _subTitle({
    required String title,
    required IconData icon,
  }) {
    return Padding(
      padding: const EdgeInsets.only(top: 43, bottom: 14),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Row(
            children: [
              Icon(
                icon,
                size: 28.sp,
                color: MomoColor.main,
              ),
              SizedBox(width: 10.w),
              Text(
                title,
                style: TextStyle(
                  fontSize: 20.sp,
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
