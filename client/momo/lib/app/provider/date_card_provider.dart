import 'package:flutter_riverpod/flutter_riverpod.dart';

final dateCardTextProvider =
    Provider.autoDispose<String>((ref) => ref.watch(dateCardTextStateProvider));

final dateCardTextStateProvider =
    StateProvider.autoDispose<String>((ref) => '');
