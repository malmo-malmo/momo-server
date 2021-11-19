import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/meet/home_meet.dart';
import 'package:momo/app/ui/components/home_meet_card.dart';

class HomeMeetingList extends StatefulWidget {
  const HomeMeetingList({Key? key}) : super(key: key);

  @override
  State<HomeMeetingList> createState() => _HomeMeetingListState();
}

class _HomeMeetingListState extends State<HomeMeetingList> {
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
          onOff: '오프라인',
          startDay: '12/25 ~',
          img:
              'https://cdn.crowdpic.net/detail-thumb/thumb_d_43948D943506904098D88A53006BE673.jpg',
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
    return SizedBox(
      height: 200.h,
      child: PagedListView<int, HomeMeet>.separated(
        pagingController: _pagingController,
        scrollDirection: Axis.horizontal,
        builderDelegate: PagedChildBuilderDelegate<HomeMeet>(
          itemBuilder: (context, item, index) => homeMeetCard(
            onOff: item.onOff,
            title: item.title,
            headNum: item.headNum,
            date: item.startDay,
            img: item.img,
            height: 200.h,
            width: 144.w,
          ),
        ),
        separatorBuilder: (context, index) => const SizedBox(width: 16),
      ),
    );
  }
}
