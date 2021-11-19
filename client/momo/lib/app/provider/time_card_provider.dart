import 'package:flutter_riverpod/flutter_riverpod.dart';

final timeCardTextProvider =
    Provider.autoDispose<String>((ref) => ref.watch(timeCardTextStateProvider));

final timeCardTextStateProvider =
    StateProvider.autoDispose<String>((ref) => '');
