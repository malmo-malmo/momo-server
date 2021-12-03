import 'package:flutter/material.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/comment/comment.dart';
import 'package:momo/app/ui/post_detail/widget/comment_card.dart';

class CommentsList extends StatefulWidget {
  const CommentsList({Key? key}) : super(key: key);

  @override
  State<CommentsList> createState() => _CommentsListState();
}

class _CommentsListState extends State<CommentsList> {
  final PagingController<int, Comment> _pagingController =
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
        (index) => Comment(
          id: index,
          contents: '모모 너무 좋아요',
          authorImage:
              'https://biz.chosun.com/resizer/kh_pcdsIH0PJWIXenLBD4Oi94Wg=/464x0/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosunbiz/HAXYB5XB4CCHXUB6VQVALOZFVY.jpg',
          authorNickname: '김모모',
          createdDate: '2021-12-31',
          authorId: index,
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
    return PagedSliverList<int, Comment>.separated(
      pagingController: _pagingController,
      builderDelegate: PagedChildBuilderDelegate<Comment>(
        itemBuilder: (context, item, index) => commentCard(
          name: item.authorNickname,
          profile: item.authorImage,
          text: item.contents,
        ),
      ),
      separatorBuilder: (context, index) => const SizedBox(height: 10),
    );
  }
}
