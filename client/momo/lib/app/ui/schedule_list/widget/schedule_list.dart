import 'package:flutter/material.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/meet/meet_feed.dart';
import 'package:momo/app/model/schedule/user_schedule.dart';
import 'package:momo/app/ui/components/feed_card.dart';
import 'package:momo/app/ui/schedule_list/widget/admin_schedule_card.dart';
import 'package:momo/app/ui/schedule_list/widget/schedule_card.dart';

class ScheduleList extends StatefulWidget {
  const ScheduleList({Key? key}) : super(key: key);

  @override
  State<ScheduleList> createState() => _ScheduleListState();
}

class _ScheduleListState extends State<ScheduleList> {
  final PagingController<int, UserSchedule> _pagingController =
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
        (index) => UserSchedule(
          id: index,
          name: '김모모',
          profile:
              'https://cdn.gukjenews.com/news/photo/202107/2270344_2261028_2410.jpg',
          title: '청계천 달리기 정기모임',
          contents:
              '일정 메모에 대한 내용이 들어갑니다. 일정 메모에 대한 내용이 들어갑니다. 일정 메모에 대한 내용이 들어갑니다. 일정 메모에 대한 내용이 들어갑니다. 일정 메모에 대한 내용이 들어갑니다. 일정 메모에 대한 내용이 들어갑니다. ',
          attendance: false,
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
        builderDelegate: PagedChildBuilderDelegate<UserSchedule>(
            itemBuilder: (context, item, index) {
          if (index == 0) {
            return adminScheduleCard(
              postId: item.id,
              profile: item.profile,
              userName: item.name,
              title: item.title,
              text: item.contents,
              date: '2021년 12월 31일 오후 9:00',
              attendance: item.attendance,
              isCheck: true,
            );
          } else if (index == 1) {
            return adminScheduleCard(
              postId: item.id,
              profile: item.profile,
              userName: item.name,
              title: item.title,
              text: item.contents,
              date: '2021년 12월 31일 오후 9:00',
              attendance: item.attendance,
              isCheck: false,
            );
          }
          return scheduleCard(
            postId: item.id,
            profile: item.profile,
            userName: item.name,
            title: item.title,
            text: item.contents,
            date: '2021년 12월 31일 오후 9:00',
            attendance: item.attendance,
          );
        }),
        separatorBuilder: (context, index) => const SizedBox(height: 14),
      ),
    );
  }
}
