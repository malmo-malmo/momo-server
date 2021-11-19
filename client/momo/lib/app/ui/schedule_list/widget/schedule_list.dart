import 'package:flutter/material.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/schedule/user_schedule.dart';
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
          name: '강모모',
          profile:
              'http://image.newsis.com/2021/10/12/NISI20211012_0000844234_web.jpg',
          title: '강모모님의 일정입니다',
          contents: '한강가서 자전거타요',
          attendance: true,
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
          itemBuilder: (context, item, index) => scheduleCard(
            name: item.name,
            profile: item.profile,
            title: item.title,
            contents: item.contents,
            attendance: item.attendance,
          ),
        ),
        separatorBuilder: (context, index) => const SizedBox(height: 8),
      ),
    );
  }
}
