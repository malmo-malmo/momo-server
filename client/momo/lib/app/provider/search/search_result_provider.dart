import 'package:flutter_riverpod/flutter_riverpod.dart';

final isShowResultStateProvider =
    StateProvider.autoDispose<bool>((ref) => false);
