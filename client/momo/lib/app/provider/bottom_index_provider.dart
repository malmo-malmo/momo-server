import 'package:flutter_riverpod/flutter_riverpod.dart';

final bottomIndexProvider = StateProvider<int>((ref) => 0);

final checkScrollProvider = StateProvider<bool>((ref) => true);
