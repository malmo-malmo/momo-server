import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/provider/post/post_list_dto.dart';
import 'package:momo/app/provider/post/post_list_provider.dart';
import 'package:momo/app/ui/components/card/post_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';

class PostListView extends ConsumerStatefulWidget {
  const PostListView({Key? key, required this.groupId}) : super(key: key);

  final int groupId;

  @override
  ConsumerState<PostListView> createState() => _PostListViewState();
}

class _PostListViewState extends ConsumerState<PostListView> {
  final _pagingController = PagingController<int, Post>(firstPageKey: 0);

  @override
  void initState() {
    _pagingController.addPageRequestListener((pageKey) =>
        ref.read(postListProvider(widget.groupId).notifier).getPosts(pageKey));

    super.initState();
  }

  @override
  void dispose() {
    _pagingController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    ref.listen<PostListDto>(
      postListProvider(widget.groupId),
      (previous, next) {
        _pagingController.value = PagingState(
          itemList: next.posts,
          nextPageKey: next.hasNext ? next.nextPage : null,
          error: null,
        );
      },
    );

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
