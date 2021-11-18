import 'package:flutter_riverpod/flutter_riverpod.dart';

final scrollProvider = Provider<int>((ref) => ref.watch(scrollStateProvider));

final scrollStateProvider = StateProvider<int>((ref) => 0);
