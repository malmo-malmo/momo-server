import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/provider/post/post_paging_controller_provider.dart';
import 'package:momo/app/ui/components/card/post_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';

class PostListView extends ConsumerWidget {
  const PostListView({Key? key, required this.groupId}) : super(key: key);

  final int groupId;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final _pagingController = ref.watch(postPaigingControllerProvider(groupId));

    return PagedSliverList<int, Post>(
      pagingController: _pagingController,
      builderDelegate: PagedChildBuilderDelegate<Post>(
        itemBuilder: (context, item, index) => PostCard(post: item),
        newPageProgressIndicatorBuilder: (context) => const LoadingCard(),
        firstPageProgressIndicatorBuilder: (context) => const LoadingCard(),
        noItemsFoundIndicatorBuilder: (context) => const NoItemCard(),
      ),
    );
  }
}
