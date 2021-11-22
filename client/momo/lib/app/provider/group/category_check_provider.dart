import 'package:flutter_riverpod/flutter_riverpod.dart';

final newMeetCategoryProvider = Provider.autoDispose<List<bool>>((ref) {
  final newMeetCategoryState = ref.watch(newMeetCategoryStateProvider);
  return newMeetCategoryState;
});

final newMeetCategoryStateProvider =
    StateNotifierProvider.autoDispose<NewMeetCategoryState, List<bool>>(
        (ref) => NewMeetCategoryState());

class NewMeetCategoryState extends StateNotifier<List<bool>> {
  NewMeetCategoryState() : super(List.generate(8, (index) => false));

  void checkCategory(int index) {
    state = [
      for (int i = 0; i < state.length; i++)
        if (i == index) state[index] = true else false
    ];
  }
}
