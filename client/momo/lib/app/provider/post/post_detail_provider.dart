import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/post/post_detail.dart';
import 'package:momo/app/repository/post_repository.dart';

final postDetailCommentCntStateProvider =
    StateProvider.autoDispose<int>((ref) => 0);

final postDetailProvider =
    Provider.family.autoDispose<PostDetail, PostDetail>((ref, postDetail) {
  final postDetailState = ref.watch(postDetailStateProvider(postDetail));
  return postDetailState;
});

final postDetailStateProvider = StateNotifierProvider.family
    .autoDispose<PostDetailState, PostDetail, PostDetail>((ref, postDetail) {
  final postRepository = ref.watch(postRepositoryProvider);
  return PostDetailState(postDetail, postRepository: postRepository);
});

final postDetailFutureProvider =
    FutureProvider.family.autoDispose<PostDetail, int>((ref, postId) async {
  final repository = ref.watch(postRepositoryProvider);
  final response = await repository.getPostDetail(postId);
  return response;
});

class PostDetailState extends StateNotifier<PostDetail> {
  PostDetailState(PostDetail state, {required this.postRepository})
      : super(state);

  final PostRepository postRepository;
}
