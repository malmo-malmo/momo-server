import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/repository/user_repository.dart';

final groupSummaryProvider = FutureProvider.autoDispose((ref) async {
  final userRepository = ref.watch(userRepositoryProvider);
  final response = await userRepository.getMyGroupSummary();
  return response;
});
