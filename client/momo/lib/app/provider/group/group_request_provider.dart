import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/group/group_request.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/provider/city_result_provider.dart';
import 'package:momo/app/repository/group_repository.dart';

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
    StateNotifierProvider.autoDispose<GroupRequestState, GroupRequest>((ref) {
  final repository = ref.watch(groupRepositoryProvider);
  return GroupRequestState(repository: repository);
});

class GroupRequestState extends StateNotifier<GroupRequest> {
  GroupRequestState({required this.repository})
      : super(
          GroupRequest(
            name: '',
            category: '',
            city: 'SEOUL',
            district: '강남구',
            imageUrl: '',
            introduction: '',
            recruitmentCnt: 0,
            startDate: '',
            isUniversity: true,
            isOffline: false,
          ),
        );

  final GroupRepository repository;

  void setGroupName(String name) => state = state.copyWith(name: name);

  void setGroupCategory(int index) =>
      state = state.copyWith(category: categoryCodeNamePair[index].code);

  void setCity(String city) => state = state.copyWith(
      city:
          cityCodeNamePair.where((element) => element.name == city).first.code);

  String get userCity => cityCodeNamePair
      .where((element) => element.code == state.city)
      .first
      .name;

  void setOnOff(bool isOffline) => state = state.copyWith(isOffline: isOffline);

  void setRecruitmentCnt(String recruitmentCnt) =>
      state = state.copyWith(recruitmentCnt: int.parse(recruitmentCnt));

  void setStartDate(String startDate) =>
      state = state.copyWith(startDate: startDate);

  void setIntroduction(String introduction) =>
      state = state.copyWith(introduction: introduction);

  void setUniversity(bool isUniversity) =>
      state = state.copyWith(isUniversity: isUniversity);

  void setDistrict(String district) =>
      state = state.copyWith(district: district);

  void setImageUrl(String imageUrl) =>
      state = state.copyWith(imageUrl: imageUrl);

  Future<dynamic> createGroup() async {
    final response = await repository.createGroup(state);
    return response;
  }
}
