import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/login/user_info_provider.dart';

final nameCheckProvider = Provider<bool>((ref) {
  final userName = ref.watch(userInfoProvider).nickname;

  if (userName.isEmpty) {
    return false;
  }
  return true;
});
