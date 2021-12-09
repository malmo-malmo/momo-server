import 'package:flutter_riverpod/flutter_riverpod.dart';

final isCheckAttendance = Provider.family.autoDispose<bool, int>((ref, count) {
  final attendanceChecks = ref.watch(attendanceCheckProvider(count));
  for (int i = 0; i < attendanceChecks.length; i++) {
    if (attendanceChecks[i] == -1) {
      return false;
    }
  }
  return true;
});

final attendanceCheckProvider =
    Provider.family.autoDispose<List<int>, int>((ref, count) {
  final attendanceState = ref.watch(attendanceCheckStateProvider(count));
  return attendanceState;
});

final attendanceCheckStateProvider = StateNotifierProvider.family
    .autoDispose<AttendanceState, List<int>, int>(
        (ref, count) => AttendanceState(count));

class AttendanceState extends StateNotifier<List<int>> {
  AttendanceState(int count) : super(List.generate(count, (index) => -1));

  void check({
    required int index,
    required int checkIndex,
  }) =>
      state = [
        for (int i = 0; i < state.length; i++)
          if (i == index) checkIndex else state[i]
      ];
}
