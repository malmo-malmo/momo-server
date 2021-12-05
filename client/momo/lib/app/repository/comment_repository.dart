import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/api/api_provider.dart';
import 'package:momo/app/api/comment_client/comment_client.dart';
import 'package:momo/app/model/comment/comment.dart';
import 'package:momo/app/model/comment/comment_request.dart';
import 'package:momo/app/model/comment/comment_response.dart';
import 'package:momo/app/util/constant.dart';

final commentRepositoryProvider = Provider<CommentRepository>((ref) {
  final commentClient = ref.watch(commnetClientProvider);
  return CommentRepository(commentClient: commentClient);
});

class CommentRepository {
  final CommentClient commentClient;

  CommentRepository({required this.commentClient});

  Future<Comment> createComment(CommentRequest commentRequest) async {
    final response = await commentClient.createComment(commentRequest);
    return response;
  }

  Future<CommentResponse> getComments({
    required int page,
    required int postId,
  }) async {
    final response = await commentClient.getComments(page, postId, pageSize);
    return response;
  }
}
