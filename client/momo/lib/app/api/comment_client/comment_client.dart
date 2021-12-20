import 'package:dio/dio.dart';
import 'package:momo/app/model/comment/comment.dart';
import 'package:momo/app/model/comment/comment_request.dart';
import 'package:momo/app/model/comment/comment_response.dart';
import 'package:retrofit/retrofit.dart';

part 'comment_client.g.dart';

@RestApi(baseUrl: 'http://gunimon.iptime.org:8100/api')
abstract class CommentClient {
  factory CommentClient(
    Dio dio, {
    String baseUrl,
  }) = _CommentClient;

  @POST('/comment')
  Future<Comment> createComment(@Body() CommentRequest commentRequest);

  @GET('/comments/paging')
  Future<CommentResponse> getComments(
    @Query('page') int page,
    @Query('postId') int postId,
    @Query('size') int size,
  );
}
