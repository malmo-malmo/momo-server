import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/user/participant_user.dart';
import 'package:momo/app/repository/group_repository.dart';

final isCheckUserProvider = Provider.family.autoDispose<int, int>((ref, count) {
  final checkState = ref.watch(participantCheckProvider(count));
  for (int i = 0; i < checkState.length; i++) {
    if (checkState[i]) {
      return i;
    }
  }
  return -1;
});

final participantCheckProvider =
    Provider.family.autoDispose<List<bool>, int>((ref, count) {
  final participantState = ref.watch(participantsCheckStateProvider(count));
  return participantState;
});

final participantsCheckStateProvider = StateNotifierProvider.family
    .autoDispose<CheckState, List<bool>, int>(
        (ref, count) => CheckState(count));

final participantUsersProvider = FutureProvider.family
    .autoDispose<List<ParticipantUser>, int>((ref, groupId) async {
  final repository = ref.watch(groupRepositoryProvider);
  final response = await repository.getParticipantUsers(groupId);
  return response;
});

class CheckState extends StateNotifier<List<bool>> {
  CheckState(int count) : super(List.generate(count, (index) => false));

  void check(int index) => state = [
        for (int i = 0; i < state.length; i++)
          if (i == index) true else false
      ];
}
