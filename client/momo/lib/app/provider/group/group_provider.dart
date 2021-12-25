import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/group/group_info.dart';

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
