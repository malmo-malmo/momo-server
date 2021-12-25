import 'package:flutter_riverpod/flutter_riverpod.dart';

final isCheckMemberProvider = Provider.family.autoDispose<bool, int>((ref, id) {
  final memberState = ref.watch(memberAdminStateProvider(id));
  for (int i = 0; i < memberState.length; i++) {
    if (memberState[i]) {
      return true;
    }
  }
  return false;
});

final memberAdminStateProvider = StateNotifierProvider.family
    .autoDispose<MemberAdminState, List<bool>, int>(
        (ref, id) => MemberAdminState(id));

class MemberAdminState extends StateNotifier<List<bool>> {
  MemberAdminState(int id) : super(List.generate(id, (index) => false));

  void checkMember(int index) {
    state = [
      for (int i = 0; i < state.length; i++)
        if (i == index) true else false
    ];
  }
}
