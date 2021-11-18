import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/post/post_request.dart';

final postRequestCheckProvider = Provider.autoDispose<bool>((ref) {
  final postRequest = ref.watch(postRequestProvider);

  if (postRequest.contents.isNotEmpty &&
      postRequest.title.isNotEmpty &&
      postRequest.img.isNotEmpty) {
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
  PostRequestState() : super(PostRequest(contents: '', img: '', title: ''));

  void setContents(String text) {
    state = state.copyWith(contents: text);
  }

  void setImage(String img) {
    state = state.copyWith(img: img);
  }

  void setTitle(String title) {
    state = state.copyWith(title: title);
  }
}
