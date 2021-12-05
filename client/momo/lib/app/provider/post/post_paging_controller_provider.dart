import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/repository/post_repository.dart';
import 'package:momo/app/util/constant.dart';

final noticePaigingControllerProvider = Provider.family
    .autoDispose<PagingController<int, Post>, int>((ref, groupId) {
  final _pagingController = PagingController<int, Post>(firstPageKey: 0);
  final repository = ref.watch(postRepositoryProvider);

  _pagingController.addPageRequestListener((pageKey) {
    _fetchPage(
      pageKey: pageKey,
      groupId: groupId,
      getPosts: repository.getNotices,
      pagingController: _pagingController,
    );
  });

  ref.onDispose(() => _pagingController.dispose());
  return _pagingController;
});

final postPaigingControllerProvider = Provider.family
    .autoDispose<PagingController<int, Post>, int>((ref, groupId) {
  final _pagingController = PagingController<int, Post>(firstPageKey: 0);
  final repository = ref.watch(postRepositoryProvider);

  _pagingController.addPageRequestListener((pageKey) {
    _fetchPage(
      pageKey: pageKey,
      groupId: groupId,
      getPosts: repository.getPosts,
      pagingController: _pagingController,
    );
  });

  ref.onDispose(() => _pagingController.dispose());
  return _pagingController;
});

Future<void> _fetchPage({
  required int pageKey,
  required int groupId,
  required Future<List<Post>> Function({
    required int page,
    required int groupId,
  })
      getPosts,
  required PagingController<int, Post> pagingController,
}) async {
  try {
    final newItems = await getPosts(
      page: pageKey++,
      groupId: groupId,
    );

    final isLastPage = newItems.length < pageSize;

    if (isLastPage) {
      pagingController.appendLastPage(newItems);
    } else {
      pagingController.appendPage(newItems, pageKey);
    }
  } catch (error) {
    pagingController.error = error;
  }
}
