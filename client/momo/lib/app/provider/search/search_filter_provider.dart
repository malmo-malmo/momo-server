import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/user/category_check_provder.dart';
import 'package:momo/app/repository/user_repository.dart';

final checkSearchFilterProvider = Provider.autoDispose<bool>((ref) {
  final categoryState = ref.watch(searchCategoryProvider);
  final locationState = ref.watch(searchLocationProvider);

  for (bool check in categoryState) {
    if (check) return true;
  }
  for (bool check in locationState) {
    if (check) return true;
  }
  return false;
});

final searchCategoryProvider = Provider.autoDispose<List<bool>>((ref) {
  final categoryState = ref.watch(searchCategoryStateProvider);
  return categoryState;
});

final searchLocationProvider = Provider.autoDispose<List<bool>>((ref) {
  final categoryState = ref.watch(searchLocationStateProvider);
  return categoryState;
});

final searchCategoryStateProvider =
    StateNotifierProvider<CategoryState, List<bool>>((ref) {
  final repository = ref.watch(userRepositoryProvider);
  return CategoryState(8, repository: repository);
});

final searchLocationStateProvider =
    StateNotifierProvider<CategoryState, List<bool>>((ref) {
  final repository = ref.watch(userRepositoryProvider);
  return CategoryState(11, repository: repository);
});
