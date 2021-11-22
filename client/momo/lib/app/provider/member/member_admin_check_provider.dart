import 'package:flutter_riverpod/flutter_riverpod.dart';

final isCheckMemberProvider =
    Provider.family.autoDispose<bool, int>((ref, num) {
  final memberState = ref.watch(memberAdminProvider(num));
  for (int i = 0; i < memberState.length; i++) {
    if (memberState[i]) {
      return true;
    }
  }
  return false;
});

final memberAdminProvider =
    Provider.family.autoDispose<List<bool>, int>((ref, num) {
  final memberAdminState = ref.watch(memberAdminStateProvider(num));
  return memberAdminState;
});

final memberAdminStateProvider =
    StateNotifierProvider.family.autoDispose<MemberAdminState, List<bool>, int>(
  (ref, num) => MemberAdminState(num),
);

class MemberAdminState extends StateNotifier<List<bool>> {
  MemberAdminState(int num) : super(List.generate(num, (index) => false));

  void checkMember(int index) {
    state = [
      for (int i = 0; i < state.length; i++)
        if (i == index) true else false
    ];
  }
}
