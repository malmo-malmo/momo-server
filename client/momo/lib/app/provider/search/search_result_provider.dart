import 'package:flutter_riverpod/flutter_riverpod.dart';

final isShowResultProvider =
    Provider.autoDispose((ref) => ref.watch(isShowResultStateProvider));

final isShowResultStateProvider =
    StateProvider.autoDispose<bool>((ref) => false);
