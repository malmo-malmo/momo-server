import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/post/post_request.dart';
import 'package:momo/app/model/enum/post_type.dart';
import 'package:momo/app/repository/post_repository.dart';
import 'package:momo/app/routes/custom_arg/post_request_arg.dart';

final postRequestCheckProvider =
    Provider.family.autoDispose<bool, PostRequestArg>((ref, postRequestArg) {
  final postRequest = ref.watch(postRequestProvider(postRequestArg));

  if (postRequest.contents.isNotEmpty &&
      postRequest.title.isNotEmpty &&
      postRequest.imageUrls.isNotEmpty) {
    return true;
  }
  return false;
});

final postRequestProvider = Provider.family
    .autoDispose<PostRequest, PostRequestArg>((ref, postRequestArg) {
  final postRequestState = ref.watch(postRequestStateProvider(postRequestArg));
  return postRequestState;
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
  PostRequestState(PostRequestArg postRequestArg, {required this.repository})
      : super(
          PostRequest(
            contents: '',
            title: '',
            groupId: postRequestArg.groupId,
            imageUrls: [],
            typeName: postRequestArg.postType.postTypeToString,
          ),
        );

  final PostRepository repository;

  void setContents(String text) {
    state = state.copyWith(contents: text);
  }

  void setImages(List<String> images) {
    state = state.copyWith(imageUrls: images);
  }

  void setTitle(String title) {
    state = state.copyWith(title: title);
  }

  void deleteImg(String img) {
    state = state.copyWith(
        imageUrls: state.imageUrls.where((e) => e != img).toList());
  }

  Future<dynamic> createPost() async {
    final response = await repository.createPost(
      state.copyWith(
        imageUrls: [
          'https://src.hidoc.co.kr/image/lib/2021/8/27/1630049987719_0.jpg',
          'https://static.wtable.co.kr/image-resize/production/service/recipe/655/16x9/74eb99a1-cb37-4ef0-a3a9-f7ab12e3b8fe.jpg',
          'https://dasima.xyz/wp-content/uploads/2021/01/domino-bulgogi-pizza-1.png',
        ],
      ),
    );
    return response;
  }
}
