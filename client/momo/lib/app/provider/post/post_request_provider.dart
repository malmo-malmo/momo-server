import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/post/post_request.dart';
import 'package:momo/app/model/enum/post_type.dart';

final postRequestCheckProvider = Provider.autoDispose<bool>((ref) {
  final postRequest = ref.watch(postRequestProvider);

  if (postRequest.contents.isNotEmpty &&
      postRequest.title.isNotEmpty &&
      postRequest.imageUrls.isNotEmpty) {
    return true;
  }
  return false;
});

final postRequestProvider = Provider.autoDispose<PostRequest>((ref) {
  final postRequestState = ref.watch(postRequestStateProvider);
  return postRequestState;
});

final postRequestStateProvider =
    StateNotifierProvider.autoDispose<PostRequestState, PostRequest>(
        (ref) => PostRequestState());

class PostRequestState extends StateNotifier<PostRequest> {
  PostRequestState()
      : super(PostRequest(
          contents: '',
          title: '',
          groupId: 0,
          imageUrls: [],
          postType: '',
        ));

  void setContents(String text) {
    state = state.copyWith(contents: text);
  }

  void setGroupId(int groupId) {
    state = state.copyWith(groupId: groupId);
  }

  void setImage(List<String> images) {
    state = state.copyWith(imageUrls: images);
  }

  void setTitle(String title) {
    state = state.copyWith(title: title);
  }

  void setPostType(PostType postType) {
    state = state.copyWith(postType: postType.postTypeToString);
  }
}
