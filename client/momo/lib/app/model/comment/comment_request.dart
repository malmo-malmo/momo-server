import 'package:freezed_annotation/freezed_annotation.dart';

part 'comment_request.g.dart';
part 'comment_request.freezed.dart';

@freezed
class CommentRequest with _$CommentRequest {
  factory CommentRequest({
    required int postId,
    required String contents,
  }) = _CommentRequest;

  factory CommentRequest.fromJson(Map<String, dynamic> json) =>
      _$CommentRequestFromJson(json);
}
