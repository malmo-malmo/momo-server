import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/post/post_request.dart';
import 'package:momo/app/repository/post_repository.dart';
import 'package:momo/app/routes/custom_arg/post_request_arg.dart';

final postRequestCheckProvider =
    Provider.family.autoDispose<bool, PostRequestArg>((ref, postRequestArg) {
  final postRequest = ref.watch(postRequestStateProvider(postRequestArg));

  if (postRequest.contents.isNotEmpty &&
      postRequest.title.isNotEmpty &&
      postRequest.images.isNotEmpty) {
    return true;
  }
  return false;
});

final postRequestStateProvider = StateNotifierProvider.family
    .autoDispose<PostRequestState, PostRequest, PostRequestArg>(
        (ref, postRequestArg) {
  final repository = ref.watch(postRepositoryProvider);
  return PostRequestState(
    postRequestArg,
    repository: repository,
  );
});

class PostRequestState extends StateNotifier<PostRequest> {
  PostRequestState(
    PostRequestArg postRequestArg, {
    required this.repository,
  }) : super(
          PostRequest(
            contents: '',
            title: '',
            groupId: postRequestArg.groupId,
            images: [],
            typeName: postRequestArg.postType.name.toUpperCase(),
          ),
        );

  final PostRepository repository;

  void setContents(String text) {
    state = state.copyWith(contents: text);
  }

  void setImages(List<String> images) {
    state = state.copyWith(images: [...images]);
  }

  void setTitle(String title) {
    state = state.copyWith(title: title);
  }

  void deleteImg(String img) {
    state =
        state.copyWith(images: state.images.where((e) => e != img).toList());
  }

  Future<dynamic> createPost() async {
    final response = await repository.createPost(state);
    return response;
  }
}
