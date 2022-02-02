import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/post/post.dart';

part 'post_list_dto.freezed.dart';

@freezed
class PostListDto with _$PostListDto {
  factory PostListDto({
    required List<Post> posts,
    required int nextPage,
    required bool hasNext,
  }) = _PostListDto;
}
