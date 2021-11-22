import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/schedule/schedule_request.dart';

final scheduleRequestCheckProvider = Provider.autoDispose<bool>((ref) {
  final scheduleRequest = ref.watch(scheduleRequestProvider);

  if (scheduleRequest.name.isNotEmpty &&
      scheduleRequest.onOff.isNotEmpty &&
      scheduleRequest.date.isNotEmpty &&
      scheduleRequest.time.isNotEmpty &&
      scheduleRequest.texts.isNotEmpty) {
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
            meetName: '청계천 달리기&산책',
            name: '',
            onOff: '',
            date: '',
            time: '',
            texts: '',
          ),
        );

  void setMeetName(String meetName) =>
      state = state.copyWith(meetName: meetName);

  void setName(String name) => state = state.copyWith(name: name);

  void setOnOff(String onOff) => state = state.copyWith(onOff: onOff);

  void setDate(String date) => state = state.copyWith(date: date);

  void setTime(String time) => state = state.copyWith(time: time);

  void setTexts(String texts) => state = state.copyWith(texts: texts);
}
