import 'package:flutter/material.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/schedule/schedule_detail.dart';
import 'package:momo/app/ui/schedule_list/widget/schedule_card.dart';

class ScheduleList extends StatefulWidget {
  const ScheduleList({Key? key}) : super(key: key);

  @override
  State<ScheduleList> createState() => _ScheduleListState();
}

class _ScheduleListState extends State<ScheduleList> {
  final PagingController<int, ScheduleDetail> _pagingController =
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
        (index) => ScheduleDetail(
          id: index,
          title: 'title',
          contents: 'contents',
          authorNickname: 'authorNickname',
          authorImage: 'authorImage',
          startDateTime: 'startDateTime',
          offline: false,
          attendanceCheck: false,
          attend: false,
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
    return Expanded(
      child: PagedListView.separated(
        pagingController: _pagingController,
        builderDelegate: PagedChildBuilderDelegate<ScheduleDetail>(
            itemBuilder: (context, item, index) {
          // if (index == 0) {
          //   return adminScheduleCard(
          //     postId: item.id,
          //     profile: item.profile,
          //     userName: item.name,
          //     title: item.title,
          //     text: item.contents,
          //     date: '2021년 12월 31일 오후 9:00',
          //     attendance: item.attendance,
          //     isCheck: true,
          //   );
          // } else if (index == 1) {
          //   return adminScheduleCard(
          //     postId: item.id,
          //     profile: item.profile,
          //     userName: item.name,
          //     title: item.title,
          //     text: item.contents,
          //     date: '2021년 12월 31일 오후 9:00',
          //     attendance: item.attendance,
          //     isCheck: false,
          //   );
          // }
          return scheduleCard(
            postId: item.id,
            profile: '',
            userName: '',
            title: item.title,
            text: item.contents,
            date: '2021년 12월 31일 오후 9:00',
            attendance: item.attend,
          );
        }),
        separatorBuilder: (context, index) => const SizedBox(height: 14),
      ),
    );
  }
}
