import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/comment/comment.dart';
import 'package:momo/app/provider/comment/comment_paging_controller_provider.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';
import 'package:momo/app/ui/post_detail/widget/comment_card.dart';

class CommentsList extends ConsumerWidget {
  const CommentsList({
    Key? key,
    required this.postId,
  }) : super(key: key);

  final int postId;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final _pagingController =
        ref.watch(commentPagingControllerProvider(postId));

    return PagedSliverList<int, Comment>.separated(
      pagingController: _pagingController,
      builderDelegate: PagedChildBuilderDelegate<Comment>(
        itemBuilder: (context, item, index) => commentCard(item),
        newPageProgressIndicatorBuilder: (context) => loadingCard(),
        firstPageProgressIndicatorBuilder: (context) => loadingCard(),
        noItemsFoundIndicatorBuilder: (context) => noItemCard(),
      ),
      separatorBuilder: (context, index) => const SizedBox(height: 10),
    );
  }
}
