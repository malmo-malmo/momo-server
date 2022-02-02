import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/api/api_provider.dart';
import 'package:momo/app/api/form_data_dio.dart';
import 'package:momo/app/api/post_client/post_client.dart';
import 'package:momo/app/model/enum/post_type.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/model/post/post_detail.dart';
import 'package:momo/app/model/post/post_request.dart';
import 'package:momo/app/util/constant.dart';

final postRepositoryProvider = Provider<PostRepository>((ref) {
  final postClient = ref.watch(postClientProvider);
  final formDataDio = ref.watch(formDataDioProvider);
  return PostRepository(
    postClient: postClient,
    formDataDio: formDataDio,
  );
});

class PostRepository {
  final PostClient postClient;
  final FormDataDio formDataDio;

  PostRepository({
    required this.postClient,
    required this.formDataDio,
  });

  Future<PostDetail> createPost(PostRequest postRequest) async {
    final response = await formDataDio.createPost(postRequest);
    return response;
  }

  Future<List<Post>> getPosts({
    required int page,
    required int groupId,
  }) async {
    final response = await postClient.getPosts(
        groupId, page, pageSize, PostType.normal.name.toUpperCase());
    return response;
  }

  Future<List<Post>> getNotices({
    required int page,
    required int groupId,
  }) async {
    final response = await postClient.getPosts(
        groupId, page, pageSize, PostType.notice.name.toUpperCase());
    return response;
  }

  Future<PostDetail> getPostDetail(int postId) async {
    final response = await postClient.getPostDetail(postId);
    return response;
  }
}
