import 'package:freezed_annotation/freezed_annotation.dart';

part 'post.g.dart';
part 'post.freezed.dart';

@freezed
class Post with _$Post {
  factory Post({
    required int id,
    required String authorNickname,
    required String authorImage,
    required String title,
    required String contents,
    required int commentCnt,
  }) = _Post;

  factory Post.fromJson(Map<String, dynamic> json) => _$PostFromJson(json);
}
