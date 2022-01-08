import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/user/category_request.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/repository/user_repository.dart';

final isCheckCategoryProvider = Provider.autoDispose<bool>((ref) {
  final categoryState = ref.watch(categoryChecksProvider);

  for (bool check in categoryState) {
    if (check) return true;
  }
  return false;
});

final categoryChecksProvider =
    StateNotifierProvider.autoDispose<CategoryState, List<bool>>((ref) {
  final repository = ref.watch(userRepositoryProvider);
  return CategoryState(8, repository: repository);
});

class CategoryState extends StateNotifier<List<bool>> {
  CategoryState(
    int cnt, {
    required this.repository,
  }) : super(List.generate(cnt, (index) => false));

  final UserRepository repository;

  void toggleCategory(int index) {
    state = [
      for (int i = 0; i < state.length; i++)
        if (i == index) state[index] = !state[index] else state[i]
    ];
  }

  Future<dynamic> updateUserCategories() {
    final categoryRequest = CategoryRequest(
      favoriteCategories: [
        for (int i = 0; i < state.length; i++)
          if (state[i]) categoryCodeNamePair[i].code
      ],
    );
    final response = repository.updateUserCategory(categoryRequest);
    return response;
  }
}
