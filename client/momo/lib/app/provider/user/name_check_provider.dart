import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/user/user_info__request_provider.dart';

final nameCheckProvider = Provider.autoDispose<bool>((ref) {
  final userName = ref.watch(userInfoRequestProvider).nickname;

  if (userName.isEmpty) {
    return false;
  }
  return true;
});

final validateNameProvider = StateProvider.autoDispose<bool>((ref) => true);
