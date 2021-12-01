import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/user/user_info_request.dart';

final userInfoCheckProvider = Provider<bool>((ref) {
  final userInfo = ref.watch(userInfoProvider);
  if (userInfo.nickname.isNotEmpty &&
      userInfo.university.isNotEmpty &&
      userInfo.city.isNotEmpty &&
      userInfo.district.isNotEmpty) {
    return true;
  }
  return false;
});

final userInfoProvider = Provider<UserInfoRequest>((ref) {
  final userInfoState = ref.watch(userInfoStateProvider);
  return userInfoState;
});

final userInfoStateProvider =
    StateNotifierProvider<UserInfoState, UserInfoRequest>(
        (ref) => UserInfoState());

class UserInfoState extends StateNotifier<UserInfoRequest> {
  UserInfoState()
      : super(
          UserInfoRequest(
            nickname: '',
            university: '',
            city: '서울',
            district: '강남구',
          ),
        );

  void setUserNickname(String nickname) =>
      state = state.copyWith(nickname: nickname);

  void setUserUniversity(String university) =>
      state = state.copyWith(university: university);

  void setUserCity(String city) => state = state.copyWith(city: city);

  void setUserDistrict(String district) =>
      state = state.copyWith(district: district);
}
