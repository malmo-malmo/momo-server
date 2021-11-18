import 'package:flutter_riverpod/flutter_riverpod.dart';

final bottomIndexProvider =
    Provider<int>((ref) => ref.watch(bottomIndexStateProvider));

final bottomIndexStateProvider = StateProvider<int>((ref) => 0);

final checkScrollProvider =
    Provider<bool>((ref) => ref.watch(checkScrollStateProvider));

final checkScrollStateProvider = StateProvider<bool>((ref) => true);
