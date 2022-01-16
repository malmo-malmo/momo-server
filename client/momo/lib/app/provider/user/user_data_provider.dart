import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/common/code_name_pair.dart';
import 'package:momo/app/model/user/category_request.dart';
import 'package:momo/app/model/user/user_info_request.dart';
import 'package:momo/app/model/user/user_response.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/provider/city_result_provider.dart';
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

  Future<bool> validateName(String nickname) async {
    try {
      await userRepository.validateNickname(nickname);
      return false;
    } catch (e) {
      return true;
    }
  }

  Future<dynamic> getUserData() async {
    final userResponse = await userRepository.getUserData();
    state = userResponse;
  }

  Future<bool> isFirstLogin() async {
    final userResponse = await userRepository.getUserData();
    if (userResponse.nickname.isNotEmpty) {
      state = userResponse;
      return false;
    }
    return true;
  }

  Future<dynamic> updateUserInfo(UserInfoRequest userInfoRequest) async {
    final response = await userRepository.updateUserInfo(userInfoRequest);
    state = state.copyWith(
      nickname: userInfoRequest.nickname,
      city: cityCodeNamePair.where((e) => e.code == userInfoRequest.city).first,
      district: userInfoRequest.district,
      university: userInfoRequest.university,
    );
    return response;
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
