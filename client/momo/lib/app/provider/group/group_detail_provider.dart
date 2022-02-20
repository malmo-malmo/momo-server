import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/group/group_detail.dart';
import 'package:momo/app/repository/group_repository.dart';

final groupDetailStateProvider = StateNotifierProvider.family
    .autoDispose<GroupDetailState, GroupDetail, GroupDetail>(
  (ref, groupDetail) {
    final groupRepository = ref.watch(groupRepositoryProvider);
    return GroupDetailState(groupDetail, groupRepository: groupRepository);
  },
);

final groupDetailFutureProvider =
    FutureProvider.family.autoDispose<GroupDetail, int>(
  (ref, groupId) async {
    final repository = ref.watch(groupRepositoryProvider);
    final response = await repository.getGroupDetail(groupId);
    return response;
  },
);

class GroupDetailState extends StateNotifier<GroupDetail> {
  GroupDetailState(
    GroupDetail state, {
    required this.groupRepository,
  }) : super(state);

  final GroupRepository groupRepository;

  Future<dynamic> participantGroup() async {
    final response = await groupRepository.participantGroup(state.id);
    state = state.copyWith(
      participant: true,
      participantCnt: state.participantCnt + 1,
    );
    return response;
  }

  Future<dynamic> endGroup() async {
    final response = await groupRepository.endGroup(state.id);
    state = state.copyWith(end: true);
    return response;
  }

  Future<dynamic> manageGroup({required int id, required int userId}) async {
    final response = await groupRepository.manageGroup(id: id, userId: userId);
    state = state.copyWith(managerId: userId);
    return response;
  }
}
