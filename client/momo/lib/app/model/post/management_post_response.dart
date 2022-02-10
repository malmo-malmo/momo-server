import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/post/post.dart';

part 'management_post_response.g.dart';
part 'management_post_response.freezed.dart';

@freezed
class ManagementPostResponse with _$ManagementPostResponse {
  factory ManagementPostResponse({
    required String groupName,
    @JsonKey(name: 'postCardResponse') required Post post,
  }) = _ManagementPostResponse;

  factory ManagementPostResponse.fromJson(Map<String, dynamic> json) =>
      _$ManagementPostResponseFromJson(json);
}
