import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/provider/user/user_data_provider.dart';
import 'package:momo/app/repository/group_repository.dart';
import 'package:momo/app/util/constant.dart';

final recommendPaigingControllerProvider =
    Provider.autoDispose<PagingController<int, GroupInfo>>((ref) {
  final _pagingController = PagingController<int, GroupInfo>(firstPageKey: 0);
  final repository = ref.watch(groupRepositoryProvider);

  Future<void> _fetchPage(int pageKey) async {
    final categories =
        ref.watch(groupCategoryCheckStateProvider.notifier).makeFilter();
    try {
      final newItems = await repository.getGroupBySearch(
        pageKey++,
        categories: categories,
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

  _pagingController.addPageRequestListener((pageKey) => _fetchPage(pageKey));
  return _pagingController;
});

final groupCategoryCheckStateProvider =
    StateNotifierProvider.autoDispose<GroupCategoryListState, List<bool>>(
        (ref) => GroupCategoryListState());

class GroupCategoryListState extends StateNotifier<List<bool>> {
  GroupCategoryListState()
      : super(List.generate(9, (index) => index == 0 ? true : false));

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

  List<String> makeFilter() {
    if (state.first) {
      return userData.categories.map((e) => e.code).toList();
    } else {
      return [
        for (int i = 1; i < state.length; i++)
          if (state[i]) categoryCodeNamePair[i - 1].code
      ];
    }
  }
}
