import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/comment/comment_list_dto.dart';
import 'package:momo/app/model/comment/comment_request.dart';
import 'package:momo/app/repository/comment_repository.dart';
import 'package:momo/app/util/constant.dart';

final commentListProvider =
    Provider.family.autoDispose<CommentListDto, int>((ref, postId) {
  final commentListState = ref.watch(commentListStateProvider(postId));
  return commentListState;
});

final commentListStateProvider = StateNotifierProvider.family
    .autoDispose<CommentListState, CommentListDto, int>((ref, postId) {
  final commentRepository = ref.watch(commentRepositoryProvider);
  return CommentListState(postId: postId, commentRepository: commentRepository);
});

class CommentListState extends StateNotifier<CommentListDto> {
  CommentListState({
    required this.postId,
    required this.commentRepository,
  }) : super(
          CommentListDto(
            comments: [],
            nextPage: 0,
            hasNext: true,
            commentCnt: 0,
          ),
        );

  final CommentRepository commentRepository;
  final int postId;

  Future<void> getComments(int page) async {
    final response =
        await commentRepository.getComments(page: page++, postId: postId);
    state = state.copyWith(
      comments: [
        ...state.comments,
        ...response.comments,
      ],
      hasNext: response.comments.length == pageSize,
      nextPage: page,
      commentCnt: response.commentCnt,
    );
  }

  Future<void> createComment(String contents) async {
    final comment = await commentRepository
        .createComment(CommentRequest(postId: postId, contents: contents));
    state = state.copyWith(
      comments: [
        comment,
        ...state.comments,
      ],
      commentCnt: state.commentCnt + 1,
    );
  }
}
