import 'package:flutter_riverpod/flutter_riverpod.dart';

final isCheckCategoryProvider = Provider<bool>((ref) {
  final categoryState = ref.watch(categoryStateProvider);

  for (bool check in categoryState) {
    if (check) return true;
  }
  return false;
});

final categoryProvider = Provider<List<bool>>((ref) {
  final categoryState = ref.watch(categoryStateProvider);
  return categoryState;
});

final categoryStateProvider =
    StateNotifierProvider<CategoryState, List<bool>>((ref) => CategoryState());

class CategoryState extends StateNotifier<List<bool>> {
  CategoryState() : super(List.generate(8, (index) => false));

  void toggleCategory(int index) {
    state = [
      for (int i = 0; i < state.length; i++)
        if (i == index) state[index] = !state[index] else state[i]
    ];
  }
}
