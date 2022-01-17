import 'package:flutter_riverpod/flutter_riverpod.dart';

//  약관 동의
final termsCheckProvider =
    StateNotifierProvider<TermsCheck, List<bool>>((ref) => TermsCheck());

// 전체 동의
final termsAllCheckStateProvider = StateProvider<bool>((ref) => false);

final termsAllCheckProvider = Provider<bool>((ref) {
  final termsAll = ref.watch(termsAllCheckStateProvider);
  final termsCheck = ref.watch(termsCheckProvider);

  if (termsAll || (termsCheck[0] && termsCheck[1] && termsCheck[2])) {
    return true;
  }
  return false;
});

// 버튼 활성화
final isAgreeTermsProvider = Provider<bool>((ref) {
  final checkAll = ref.watch(termsAllCheckProvider);

  final termsChecks = ref.watch(termsCheckProvider);

  if (checkAll) {
    return true;
  } else {
    if (termsChecks[0] && termsChecks[1]) {
      return true;
    }
    return false;
  }
});

class TermsCheck extends StateNotifier<List<bool>> {
  TermsCheck() : super([false, false, false]);

  void toggleTerms(int index) {
    state = [
      for (var i = 0; i < state.length; i++)
        if (i == index) !state[index] else state[i]
    ];
  }

  void checkAll(bool check) => state = [check, check, check];

  bool isCheckAll() {
    for (int i = 0; i < state.length; i++) {
      if (!state[i]) {
        return false;
      }
    }
    return true;
  }
}
