import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/provider/search/search_request_filter_provider.dart';
import 'package:momo/app/repository/group_repository.dart';
import 'package:momo/app/util/constant.dart';

final searchReulstPagingController =
    Provider<PagingController<int, GroupInfo>>((ref) {
  final _pagingController = PagingController<int, GroupInfo>(firstPageKey: 0);

  final repository = ref.watch(groupRepositoryProvider);
  final categories = ref.watch(categoryFilterProvider);
  final cities = ref.watch(cityFilterProvider);

  Future<void> _fetchPage(int pageKey) async {
    try {
      final newItems = await repository.getGroupBySearch(
        pageKey++,
        categories: categories,
        cities: cities,
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
