import 'package:flutter/material.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/ui/components/feed_card.dart';

class NoticeListView extends StatefulWidget {
  const NoticeListView({Key? key}) : super(key: key);

  @override
  State<NoticeListView> createState() => _NoticeListViewState();
}

class _NoticeListViewState extends State<NoticeListView> {
  final PagingController<int, GroupInfo> _pagingController =
      PagingController(firstPageKey: 0);

  @override
  void initState() {
    _pagingController.addPageRequestListener((pageKey) {
      _fetchPage(pageKey);
    });
    super.initState();
  }

  @override
  void dispose() {
    _pagingController.dispose();
    super.dispose();
  }

  Future<void> _fetchPage(int pageKey) async {
    try {
      await Future.delayed(const Duration(seconds: 1));
      final newItems = List.generate(
        10,
        (index) => GroupInfo(
          id: index,
          imageUrl: '',
          offline: false,
          name: '',
          participantCnt: 5,
          startDate: '',
        ),
      );
      const isLastPage = false;
      if (isLastPage) {
        _pagingController.appendLastPage(newItems);
      } else {
        final nextPageKey = pageKey + newItems.length;
        _pagingController.appendPage(newItems, nextPageKey);
      }
    } catch (error) {
      _pagingController.error = error;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: PagedListView.separated(
        pagingController: _pagingController,
        builderDelegate: PagedChildBuilderDelegate<GroupInfo>(
          itemBuilder: (context, item, index) => FeedCard(
            postId: index,
            profile:
                'https://blog.kakaocdn.net/dn/l2HIx/btqAIQ3UbfL/AaP9zEOiO8zhbj2OAjcPS1/img.jpg',
            text: '',
            comments: 3,
            userName: '',
            title: '',
            date: '2021년 12월 31일 오후 1:00',
          ),
        ),
        separatorBuilder: (context, index) => const SizedBox(height: 14),
      ),
    );
  }
}
