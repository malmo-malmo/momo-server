import 'package:flutter_riverpod/flutter_riverpod.dart';

final bottomIndexProvider = StateProvider<int>((ref) => 0);

final checkScrollProvider =
    Provider<bool>((ref) => ref.watch(checkScrollStateProvider));

final checkScrollStateProvider = StateProvider<bool>((ref) => true);
