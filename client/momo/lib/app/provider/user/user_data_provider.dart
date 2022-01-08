import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/common/code_name_pair.dart';
import 'package:momo/app/model/user/category_request.dart';
import 'package:momo/app/model/user/user_response.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/repository/user_repository.dart';

final userDataProvider =
    StateNotifierProvider<UserDataState, UserResponse>((ref) {
  final repository = ref.watch(userRepositoryProvider);
  return UserDataState(userRepository: repository);
});

class UserDataState extends StateNotifier<UserResponse> {
  UserDataState({
    required this.userRepository,
  }) : super(
          UserResponse(
            id: -1,
            nickname: '',
            city: CodeNamePair(code: '', name: ''),
            district: '',
            university: '',
            categories: [],
          ),
        );

  final UserRepository userRepository;

  Future<void> getUserData() async {
    final response = await userRepository.getUserData();
    state = response;
  }

  Future<dynamic> updateUserCategories(CategoryRequest categoryRequest) async {
    final response = await userRepository.updateUserCategory(categoryRequest);
    state = state.copyWith(
      categories: [
        for (int i = 0; i < categoryRequest.favoriteCategories.length; i++)
          for (int j = 0; j < categoryCodeNamePair.length; j++)
            if (categoryCodeNamePair[j].code ==
                categoryRequest.favoriteCategories[i])
              categoryCodeNamePair[j]
      ],
    );
    return response;
  }
}
