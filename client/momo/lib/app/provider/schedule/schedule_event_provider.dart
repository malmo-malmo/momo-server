import 'package:flutter_riverpod/flutter_riverpod.dart';

final scheduleEventProvider = Provider<List<DateTime>>((ref) {
  final scheduleEvents = ref.watch(scheduleEventStateProvider);
  return scheduleEvents;
});

final scheduleEventStateProvider =
    StateNotifierProvider<ScheduleEventState, List<DateTime>>(
        (ref) => ScheduleEventState());

class ScheduleEventState extends StateNotifier<List<DateTime>> {
  ScheduleEventState() : super([]);

  void changeEvent(List<DateTime> dateTimes) => state = [...dateTimes];
}
