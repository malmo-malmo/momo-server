import 'package:freezed_annotation/freezed_annotation.dart';

part 'post_detail.g.dart';
part 'post_detail.freezed.dart';

@freezed
class PostDetail with _$PostDetail {
  factory PostDetail({
    required int id,
    required int authorId,
    required String authorNickname,
    required String title,
    required String contents,
    String? authorImage,
    required List<String> imageUrls,
    required String createdDate,
  }) = _PostDetail;

  factory PostDetail.fromJson(Map<String, dynamic> json) =>
      _$PostDetailFromJson(json);
}
