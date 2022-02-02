import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/post/post.dart';
import 'package:momo/app/model/post/post_detail.dart';
import 'package:momo/app/provider/post/post_list_dto.dart';
import 'package:momo/app/repository/post_repository.dart';
import 'package:momo/app/util/constant.dart';

final noticeListProvider = StateNotifierProvider.family
    .autoDispose<PostListState, PostListDto, int>((ref, groupId) {
  final postRepository = ref.watch(postRepositoryProvider);
  return PostListState(
    groupId: groupId,
    postRepository: postRepository,
  );
});

final postListProvider = StateNotifierProvider.family
    .autoDispose<PostListState, PostListDto, int>((ref, groupId) {
  final postRepository = ref.watch(postRepositoryProvider);
  return PostListState(
    groupId: groupId,
    postRepository: postRepository,
  );
});

class PostListState extends StateNotifier<PostListDto> {
  PostListState({
    required this.groupId,
    required this.postRepository,
  }) : super(
          PostListDto(
            posts: [],
            nextPage: 0,
            hasNext: true,
          ),
        );

  final int groupId;
  final PostRepository postRepository;

  Future<void> getNotices(int page) async {
    final response =
        await postRepository.getNotices(page: page++, groupId: groupId);
    state = state.copyWith(
      posts: [...state.posts, ...response],
      hasNext: response.length == pageSize,
      nextPage: page,
    );
  }

  Future<void> getPosts(int page) async {
    final response =
        await postRepository.getPosts(page: page++, groupId: groupId);
    state = state.copyWith(
      posts: [...state.posts, ...response],
      hasNext: response.length == pageSize,
      nextPage: page,
    );
  }

  void addPost(PostDetail postDetail) {
    state = state.copyWith(
      posts: [
        Post(
          id: postDetail.id,
          authorNickname: postDetail.authorNickname,
          title: postDetail.title,
          contents: postDetail.contents,
          commentCnt: 0,
          createdDate: postDetail.createdDate,
        ),
        ...state.posts
      ],
    );
  }
}
