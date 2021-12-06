import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/group/group_detail.dart';
import 'package:momo/app/repository/group_repository.dart';

final groupDetailProvider =
    Provider.family.autoDispose<GroupDetail, GroupDetail>(
  (ref, groupDetail) {
    final groupDetailState = ref.watch(groupDetailStateProvider(groupDetail));
    return groupDetailState;
  },
);

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
        isParticipant: true, participantCnt: state.participantCnt + 1);
    return response;
  }
}
