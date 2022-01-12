import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/user/category_request.dart';
import 'package:momo/app/model/user/user_info_request.dart';
import 'package:momo/app/provider/city_result_provider.dart';
import 'package:momo/app/provider/user/name_check_provider.dart';
import 'package:momo/app/repository/user_repository.dart';

final userInfoRequestCheckProvider = Provider<bool>((ref) {
  final userInfo = ref.watch(userInfoRequestProvider);
  final validateNameCheck = ref.watch(validateNameProvider);
  if (!validateNameCheck &&
      userInfo.nickname.isNotEmpty &&
      userInfo.university.isNotEmpty &&
      userInfo.city.isNotEmpty &&
      userInfo.district.isNotEmpty) {
    return true;
  }
  return false;
});

final userInfoRequestProvider =
    StateNotifierProvider<UserInfoRequestState, UserInfoRequest>((ref) {
  final repository = ref.watch(userRepositoryProvider);
  return UserInfoRequestState(repository: repository);
});

class UserInfoRequestState extends StateNotifier<UserInfoRequest> {
  UserInfoRequestState({required this.repository})
      : super(
          UserInfoRequest(
            nickname: '',
            university: '',
            city: '',
            district: '',
          ),
        );

  final UserRepository repository;

  void setUserNickname(String nickname) =>
      state = state.copyWith(nickname: nickname);

  void setUserUniversity(String university) =>
      state = state.copyWith(university: university);

  void setUserCity(String city) => state = state.copyWith(
      city:
          cityCodeNamePair.where((element) => element.name == city).first.code,
      district: '');

  void setUserDistrict(String district) =>
      state = state.copyWith(district: district);

  String get userCity => state.city.isEmpty
      ? ''
      : cityCodeNamePair
          .where((element) => element.code == state.city)
          .first
          .name;

  Future<bool> validateName(String nickname) async {
    try {
      await repository.validateNickname(nickname);
      return false;
    } catch (e) {
      return true;
    }
  }

  Future<dynamic> updateUserInfo(UserInfoRequest userInfoRequest) async {
    final response = await repository.updateUserInfo(userInfoRequest);
    return response;
  }

  Future<dynamic> updateUserCategory(CategoryRequest categoryRequest) async {
    final response = await repository.updateUserCategory(categoryRequest);
    return response;
  }
}