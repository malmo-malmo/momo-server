import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/schedule/group_schedule_list_dto.dart';
import 'package:momo/app/repository/schedule_repository.dart';
import 'package:momo/app/util/constant.dart';

final groupScheduleListStateProvider = StateNotifierProvider.family
    .autoDispose<GroupScheduleListState, GroupScheduleListDto, int>(
        (ref, groupId) {
  final scheduleRepository = ref.watch(scheduleRepositoryProvider);
  return GroupScheduleListState(
    groupId: groupId,
    scheduleRepository: scheduleRepository,
  );
});

class GroupScheduleListState extends StateNotifier<GroupScheduleListDto> {
  GroupScheduleListState({
    required this.scheduleRepository,
    required this.groupId,
  }) : super(
          GroupScheduleListDto(
            managerId: -1,
            schedules: [],
            hasNext: true,
            nextPage: 0,
          ),
        );

  final ScheduleRepository scheduleRepository;
  final int groupId;

  Future<void> getSchedules(int page) async {
    final response =
        await scheduleRepository.getSchedules(page++, groupId: groupId);
    state = state.copyWith(
      managerId: response.managerId,
      schedules: [...state.schedules, ...response.groupScheduleResponses],
      hasNext: response.groupScheduleResponses.length == pageSize,
      nextPage: page,
    );
  }

  void attendanceCallback(int scheduleId) {
    state = state.copyWith(
        schedules: state.schedules
            .map((element) => element.scheduleId == scheduleId
                ? element.copyWith(attendanceCheck: true)
                : element)
            .toList());
  }
}
