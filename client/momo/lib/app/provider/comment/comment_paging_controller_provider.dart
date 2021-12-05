import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/comment/comment.dart';
import 'package:momo/app/repository/comment_repository.dart';
import 'package:momo/app/util/constant.dart';

final commentPagingControllerProvider = Provider.family
    .autoDispose<PagingController<int, Comment>, int>((ref, postId) {
  final _pagingController = PagingController<int, Comment>(firstPageKey: 0);
  final repository = ref.watch(commentRepositoryProvider);

  Future<void> _fetchPage(int pageKey) async {
    try {
      final response =
          await repository.getComments(page: pageKey++, postId: postId);
      final newItems = response.comments;
      final isLastPage = response.commentCnt < pageSize;

      if (isLastPage) {
        _pagingController.appendLastPage(newItems);
      } else {
        _pagingController.appendPage(newItems, pageKey);
      }
    } catch (error) {
      _pagingController.error = error;
    }
  }

  _pagingController.addPageRequestListener((pageKey) => _fetchPage(pageKey));

  ref.onDispose(() => _pagingController.dispose());
  return _pagingController;
});
