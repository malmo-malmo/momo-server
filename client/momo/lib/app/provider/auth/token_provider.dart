import 'package:flutter_riverpod/flutter_riverpod.dart';

final tokenProvider = Provider<String>((ref) => ref.watch(tokenStateProvider));

final tokenStateProvider = StateProvider<String>((ref) => '');
