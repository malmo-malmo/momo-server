import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/common/code_check_pair.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/provider/city_result_provider.dart';

final categoryFilterProvider = Provider.autoDispose<List<CodeCheckPair>>((ref) {
  final categoryFilterState = ref.watch(categoryFilterStateProvider);
  return categoryFilterState;
});

final cityFilterProvider = Provider.autoDispose<List<CodeCheckPair>>((ref) {
  final cityFilterState = ref.watch(cityFilterStateProvider);
  return cityFilterState;
});

final categoryFilterStateProvider = StateNotifierProvider.autoDispose<
        SearchRequestFilterState, List<CodeCheckPair>>(
    (ref) => SearchRequestFilterState(categoryCodeNamePair.length));

final cityFilterStateProvider = StateNotifierProvider.autoDispose<
        SearchRequestFilterState, List<CodeCheckPair>>(
    (ref) => SearchRequestFilterState(cityCodeNamePair.length));

class SearchRequestFilterState extends StateNotifier<List<CodeCheckPair>> {
  SearchRequestFilterState(int count)
      : super(
          count == categoryCodeNamePair.length
              ? List.generate(
                  categoryCodeNamePair.length,
                  (index) => CodeCheckPair(
                    check: false,
                    code: categoryCodeNamePair[index].code,
                  ),
                )
              : List.generate(
                  cityCodeNamePair.length,
                  (index) => CodeCheckPair(
                    check: false,
                    code: cityCodeNamePair[index].code,
                  ),
                ),
        );

  void checkFilter(List<bool> checks) {
    state = [
      for (int i = 0; i < checks.length; i++)
        state[i].copyWith(check: checks[i])
    ];
  }

  List<String> makeRequestFilter() {
    return [
      for (int i = 0; i < state.length; i++)
        if (state[i].check) state[i].code
    ];
  }
}
