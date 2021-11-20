import 'package:flutter_riverpod/flutter_riverpod.dart';

final isSelectPhoto = Provider.autoDispose<bool>((ref) {
  final galleryState = ref.watch(galleryProvider);
  for (int i = 0; i < galleryState.length; i++) {
    if (galleryState[i]) {
      return true;
    }
  }
  return false;
});

final galleryProvider = Provider.autoDispose<List<bool>>((ref) {
  final galleryState = ref.watch(galleryStateProvider);
  return galleryState;
});

final galleryStateProvider =
    StateNotifierProvider.autoDispose<GalleryState, List<bool>>(
        (ref) => GalleryState());

class GalleryState extends StateNotifier<List<bool>> {
  GalleryState() : super(List.generate(100, (index) => false));

  void checkPhoto(int idx) {
    state = List.generate(100, (index) => index == idx ? true : false);
  }
}
