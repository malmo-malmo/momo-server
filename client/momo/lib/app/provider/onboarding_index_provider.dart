import 'package:flutter_riverpod/flutter_riverpod.dart';

final onboardingProvider =
    Provider<int>((ref) => ref.watch(onboardingStateProvider));

final onboardingStateProvider = StateProvider<int>((ref) => 0);
