import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/post/management_post_response.dart';

part 'user_post_list.freezed.dart';

@freezed
class UserPostList with _$UserPostList {
  factory UserPostList({
    required List<ManagementPostResponse> posts,
    required bool hasNext,
    required int nextPage,
  }) = _UserPostList;
}
