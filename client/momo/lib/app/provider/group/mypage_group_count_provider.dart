import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/group/my_page_group_counts.dart';
import 'package:momo/app/repository/user_repository.dart';

final mypageGroupCountProvider =
    StateNotifierProvider.autoDispose<MypageGroupCountState, MypageGroupCounts>(
        (ref) {
  final userRepository = ref.watch(userRepositoryProvider);
  return MypageGroupCountState(userRepository: userRepository);
});

class MypageGroupCountState extends StateNotifier<MypageGroupCounts> {
  MypageGroupCountState({
    required this.userRepository,
  }) : super(
          MypageGroupCounts(
            isLoading: true,
            groupCount: 0,
            favoriteGroupCount: 0,
            badgeCount: 0,
          ),
        );

  final UserRepository userRepository;

  Future<void> getGroupCounts() async {
    state = state.copyWith(isLoading: true);
    final groupCountResponse = userRepository.getGroupCount();
    final favoriteGroupCountResponse = userRepository.getFavoriteGroupCount();
    final counts =
        await Future.wait([groupCountResponse, favoriteGroupCountResponse]);

    state = state.copyWith(
      isLoading: false,
      groupCount: counts[0].count,
      favoriteGroupCount: counts[1].count,
    );
  }
}
