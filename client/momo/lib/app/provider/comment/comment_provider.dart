import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/comment/comment.dart';
import 'package:momo/app/repository/comment_repository.dart';

final commentListProvider = Provider.family
    .autoDispose<List<Comment>, List<Comment>>((ref, commentList) {
  final commentListState = ref.watch(commentListStateProvider(commentList));
  return commentListState;
});

final commentListStateProvider = StateNotifierProvider.family
    .autoDispose<CommentState, List<Comment>, List<Comment>>(
        (ref, commentList) {
  final commentRepository = ref.watch(commentRepositoryProvider);
  return CommentState(commentList, commentRepository: commentRepository);
});

class CommentState extends StateNotifier<List<Comment>> {
  CommentState(List<Comment> state, {required this.commentRepository})
      : super(state);

  final CommentRepository commentRepository;

  void addComment(Comment comment) => state = [comment, ...state];
}
