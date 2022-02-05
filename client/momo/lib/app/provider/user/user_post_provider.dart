import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/user/user_post_list.dart';
import 'package:momo/app/repository/user_repository.dart';
import 'package:momo/app/util/constant.dart';

final userPostStateProvider =
    StateNotifierProvider<UserPostState, UserPostList>((ref) {
  final userRepository = ref.watch(userRepositoryProvider);
  return UserPostState(userRepository: userRepository);
});

class UserPostState extends StateNotifier<UserPostList> {
  UserPostState({
    required this.userRepository,
  }) : super(
          UserPostList(
            hasNext: true,
            nextPage: 0,
            posts: [],
          ),
        );

  final UserRepository userRepository;

  Future<void> getPosts(int page) async {
    final response = await userRepository.getMyPosts(page++);
    state = state.copyWith(
      posts: [...response],
      hasNext: response.length == pageSize,
      nextPage: page,
    );
  }
}
