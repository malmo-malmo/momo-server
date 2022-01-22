import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/user/user_info_request.dart';
import 'package:momo/app/provider/city_result_provider.dart';
import 'package:momo/app/provider/user/name_check_provider.dart';

final userInfoRequestCheckProvider = Provider.autoDispose<bool>((ref) {
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
    StateNotifierProvider.autoDispose<UserInfoRequestState, UserInfoRequest>(
        (ref) => UserInfoRequestState());

class UserInfoRequestState extends StateNotifier<UserInfoRequest> {
  UserInfoRequestState()
      : super(
          UserInfoRequest(
            nickname: '',
            university: '',
            city: '',
            district: '',
            imagePath: '',
          ),
        );

  UserInfoRequest get updateUserInfo => state;

  void setImagePath(String imagePath) =>
      state = state.copyWith(imagePath: imagePath);

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
}
