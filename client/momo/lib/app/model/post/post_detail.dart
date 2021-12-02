import 'package:freezed_annotation/freezed_annotation.dart';

part 'post_detail.g.dart';
part 'post_detail.freezed.dart';

@freezed
class PostDetail with _$PostDetail {
  factory PostDetail({
    required int id,
    required String authorNickname,
    required String title,
    required String contents,
    required String authorImage,
    required List<String> imageUrls,
  }) = _PostDetail;

  factory PostDetail.fromJson(Map<String, dynamic> json) =>
      _$PostDetailFromJson(json);
}
