import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/search/search_request_filter_provider.dart';

final searchFilterCheckProvider = Provider.autoDispose<bool>((ref) {
  final categoryChecks = ref.watch(searchCategoryStateProvider);
  final cityChecks = ref.watch(searchCityStateProvider);

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

final searchCategoryStateProvider =
    StateNotifierProvider.autoDispose<ToggleState, List<bool>>((ref) {
  final state =
      ref.watch(categoryFilterStateProvider).map((e) => e.check).toList();
  return ToggleState(state);
});

final searchCityStateProvider =
    StateNotifierProvider.autoDispose<ToggleState, List<bool>>((ref) {
  final state = ref.watch(cityFilterStateProvider).map((e) => e.check).toList();
  return ToggleState(state);
});

class ToggleState extends StateNotifier<List<bool>> {
  ToggleState(List<bool> state) : super(state);

  void toggle(int index) {
    state = [
      for (int i = 0; i < state.length; i++)
        if (i == index) state[index] = !state[index] else state[i]
    ];
  }
}
