import 'package:flutter/material.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/ui/components/feed_card.dart';

class FeedList extends StatefulWidget {
  const FeedList({Key? key}) : super(key: key);

  @override
  State<FeedList> createState() => _FeedListState();
}

class _FeedListState extends State<FeedList> {
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
            name: '',
            offline: false,
            participantCnt: 5,
            startDate: ''),
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
    return PagedSliverList<int, GroupInfo>(
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
    );
  }
}
