import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/provider/city_result_provider.dart';

final categoryFilterProvider = Provider<List<String>>((ref) {
  final categoryFilterState = ref.watch(categoryFilterStateProvider);
  return categoryFilterState;
});

final cityFilterProvider = Provider<List<String>>((ref) {
  final cityFilterState = ref.watch(cityFilterStateProvider);
  return cityFilterState;
});

final categoryFilterStateProvider =
    StateNotifierProvider<SearchRequestFilterState, List<String>>(
        (ref) => SearchRequestFilterState());

final cityFilterStateProvider =
    StateNotifierProvider<SearchRequestFilterState, List<String>>(
        (ref) => SearchRequestFilterState());

class SearchRequestFilterState extends StateNotifier<List<String>> {
  SearchRequestFilterState() : super([]);

  void makeCategoryFilter(List<bool> checks) {
    state = [
      for (int i = 0; i < checks.length; i++)
        if (checks[i]) categoryCodeNamePair[i].code
    ];
  }

  void makeCityFilter(List<bool> checks) {
    state = [
      for (int i = 0; i < checks.length; i++)
        if (checks[i]) cityCodeNamePair[i].code
    ];
  }
}
