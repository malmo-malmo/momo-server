import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/repository/group_repository.dart';
import 'package:momo/app/util/constant.dart';

final categoryListPagingController =
    Provider.autoDispose<PagingController<int, GroupInfo>>((ref) {
  final _pagingController = PagingController<int, GroupInfo>(firstPageKey: 1);
  final repository = ref.watch(groupRepositoryProvider);

  Future<void> _fetchPage(int pageKey) async {
    try {
      final newItems = await repository.getGroupBySearch(
        pageKey++,
        categories: [],
        cities: [],
      );

      final isLastPage = newItems.length < pageSize;

      if (isLastPage) {
        _pagingController.appendLastPage(newItems);
      } else {
        _pagingController.appendPage(newItems, pageKey);
      }
    } catch (error) {
      _pagingController.error = error;
    }
  }

  _pagingController.addPageRequestListener((pageKey) {
    _fetchPage(pageKey);
  });

  ref.onDispose(() => _pagingController.dispose());
  return _pagingController;
});

final groupCategoryStateProvider = Provider.autoDispose<List<bool>>((ref) {
  final groupCategoryState = ref.watch(groupCategoryCheckStateProvider);
  return groupCategoryState;
});

final groupCategoryCheckStateProvider =
    StateNotifierProvider.autoDispose<GroupCategoryListState, List<bool>>(
        (ref) {
  final repository = ref.watch(groupRepositoryProvider);
  return GroupCategoryListState(repository: repository);
});

class GroupCategoryListState extends StateNotifier<List<bool>> {
  GroupCategoryListState({
    required this.repository,
  }) : super(List.generate(9, (index) => index == 0 ? true : false));

  final GroupRepository repository;

  void toggleCategory(int index) {
    if (index == 0) {
      state = List.generate(9, (idx) => idx == 0 ? !state[index] : false);
    } else {
      state = [
        false,
        for (int i = 1; i < 9; i++)
          if (i == index) state[index] = !state[index] else state[i]
      ];
    }
  }
}
