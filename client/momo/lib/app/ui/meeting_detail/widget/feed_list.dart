import 'package:flutter/material.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/meet/meet_feed.dart';
import 'package:momo/app/ui/meeting_detail/widget/feed_card.dart';

class FeedList extends StatefulWidget {
  const FeedList({Key? key}) : super(key: key);

  @override
  State<FeedList> createState() => _FeedListState();
}

class _FeedListState extends State<FeedList> {
  final PagingController<int, MeetFeed> _pagingController =
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
        (index) => MeetFeed(
          id: index,
          title: '안녕하세요',
          userName: '이모모',
          contents: '모모 짱이에요',
          comments: 30,
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
    return PagedSliverList<int, MeetFeed>(
      pagingController: _pagingController,
      builderDelegate: PagedChildBuilderDelegate<MeetFeed>(
        itemBuilder: (context, item, index) => FeedCard(
          img:
              'https://blog.kakaocdn.net/dn/l2HIx/btqAIQ3UbfL/AaP9zEOiO8zhbj2OAjcPS1/img.jpg',
          text: item.contents,
          comments: item.comments,
          userName: item.userName,
          title: item.title,
        ),
      ),
    );
  }
}
