import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/comment/comment.dart';
import 'package:momo/app/model/comment/comment_request.dart';
import 'package:momo/app/repository/comment_repository.dart';

final commentContentsCheckProvider =
    Provider.family.autoDispose<bool, int>((ref, postId) {
  final commentState = ref.watch(commentRequestProvider(postId));
  return commentState.contents.isNotEmpty ? true : false;
});

final commentRequestProvider =
    Provider.family.autoDispose<CommentRequest, int>((ref, postId) {
  final commentRequestState = ref.watch(commentRequestStateProvider(postId));
  return commentRequestState;
});

final commentRequestStateProvider = StateNotifierProvider.family
    .autoDispose<CommentRequestState, CommentRequest, int>((ref, postId) {
  final repository = ref.watch(commentRepositoryProvider);
  return CommentRequestState(postId, repository: repository);
});

class CommentRequestState extends StateNotifier<CommentRequest> {
  CommentRequestState(
    int postId, {
    required this.repository,
  }) : super(CommentRequest(postId: postId, contents: ''));

  final CommentRepository repository;

  void setContents(String contents) =>
      state = state.copyWith(contents: contents);

  void resetContents() => state = state.copyWith(contents: '');

  Future<Comment> createComment() async {
    final comment = await repository.createComment(state);
    return comment;
  }
}
