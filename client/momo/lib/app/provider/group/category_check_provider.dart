import 'package:flutter_riverpod/flutter_riverpod.dart';

final groupRequestCategoryProvider = Provider.autoDispose<List<bool>>((ref) {
  final groupRequestCategoryState =
      ref.watch(groupRequestCategoryStateProvider);
  return groupRequestCategoryState;
});

final groupRequestCategoryStateProvider =
    StateNotifierProvider.autoDispose<GroupRequestCategoryState, List<bool>>(
        (ref) => GroupRequestCategoryState());

class GroupRequestCategoryState extends StateNotifier<List<bool>> {
  GroupRequestCategoryState() : super(List.generate(8, (index) => false));

  void checkCategory(int index) {
    state = [
      for (int i = 0; i < state.length; i++)
        if (i == index) state[index] = true else false
    ];
  }
}
