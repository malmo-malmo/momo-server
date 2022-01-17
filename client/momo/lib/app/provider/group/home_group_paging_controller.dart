import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/model/group/group_list_dto.dart';
import 'package:momo/app/provider/group/group_provider.dart';
import 'package:momo/app/repository/group_repository.dart';
import 'package:momo/app/util/constant.dart';

final categoryController =
    Provider.autoDispose<PagingController<int, GroupInfo>>((ref) {
  final _pagingController = PagingController<int, GroupInfo>(firstPageKey: 0);

  _pagingController.addPageRequestListener((pageKey) => ref
      .read(categoryGroupListProvider.notifier)
      .getGroupsByCategories(pageKey));
  ref.listen<GroupListDto>(categoryGroupListProvider, (previous, next) {
    _pagingController.value = PagingState(
        itemList: next.groups,
        nextPageKey: next.hasNext ? next.nextPage : null,
        error: null);
  });

  ref.onDispose(() => _pagingController.dispose());
  return _pagingController;
});

final districtController =
    Provider.autoDispose<PagingController<int, GroupInfo>>((ref) {
  final _pagingController = PagingController<int, GroupInfo>(firstPageKey: 0);
  final repository = ref.watch(groupRepositoryProvider);

  _pagingController.addPageRequestListener((pageKey) {
    _fetchPage(
      pageKey: pageKey,
      getGroups: repository.getGroupsByDistrict,
      pagingController: _pagingController,
    );
  });

  ref.onDispose(() => _pagingController.dispose());
  return _pagingController;
});

final universityController =
    Provider.autoDispose<PagingController<int, GroupInfo>>((ref) {
  final _pagingController = PagingController<int, GroupInfo>(firstPageKey: 0);
  final repository = ref.watch(groupRepositoryProvider);

  _pagingController.addPageRequestListener((pageKey) {
    _fetchPage(
      pageKey: pageKey,
      getGroups: repository.getGroupsByUniversity,
      pagingController: _pagingController,
    );
  });

  ref.onDispose(() => _pagingController.dispose());
  return _pagingController;
});

Future<void> _fetchPage({
  required int pageKey,
  required Future<List<GroupInfo>> Function(int page) getGroups,
  required PagingController pagingController,
}) async {
  try {
    final newItems = await getGroups(pageKey++);

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
