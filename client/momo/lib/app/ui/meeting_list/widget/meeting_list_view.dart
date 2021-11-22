import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/ui/components/card/home_group_card.dart';

class MeetingListView extends StatefulWidget {
  const MeetingListView({Key? key}) : super(key: key);

  @override
  State<MeetingListView> createState() => _MeetingListViewState();
}

class _MeetingListViewState extends State<MeetingListView> {
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
            startDate: ''),
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
    return Expanded(
      child: PagedGridView(
        pagingController: _pagingController,
        builderDelegate: PagedChildBuilderDelegate<GroupInfo>(
          itemBuilder: (context, item, index) => homeGroupCard(
            onOff: true,
            title: 'item.title',
            headNum: 5,
            date: 'item.startDay',
            img: '',
            height: 200.h,
            width: double.infinity,
          ),
        ),
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
          mainAxisExtent: 200.h,
          mainAxisSpacing: 16,
          crossAxisSpacing: 16,
          childAspectRatio: 100 / 150,
        ),
        showNewPageProgressIndicatorAsGridChild: false,
        showNewPageErrorIndicatorAsGridChild: false,
        showNoMoreItemsIndicatorAsGridChild: false,
      ),
    );
  }
}
