import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/group/total_group_list.dart';
import 'package:momo/app/repository/user_repository.dart';

final totalGroupStateProvider =
    StateNotifierProvider<TotalGroupState, TotalGroupList>((ref) {
  final userRepository = ref.watch(userRepositoryProvider);
  return TotalGroupState(userRepository: userRepository);
});

class TotalGroupState extends StateNotifier<TotalGroupList> {
  TotalGroupState({
    required this.userRepository,
  }) : super(TotalGroupList(isLoading: true, groups: []));

  final UserRepository userRepository;

  Future<void> getTotalGroups() async {
    state = state.copyWith(isLoading: true);
    final response = await userRepository.getTotalGroups();
    state = state.copyWith(
      isLoading: false,
      groups: [...response],
    );
  }
}
