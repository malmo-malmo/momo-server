import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/group/group_like_request.dart';
import 'package:momo/app/model/group/wish_group_dto.dart';
import 'package:momo/app/repository/user_repository.dart';

final wishGroupListProvider =
    StateNotifierProvider<WishGroupListState, WishGroupDto>((ref) {
  final userRepository = ref.watch(userRepositoryProvider);
  return WishGroupListState(userRepository: userRepository);
});

class WishGroupListState extends StateNotifier<WishGroupDto> {
  WishGroupListState({required this.userRepository})
      : super(WishGroupDto(groups: [], isLoading: true));

  final UserRepository userRepository;

  Future<void> getFavoriteGroups() async {
    state = state.copyWith(isLoading: true);
    try {
      final response = await userRepository.getFavoriteGroups();
      state = state.copyWith(
        groups: [
          ...state.groups,
          ...response.map((e) => e.groupCardResponse).toList(),
        ],
        isLoading: false,
      );
    } catch (e) {
      state = state.copyWith(
        error: e.toString(),
        isLoading: false,
      );
    }
  }

  Future<dynamic> createLike(int groupId) async {
    state = state.copyWith(
      groups: [
        ...state.groups
            .map(
              (e) => e.id == groupId ? e.copyWith(favoriteGroup: true) : e,
            )
            .toList()
      ],
    );
    try {
      await userRepository.createGroupLike(GroupLikeRequest(groupId: groupId));
    } catch (e) {
      state = state.copyWith(
        groups: [
          ...state.groups
              .map(
                (e) => e.id == groupId ? e.copyWith(favoriteGroup: false) : e,
              )
              .toList()
        ],
      );
    }
  }

  Future<dynamic> deleteLike(int groupId) async {
    state = state.copyWith(
      groups: [
        ...state.groups
            .map(
              (e) => e.id == groupId ? e.copyWith(favoriteGroup: false) : e,
            )
            .toList()
      ],
    );
    try {
      await userRepository.createGroupLike(GroupLikeRequest(groupId: groupId));
    } catch (e) {
      state = state.copyWith(
        groups: [
          ...state.groups
              .map(
                (e) => e.id == groupId ? e.copyWith(favoriteGroup: true) : e,
              )
              .toList()
        ],
      );
    }
  }
}
