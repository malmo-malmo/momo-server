import 'package:flutter_riverpod/flutter_riverpod.dart';

final todayProvider =
    Provider.autoDispose<int>((ref) => ref.watch(todayStateProvider));

final todayStateProvider =
    StateProvider.autoDispose<int>((ref) => DateTime.now().day);

final reminderScheduleCheckProvider =
    Provider.family.autoDispose<List<bool>, int>((ref, count) {
  final reminderScheduleState =
      ref.watch(reminderScheduleCheckStateProvider(count));
  return reminderScheduleState;
});

final reminderScheduleCheckStateProvider = StateNotifierProvider.family
    .autoDispose<DayState, List<bool>, int>((ref, count) => DayState(count));

class DayState extends StateNotifier<List<bool>> {
  DayState(int count)
      : super(List.generate(
            count, (index) => index == DateTime.now().day ? true : false));

  void check(int index) => state = [
        for (int i = 0; i < state.length; i++)
          if (i == index) true else false
      ];
}
