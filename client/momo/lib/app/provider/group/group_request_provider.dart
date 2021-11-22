import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/group/group_request.dart';

final groupRequestCheckProvider = Provider.autoDispose<bool>((ref) {
  final groupRequest = ref.watch(groupRequestStateProvider);

  if (groupRequest.name.isNotEmpty &&
      groupRequest.category.isNotEmpty &&
      groupRequest.city.isNotEmpty &&
      groupRequest.district.isNotEmpty &&
      groupRequest.imageUrl.isNotEmpty &&
      groupRequest.city.isNotEmpty &&
      groupRequest.introduction.isNotEmpty &&
      groupRequest.startDate.isNotEmpty &&
      groupRequest.recruitmentCnt != 0) {
    return true;
  }
  return false;
});

final groupRequestProvider = Provider.autoDispose<GroupRequest>((ref) {
  final groupRequestState = ref.watch(groupRequestStateProvider);
  return groupRequestState;
});

final groupRequestStateProvider =
    StateNotifierProvider.autoDispose<GroupRequestState, GroupRequest>(
        (ref) => GroupRequestState());

class GroupRequestState extends StateNotifier<GroupRequest> {
  GroupRequestState()
      : super(
          GroupRequest(
            name: '',
            category: '',
            city: '',
            district: '',
            imageUrl: '',
            introduction: '',
            recruitmentCnt: 0,
            startDate: '',
            isUniversity: false,
            isOffline: false,
          ),
        );

  void setMeetName(String name) => state = state.copyWith(name: name);

  void setCategory(String category) =>
      state = state.copyWith(category: category);

  void setCity(String city) => state = state.copyWith(city: city);

  void setOnOff(bool isOffline) => state = state.copyWith(isOffline: isOffline);

  void setRecruitmentCnt(String recruitmentCnt) =>
      state = state.copyWith(recruitmentCnt: int.parse(recruitmentCnt));

  void setStartDate(String startDate) =>
      state = state.copyWith(startDate: startDate);

  void setIntroduction(String introduction) =>
      state = state.copyWith(introduction: introduction);

  void setSchool(bool isUniversity) =>
      state = state.copyWith(isUniversity: isUniversity);

  void setDistrict(String district) =>
      state = state.copyWith(district: district);

  void setImageUrl(String imageUrl) =>
      state = state.copyWith(imageUrl: imageUrl);
}
