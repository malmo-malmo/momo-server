import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/group/group_like_request.dart';
import 'package:momo/app/provider/group/home_group_dto.dart';
import 'package:momo/app/repository/group_repository.dart';
import 'package:momo/app/repository/user_repository.dart';

final homeGroupStateProvider =
    StateNotifierProvider.autoDispose<HomeGroupState, HomeGroupDto>((ref) {
  final groupRepository = ref.watch(groupRepositoryProvider);
  final userRepository = ref.watch(userRepositoryProvider);
  return HomeGroupState(
    groupRepository: groupRepository,
    userRepository: userRepository,
  );
});

class HomeGroupState extends StateNotifier<HomeGroupDto> {
  HomeGroupState({
    required this.groupRepository,
    required this.userRepository,
  }) : super(
          HomeGroupDto(
            isLoading: true,
            categoryGroup: [],
            districtGroup: [],
            universityGroup: [],
          ),
        );

  final GroupRepository groupRepository;
  final UserRepository userRepository;

  Future<void> getGroups() async {
    state = state.copyWith(isLoading: true);
    final categoryGroupResponse =
        groupRepository.getGroupsByCategories(0, pageSize: 10);
    final districtGroupResponse =
        groupRepository.getGroupsByDistrict(0, pageSize: 10);
    final universityGroupResponse =
        groupRepository.getGroupsByUniversity(0, pageSize: 10);

    final groupResponse = await Future.wait([
      categoryGroupResponse,
      districtGroupResponse,
      universityGroupResponse,
    ]);

    state = state.copyWith(
      categoryGroup: [...groupResponse[0]],
      districtGroup: [...groupResponse[1]],
      universityGroup: [...groupResponse[2]],
      isLoading: false,
    );
  }

  Future<void> createGroupLike(int groupId) async {
    await userRepository.createGroupLike(GroupLikeRequest(groupId: groupId));
    state = state.copyWith(
      categoryGroup: [
        ...state.categoryGroup
            .map(
              (e) => e.id == groupId ? e.copyWith(favoriteGroup: true) : e,
            )
            .toList()
      ],
      districtGroup: [
        ...state.districtGroup
            .map(
              (e) => e.id == groupId ? e.copyWith(favoriteGroup: true) : e,
            )
            .toList()
      ],
      universityGroup: [
        ...state.universityGroup
            .map(
              (e) => e.id == groupId ? e.copyWith(favoriteGroup: true) : e,
            )
            .toList()
      ],
    );
  }

  Future<void> deleteGroupLike(int groupId) async {
    await userRepository.deleteGroupLike(groupId);
    state = state.copyWith(
      categoryGroup: [
        ...state.categoryGroup
            .map(
              (e) => e.id == groupId ? e.copyWith(favoriteGroup: false) : e,
            )
            .toList()
      ],
      districtGroup: [
        ...state.districtGroup
            .map(
              (e) => e.id == groupId ? e.copyWith(favoriteGroup: false) : e,
            )
            .toList()
      ],
      universityGroup: [
        ...state.universityGroup
            .map(
              (e) => e.id == groupId ? e.copyWith(favoriteGroup: false) : e,
            )
            .toList()
      ],
    );
  }

  void groupLikeCallBack({
    required int groupId,
    required bool favorite,
  }) {
    state = state.copyWith(
      categoryGroup: [
        ...state.categoryGroup
            .map(
              (e) => e.id == groupId ? e.copyWith(favoriteGroup: favorite) : e,
            )
            .toList()
      ],
      districtGroup: [
        ...state.districtGroup
            .map(
              (e) => e.id == groupId ? e.copyWith(favoriteGroup: favorite) : e,
            )
            .toList()
      ],
      universityGroup: [
        ...state.universityGroup
            .map(
              (e) => e.id == groupId ? e.copyWith(favoriteGroup: favorite) : e,
            )
            .toList()
      ],
    );
  }
}
