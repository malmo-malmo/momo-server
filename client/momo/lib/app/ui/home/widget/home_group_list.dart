import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/ui/components/card/home_group_card.dart';
import 'package:momo/app/util/date_format.dart';

class HomeMeetingList extends StatefulWidget {
  const HomeMeetingList({Key? key}) : super(key: key);

  @override
  State<HomeMeetingList> createState() => _HomeMeetingListState();
}

class _HomeMeetingListState extends State<HomeMeetingList> {
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
          name: '시사 이슈 스터디',
          offline: false,
          participantCnt: 5,
          startDate: '2021-12-31',
          imageUrl:
              'https://p4.wallpaperbetter.com/wallpaper/817/916/889/falcon-9-rocket-4k-high-definition-wallpaper-preview.jpg',
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
      height: 200,
      child: PagedListView<int, GroupInfo>.separated(
        pagingController: _pagingController,
        scrollDirection: Axis.horizontal,
        builderDelegate: PagedChildBuilderDelegate<GroupInfo>(
          itemBuilder: (context, item, index) => homeGroupCard(
            title: item.name,
            onOff: item.offline,
            headNum: item.participantCnt,
            date: groupDateFormat(item.startDate),
            img: item.imageUrl,
            height: 200,
            width: 144,
          ),
        ),
        separatorBuilder: (context, index) => const SizedBox(width: 14),
      ),
    );
  }
}
