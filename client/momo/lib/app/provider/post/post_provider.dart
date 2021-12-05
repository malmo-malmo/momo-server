import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/post/post.dart';

final postProvider = Provider.family.autoDispose<Post, Post>((ref, post) {
  final postState = ref.watch(postStateProvider(post));
  return postState;
});

final postStateProvider = StateNotifierProvider.family
    .autoDispose<PostState, Post, Post>((ref, post) {
  return PostState(post);
});

class PostState extends StateNotifier<Post> {
  PostState(Post state) : super(state);

  void addComment() => state = state.copyWith(commentCnt: state.commentCnt + 1);
}
