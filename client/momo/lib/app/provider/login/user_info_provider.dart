import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/login/user_info.dart';

final userInfoCheckProvider = Provider<bool>((ref) {
  final userInfo = ref.watch(userInfoProvider);
  if (userInfo.nickname.isNotEmpty &&
      userInfo.school.isNotEmpty &&
      userInfo.city.isNotEmpty &&
      userInfo.country.isNotEmpty) {
    return true;
  }
  return false;
});

final userInfoProvider = Provider<UserInfo>((ref) {
  final userInfoState = ref.watch(userInfoStateProvider);
  return userInfoState;
});

final userInfoStateProvider =
    StateNotifierProvider<UserInfoState, UserInfo>((ref) => UserInfoState());

class UserInfoState extends StateNotifier<UserInfo> {
  UserInfoState()
      : super(
          UserInfo(
            nickname: '',
            school: '',
            city: '서울',
            country: '강남구',
          ),
        );

  void setUserNickname(String name) => state = state.copyWith(nickname: name);

  void setUserSchool(String school) => state = state.copyWith(school: school);

  void setUserCity(String city) => state = state.copyWith(city: city);

  void setUserCountry(String country) =>
      state = state.copyWith(country: country);
}
