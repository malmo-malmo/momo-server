import 'package:dio/dio.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/model/post/post_detail.dart';
import 'package:momo/app/model/post/post_request.dart';
import 'package:momo/app/util/constant.dart';
import 'package:retrofit/retrofit.dart';

part 'post_client.g.dart';

@RestApi(baseUrl: baseUrl)
abstract class PostClient {
  factory PostClient(
    Dio dio, {
    String baseUrl,
  }) = _PostClient;

  @POST('/post')
  Future<dynamic> createPost(
    @Body() PostRequest postRequest,
  );

  @GET('/post/{postId}')
  Future<PostDetail> getPostDetail(
    @Path() int postId,
  );

  @GET('/posts/paging')
  Future<List<Post>> getPosts(
    @Query('groupId') int groupId,
    @Query('page') int page,
    @Query('size') int size,
    @Query('type') String type,
  );
}
