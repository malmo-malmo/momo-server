import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/schedule/schedule_detail.dart';
import 'package:momo/app/provider/schedule/group_schedule_list_dto.dart';
import 'package:momo/app/provider/schedule/group_schedule_list_provider.dart';
import 'package:momo/app/provider/user/user_data_provider.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';
import 'package:momo/app/ui/schedule_list/widget/admin_schedule_card.dart';
import 'package:momo/app/ui/schedule_list/widget/schedule_card.dart';

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
  final PagingController<int, ScheduleDetail> _pagingController =
      PagingController(firstPageKey: 0);

  @override
  void initState() {
    _pagingController.addPageRequestListener((pageKey) => ref
        .read(groupScheduleListStateProvider(widget.groupId).notifier)
        .getSchedules(pageKey));
    super.initState();
  }

  @override
  void dispose() {
    _pagingController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    ref.listen<GroupScheduleListDto>(
      groupScheduleListStateProvider(widget.groupId),
      (previous, next) {
        _pagingController.value = PagingState(
          itemList: next.schedules,
          nextPageKey: next.hasNext ? next.nextPage : null,
          error: next.error,
        );
      },
    );

    final userId = ref.watch(userDataProvider).id;
    final groupScheduleState =
        ref.watch(groupScheduleListStateProvider(widget.groupId));
    return PagedSliverList.separated(
      pagingController: _pagingController,
      builderDelegate: PagedChildBuilderDelegate<ScheduleDetail>(
        itemBuilder: (context, item, index) {
          return groupScheduleState.managerId == userId
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
