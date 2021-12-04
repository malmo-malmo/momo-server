import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/user/user_response.dart';
import 'package:momo/app/repository/user_repository.dart';

late UserResponse userData;

final userDataProvider = FutureProvider((ref) async {
  final repository = ref.watch(userRepositoryProvider);
  final userResponse = await repository.getUserData();
  userData = userResponse;
});
