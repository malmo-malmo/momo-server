import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/repository/user_repository.dart';

final universityResultProvider = FutureProvider.family
    .autoDispose<List<String>, String>((ref, universityName) async {
  final repository = ref.watch(userRepositoryProvider);
  final universities = await repository.getUniversities(universityName);
  return universities.map((e) => e.name).toList();
});

final universityCheckProvider = StateNotifierProvider.family
    .autoDispose<CheckState, List<bool>, int>(
        (ref, count) => CheckState(count));

class CheckState extends StateNotifier<List<bool>> {
  CheckState(int count) : super(List.generate(count, (index) => false));

  void checkOne(int index) {
    state = [
      for (int i = 0; i < state.length; i++)
        if (i == index) true else false
    ];
  }
}
