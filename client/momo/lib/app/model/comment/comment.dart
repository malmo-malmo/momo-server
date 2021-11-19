import 'package:freezed_annotation/freezed_annotation.dart';

part 'comment.g.dart';
part 'comment.freezed.dart';

@freezed
class Comment with _$Comment {
  factory Comment({
    required int id,
    required String name,
    required String contents,
    required String profile,
    required String date,
  }) = _Comment;

  factory Comment.fromJson(Map<String, dynamic> json) =>
      _$CommentFromJson(json);
}
