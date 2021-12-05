import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/api/api_provider.dart';
import 'package:momo/app/api/post_client/post_client.dart';
import 'package:momo/app/model/enum/post_type.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/model/post/post_detail.dart';
import 'package:momo/app/util/constant.dart';

final postRepositoryProvider = Provider<PostRepository>((ref) {
  final postClient = ref.watch(postClientProvider);
  return PostRepository(postClient: postClient);
});

class PostRepository {
  final PostClient postClient;

  PostRepository({required this.postClient});

  Future<List<Post>> getPosts({
    required int page,
    required int groupId,
  }) async {
    final response = await postClient.getPosts(
        groupId, page, pageSize, PostType.normal.postTypeToString);
    return response;
  }

  Future<List<Post>> getNotices({
    required int page,
    required int groupId,
  }) async {
    final response = await postClient.getPosts(
        groupId, page, pageSize, PostType.notice.postTypeToString);
    return response;
  }

  Future<PostDetail> getPostDetail(int postId) async {
    final response = await postClient.getPostDetail(postId);
    return response;
  }
}
