import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/user/user_info_provider.dart';

final nameCheckProvider = Provider<bool>((ref) {
  final userName = ref.watch(userInfoProvider).nickname;

  if (userName.isEmpty) {
    return false;
  }
  return true;
});

final validateNameProvider =
    Provider<bool>((ref) => ref.watch(validateNameStateProvider));

final validateNameStateProvider = StateProvider<bool>((ref) => true);