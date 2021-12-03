import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/comment/comment.dart';

part 'comment_response.g.dart';
part 'comment_response.freezed.dart';

@freezed
class CommentResponse with _$CommentResponse {
  factory CommentResponse({
    required int commentCnt,
    @JsonKey(name: 'commentResponses') required List<Comment> comments,
  }) = _CommentResponse;

  factory CommentResponse.fromJson(Map<String, dynamic> json) =>
      _$CommentResponseFromJson(json);
}
