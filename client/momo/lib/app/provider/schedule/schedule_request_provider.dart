import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/schedule/schedule_request.dart';
import 'package:momo/app/model/schedule/schedule_request_dto.dart';
import 'package:momo/app/repository/schedule_repository.dart';

final scheduleRequestCheckProvider =
    Provider.family.autoDispose<bool, int?>((ref, groupId) {
  final scheduleRequest = ref.watch(scheduleRequestProvider(groupId));

  if (scheduleRequest.title.isNotEmpty &&
      scheduleRequest.year != -1 &&
      scheduleRequest.month != -1 &&
      scheduleRequest.day != -1 &&
      scheduleRequest.hour != -1 &&
      scheduleRequest.minute != -1 &&
      scheduleRequest.groupId != 0 &&
      scheduleRequest.contents.isNotEmpty) {
    return true;
  }
  return false;
});

final scheduleRequestProvider =
    Provider.family.autoDispose<ScheduleRequestDTO, int?>((ref, groupId) {
  final scheduleRequestState = ref.watch(scheduleRequestStateProvider(groupId));
  return scheduleRequestState;
});

final scheduleRequestStateProvider = StateNotifierProvider.family
    .autoDispose<ScheduleRequestState, ScheduleRequestDTO, int?>(
        (ref, groupId) {
  final repository = ref.watch(scheduleRepositoryProvider);

  return ScheduleRequestState(
    scheduleRepository: repository,
    groupId: groupId,
  );
});

class ScheduleRequestState extends StateNotifier<ScheduleRequestDTO> {
  ScheduleRequestState({
    int? groupId,
    required this.scheduleRepository,
  }) : super(
          ScheduleRequestDTO(
            contents: '',
            groupId: groupId ?? 0,
            isOffline: false,
            title: '',
            day: -1,
            hour: -1,
            minute: -1,
            month: -1,
            year: -1,
          ),
        );

  final ScheduleRepository scheduleRepository;

  void setGroupId(int groupId) => state = state.copyWith(groupId: groupId);

  void setTitle(String title) => state = state.copyWith(title: title);

  void setOnOff(bool isOffline) => state = state.copyWith(isOffline: isOffline);

  void setDate(DateTime dateTime) => state = state.copyWith(
        year: dateTime.year,
        month: dateTime.month,
        day: dateTime.day,
      );

  void setTime(int hour, int minute) =>
      state = state.copyWith(hour: hour, minute: minute);

  void setContents(String contents) =>
      state = state.copyWith(contents: contents);

  Future<void> createSchedule() async {
    final response = await scheduleRepository.createSchedule(
      ScheduleRequest(
        groupId: state.groupId,
        title: state.title,
        isOffline: state.isOffline,
        startDateTime: DateTime(
          state.year,
          state.month,
          state.day,
          state.hour,
          state.minute,
        ).toIso8601String(),
        contents: state.contents,
      ),
    );
    return response;
  }
}
