import 'package:flutter_riverpod/flutter_riverpod.dart';

final onOffToggleProvider = Provider.autoDispose<List<bool>>((ref) {
  final onOffToggleState = ref.watch(onOffToggleStateProvider);
  return onOffToggleState;
});

final onOffToggleStateProvider =
    StateNotifierProvider.autoDispose<OnOffToggleState, List<bool>>(
        (ref) => OnOffToggleState());

class OnOffToggleState extends StateNotifier<List<bool>> {
  OnOffToggleState() : super([false, false]);

  void toggle(int index) => state = index == 0 ? [true, false] : [false, true];
}
