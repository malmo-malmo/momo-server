import 'package:flutter_riverpod/flutter_riverpod.dart';

final isShowResultProvider =
    Provider.autoDispose((ref) => ref.watch(isShowResultStateProvider));

final isShowResultStateProvider =
    StateNotifierProvider.autoDispose<SearchState, bool>(
        (ref) => SearchState());

class SearchState extends StateNotifier<bool> {
  SearchState() : super(false);

  void search() {
    state = true;
  }
}
