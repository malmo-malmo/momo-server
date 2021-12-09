import 'package:flutter_riverpod/flutter_riverpod.dart';

final selectDayProvider =
    Provider.autoDispose<DateTime>((ref) => ref.watch(selectdDayStateProvider));

final selectdDayStateProvider =
    StateProvider.autoDispose<DateTime>((ref) => DateTime.now());
