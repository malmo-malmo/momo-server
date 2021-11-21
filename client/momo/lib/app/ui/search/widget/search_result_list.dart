import 'package:flutter/material.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/meet/home_meet.dart';
import 'package:momo/app/ui/search/widget/search_result_card.dart';

class SearchResultList extends StatefulWidget {
  const SearchResultList({Key? key}) : super(key: key);

  @override
  State<SearchResultList> createState() => _SearchResultListState();
}

class _SearchResultListState extends State<SearchResultList> {
  final PagingController<int, HomeMeet> _pagingController =
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
        (index) => HomeMeet(
          id: index,
          title: '말모말모 모임',
          headNum: 3,
          onOff: '온라인',
          startDay: '12/25 ~',
          img:
              'https://dimg.donga.com/wps/NEWS/IMAGE/2021/07/11/107900145.1.jpg',
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
      builderDelegate: PagedChildBuilderDelegate<HomeMeet>(
        itemBuilder: (context, item, index) => searchResultCard(
          title: item.title,
          img: item.img,
          onOff: item.onOff,
          startDay: item.startDay,
          headNum: item.headNum,
        ),
      ),
      separatorBuilder: (context, index) => const SizedBox(height: 14),
    );
  }
}
