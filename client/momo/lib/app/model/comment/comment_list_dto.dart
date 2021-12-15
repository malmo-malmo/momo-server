import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/comment/comment.dart';

part 'comment_list_dto.freezed.dart';

@freezed
class CommentListDto with _$CommentListDto {
  factory CommentListDto({
    required List<Comment> comments,
    required int nextPage,
    required bool hasNext,
  }) = _CommentListDto;
}
