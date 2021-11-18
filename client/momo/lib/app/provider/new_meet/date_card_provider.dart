import 'package:flutter_riverpod/flutter_riverpod.dart';

final dateCardTextProvider =
    Provider<String>((ref) => ref.watch(dateCardTextStateProvider));

final dateCardTextStateProvider = StateProvider<String>((ref) => '');
