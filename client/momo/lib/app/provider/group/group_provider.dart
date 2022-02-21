import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/model/group/group_like_request.dart';
import 'package:momo/app/model/group/group_list_dto.dart';
import 'package:momo/app/repository/group_repository.dart';
import 'package:momo/app/repository/user_repository.dart';
import 'package:momo/app/util/constant.dart';

final groupStateProvider = StateNotifierProvider.family
    .autoDispose<GroupState, GroupInfo, GroupInfo>((ref, groupInfo) {
  return GroupState(groupInfo);
});

class GroupState extends StateNotifier<GroupInfo> {
  GroupState(GroupInfo state) : super(state);

  void addParticipantCnt() =>
      state = state.copyWith(participantCnt: state.participantCnt + 1);

  void subParticipantCnt() =>
      state = state.copyWith(participantCnt: state.participantCnt - 1);
}

final categoryGroupListProvider =
    StateNotifierProvider.autoDispose<GroupListState, GroupListDto>((ref) {
  final userRepository = ref.watch(userRepositoryProvider);
  final groupRepository = ref.watch(groupRepositoryProvider);
  return GroupListState(
    userRepository: userRepository,
    groupRepository: groupRepository,
  );
});

final groupListProvider =
    StateNotifierProvider.autoDispose<GroupListState, GroupListDto>((ref) {
  final userRepository = ref.watch(userRepositoryProvider);
  final groupRepository = ref.watch(groupRepositoryProvider);
  return GroupListState(
    userRepository: userRepository,
    groupRepository: groupRepository,
  );
});

class GroupListState extends StateNotifier<GroupListDto> {
  GroupListState({
    required this.userRepository,
    required this.groupRepository,
  }) : super(
          GroupListDto(
            groups: [],
            hasNext: true,
            nextPage: 0,
          ),
        );
  final UserRepository userRepository;
  final GroupRepository groupRepository;

  Future<void> getGroupsByCategoriesDetail(
      int page, List<String> categories) async {
    final response = await groupRepository
        .getGroupBySearch(page, categories: categories, cities: []);
    state = state.copyWith(
      groups: [
        ...state.groups,
        ...response,
      ],
      hasNext: response.length == pageSize,
      nextPage: page,
    );
  }

  void resetGroups() {
    state = state.copyWith(
      groups: [],
      hasNext: true,
      nextPage: 0,
    );
  }

  Future<void> getGroupsByDistrict(int page) async {
    final response = await groupRepository.getGroupsByDistrict(page++);
    state = state.copyWith(
      groups: [
        ...state.groups,
        ...response,
      ],
      hasNext: response.length == pageSize,
      nextPage: page,
    );
  }

  Future<void> getGroupsByCategories(int page) async {
    final response = await groupRepository.getGroupsByCategories(page++);
    state = state.copyWith(
      groups: [
        ...state.groups,
        ...response,
      ],
      hasNext: response.length == pageSize,
      nextPage: page,
    );
  }

  Future<void> getGroupsByUniversity(int page) async {
    final response = await groupRepository.getGroupsByUniversity(page++);
    state = state.copyWith(
      groups: [
        ...state.groups,
        ...response,
      ],
      hasNext: response.length == pageSize,
      nextPage: page,
    );
  }

  Future<void> createLike(int groupId) async {
    await userRepository.createGroupLike(GroupLikeRequest(groupId: groupId));

    state = state.copyWith(
      groups: [
        ...state.groups
            .map((e) => e.id == groupId ? e.copyWith(favoriteGroup: true) : e)
            .toList()
      ],
    );
  }

  Future<void> deleteLike(int groupId) async {
    await userRepository.createGroupLike(GroupLikeRequest(groupId: groupId));

    state = state.copyWith(
      groups: [
        ...state.groups
            .map((e) => e.id == groupId ? e.copyWith(favoriteGroup: false) : e)
            .toList()
      ],
    );
  }
}
