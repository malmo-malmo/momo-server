import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/schedule/schedule_detail.dart';
import 'package:momo/app/provider/user/user_data_provider.dart';
import 'package:momo/app/repository/schedule_repository.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';
import 'package:momo/app/ui/schedule_list/widget/admin_schedule_card.dart';
import 'package:momo/app/ui/schedule_list/widget/schedule_card.dart';
import 'package:momo/app/util/constant.dart';

import 'dart:developer';

class ScheduleList extends ConsumerStatefulWidget {
  const ScheduleList({
    Key? key,
    required this.groupId,
  }) : super(key: key);

  final int groupId;

  @override
  ConsumerState<ScheduleList> createState() => _ScheduleListState();
}

class _ScheduleListState extends ConsumerState<ScheduleList> {
  late int _manageId;

  final PagingController<int, ScheduleDetail> _pagingController =
      PagingController(firstPageKey: 0);

  @override
  void initState() {
    _pagingController.addPageRequestListener((pageKey) => _fetchPage(pageKey));
    super.initState();
  }

  @override
  void dispose() {
    _pagingController.dispose();
    super.dispose();
  }

  Future<void> _fetchPage(int pageKey) async {
    final repository = ref.watch(scheduleRepositoryProvider);
    try {
      final response =
          await repository.getSchedules(pageKey++, groupId: widget.groupId);
      _manageId = response.managerId;
      final newItems = response.groupScheduleResponses;

      final isLastPage = newItems.length < pageSize;
      if (isLastPage) {
        _pagingController.appendLastPage(newItems);
      } else {
        _pagingController.appendPage(newItems, pageKey);
      }
    } catch (error) {
      log(error.toString());
      _pagingController.error = error;
    }
  }

  @override
  Widget build(BuildContext context) {
    final userId = ref.watch(userDataProvider).id;
    return PagedSliverList.separated(
      pagingController: _pagingController,
      builderDelegate: PagedChildBuilderDelegate<ScheduleDetail>(
        itemBuilder: (context, item, index) {
          return _manageId == userId
              ? adminScheduleCard(
                  groupId: widget.groupId,
                  scheduleId: item.scheduleId,
                  profile: item.authorImage ??
                      'https://photo.hankooki.com/newsphoto/v001/2021/09/13/kha20210913180225_O_01_C_1.jpg',
                  nickname: item.authorNickname,
                  title: item.title,
                  contents: item.contents,
                  date: item.startDateTime,
                  attendance: item.attend,
                  isCheck: item.attendanceCheck,
                )
              : scheduleCard(
                  scheduleId: item.scheduleId,
                  profile: item.authorImage ??
                      'https://photo.hankooki.com/newsphoto/v001/2021/09/13/kha20210913180225_O_01_C_1.jpg',
                  nickname: item.authorNickname,
                  title: item.title,
                  contents: item.contents,
                  date: item.startDateTime,
                  attendance: item.attend,
                );
        },
        newPageProgressIndicatorBuilder: (context) => const LoadingCard(),
        firstPageProgressIndicatorBuilder: (context) => const LoadingCard(),
        noItemsFoundIndicatorBuilder: (context) => const NoItemCard(),
      ),
      separatorBuilder: (context, index) => const SizedBox(height: 14),
    );
  }
}
