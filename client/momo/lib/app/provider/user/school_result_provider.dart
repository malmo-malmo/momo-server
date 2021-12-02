import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/repository/user_repository.dart';

final universityResultProvider = FutureProvider.family
    .autoDispose<List<String>, String>((ref, universityName) async {
  final repository = ref.watch(userRepositoryProvider);
  final universities = await repository.getUniversities(universityName);
  return universities.map((e) => e.name).toList();
});
