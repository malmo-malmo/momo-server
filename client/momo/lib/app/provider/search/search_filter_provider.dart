import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/provider/city_result_provider.dart';

final searchFilterCheckProvider = Provider.autoDispose<bool>((ref) {
  final categoryChecks = ref.watch(searchCategoryProvider);
  final cityChecks = ref.watch(searchCityProvider);

  for (int i = 0; i < categoryChecks.length; i++) {
    if (categoryChecks[i]) {
      return true;
    }
  }
  for (int i = 0; i < cityChecks.length; i++) {
    if (cityChecks[i]) {
      return true;
    }
  }
  return false;
});

final searchCategoryProvider = Provider.autoDispose<List<bool>>((ref) {
  final categoryState = ref.watch(searchCategoryStateProvider);
  return categoryState;
});

final searchCityProvider = Provider.autoDispose<List<bool>>((ref) {
  final categoryState = ref.watch(searchCityStateProvider);
  return categoryState;
});

final searchCategoryStateProvider =
    StateNotifierProvider.autoDispose<ToggleState, List<bool>>(
        (ref) => ToggleState(categoryCodeNamePair.length));

final searchCityStateProvider =
    StateNotifierProvider.autoDispose<ToggleState, List<bool>>(
        (ref) => ToggleState(cityCodeNamePair.length));

class ToggleState extends StateNotifier<List<bool>> {
  ToggleState(int count) : super(List.generate(count, (index) => false));

  void toggle(int index) {
    state = [
      for (int i = 0; i < state.length; i++)
        if (i == index) state[index] = !state[index] else state[i]
    ];
  }
}
