import 'package:flutter/material.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/ui/search/widget/search_result_card.dart';

class SearchResultList extends StatefulWidget {
  const SearchResultList({Key? key}) : super(key: key);

  @override
  State<SearchResultList> createState() => _SearchResultListState();
}

class _SearchResultListState extends State<SearchResultList> {
  final PagingController<int, GroupInfo> _pagingController =
      PagingController(firstPageKey: 0);

  @override
  void initState() {
    _pagingController.addPageRequestListener((pageKey) {
      _fetchPage(pageKey);
    });
    super.initState();
  }

  @override
  void dispose() {
    _pagingController.dispose();
    super.dispose();
  }

  Future<void> _fetchPage(int pageKey) async {
    try {
      await Future.delayed(const Duration(seconds: 1));
      final newItems = List.generate(
        10,
        (index) => GroupInfo(
          id: index,
          imageUrl: '',
          name: '',
          offline: false,
          participantCnt: 5,
          startDate: '',
        ),
      );
      const isLastPage = false;
      if (isLastPage) {
        _pagingController.appendLastPage(newItems);
      } else {
        final nextPageKey = pageKey + newItems.length;
        _pagingController.appendPage(newItems, nextPageKey);
      }
    } catch (error) {
      _pagingController.error = error;
    }
  }

  @override
  Widget build(BuildContext context) {
    return PagedSliverList.separated(
      pagingController: _pagingController,
      builderDelegate: PagedChildBuilderDelegate<GroupInfo>(
        itemBuilder: (context, item, index) => searchResultCard(
          title: '모모짱',
          img:
              'https://cdnweb01.wikitree.co.kr/webdata/editor/202105/14/img_20210514103008_152f5427.webp',
          onOff: false,
          startDay: '12/31~',
          headNum: 3,
        ),
      ),
      separatorBuilder: (context, index) => const SizedBox(height: 14),
    );
  }
}
