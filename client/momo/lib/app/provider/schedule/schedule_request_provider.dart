import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/schedule/schedule_request.dart';

final scheduleRequestCheckProvider = Provider.autoDispose<bool>((ref) {
  final scheduleRequest = ref.watch(scheduleRequestProvider);

  if (scheduleRequest.title.isNotEmpty &&
      scheduleRequest.startDateTime.isNotEmpty &&
      scheduleRequest.groupId != 0 &&
      scheduleRequest.contents.isNotEmpty) {
    return true;
  }
  return false;
});

final scheduleRequestProvider = Provider.autoDispose<ScheduleRequest>((ref) {
  final scheduleRequestState = ref.watch(scheduleRequestStateProvider);
  return scheduleRequestState;
});

final scheduleRequestStateProvider =
    StateNotifierProvider.autoDispose<ScheduleRequestState, ScheduleRequest>(
        (ref) => ScheduleRequestState());

class ScheduleRequestState extends StateNotifier<ScheduleRequest> {
  ScheduleRequestState()
      : super(
          ScheduleRequest(
            contents: '',
            groupId: 0,
            isOffline: false,
            startDateTime: '',
            title: '',
          ),
        );
  void setGroupId(int groupId) => state = state.copyWith(groupId: groupId);

  void setTitle(String title) => state = state.copyWith(title: title);

  void setOnOff(bool isOffline) => state = state.copyWith(isOffline: isOffline);

  void setStartDateTime(String startDateTime) =>
      state = state.copyWith(startDateTime: startDateTime);

  void setContents(String contents) =>
      state = state.copyWith(contents: contents);
}
