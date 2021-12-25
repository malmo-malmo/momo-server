import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/comment/comment.dart';
import 'package:momo/app/model/comment/comment_list_dto.dart';
import 'package:momo/app/provider/comment/comment_list_provider.dart';
import 'package:momo/app/provider/post/post_detail_provider.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';
import 'package:momo/app/ui/post_detail/widget/comment_card.dart';

class CommentsList extends ConsumerStatefulWidget {
  const CommentsList({Key? key, required this.postId}) : super(key: key);

  final int postId;

  @override
  ConsumerState<CommentsList> createState() => _CommentsListState();
}

class _CommentsListState extends ConsumerState<CommentsList> {
  final _pagingController = PagingController<int, Comment>(firstPageKey: 0);

  @override
  void initState() {
    _pagingController.addPageRequestListener((pageKey) => ref
        .read(commentListProvider(widget.postId).notifier)
        .getComments(pageKey));

    super.initState();
  }

  @override
  void dispose() {
    _pagingController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    ref.listen<CommentListDto>(commentListProvider(widget.postId),
        (previous, next) {
      _pagingController.value = PagingState(
          itemList: next.comments,
          nextPageKey: next.hasNext ? next.nextPage : null,
          error: null);
      ref.watch(postDetailCommentCntStateProvider.state).state =
          next.commentCnt;
    });

    return PagedSliverList<int, Comment>.separated(
      pagingController: _pagingController,
      builderDelegate: PagedChildBuilderDelegate<Comment>(
        itemBuilder: (context, item, index) => CommentCard(comment: item),
        newPageProgressIndicatorBuilder: (context) => const LoadingCard(),
        firstPageProgressIndicatorBuilder: (context) => const LoadingCard(),
        noItemsFoundIndicatorBuilder: (context) => const NoItemCard(),
      ),
      separatorBuilder: (context, index) => const SizedBox(height: 10),
    );
  }
}
