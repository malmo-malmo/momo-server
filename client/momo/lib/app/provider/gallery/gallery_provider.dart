import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/util/constant.dart';

final isSelectPhoto = Provider.autoDispose<bool>((ref) {
  final galleryState = ref.watch(galleryStateProvider);
  for (int i = 0; i < galleryState.length; i++) {
    if (galleryState[i]) {
      return true;
    }
  }
  return false;
});

final checkMaxPhoto = Provider.autoDispose<bool>((ref) {
  final galleryState = ref.watch(galleryStateProvider);
  return galleryState.where((e) => e).toList().length == maxSelectCount;
});

final galleryStateProvider =
    StateNotifierProvider.autoDispose<GalleryState, List<bool>>(
        (ref) => GalleryState());

class GalleryState extends StateNotifier<List<bool>> {
  GalleryState() : super(List.generate(maxPhotoCount, (index) => false));

  void toggle(int index) {
    state = [
      for (int i = 0; i < state.length; i++)
        if (i == index) state[index] = !state[index] else state[i]
    ];
  }

  void checkOne(int index) {
    state = [
      for (int i = 0; i < state.length; i++)
        if (i == index) true else false
    ];
  }
}
