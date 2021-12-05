import 'package:freezed_annotation/freezed_annotation.dart';

part 'comment.g.dart';
part 'comment.freezed.dart';

@freezed
class Comment with _$Comment {
  factory Comment({
    required int id,
    required int authorId,
    required String authorNickname,
    required String contents,
    String? authorImage,
    required String createdDate,
  }) = _Comment;

  factory Comment.fromJson(Map<String, dynamic> json) =>
      _$CommentFromJson(json);
}
