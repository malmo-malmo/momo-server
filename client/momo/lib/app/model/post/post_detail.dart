import 'package:freezed_annotation/freezed_annotation.dart';

part 'post_detail.g.dart';
part 'post_detail.freezed.dart';

@freezed
class PostDetail with _$PostDetail {
  factory PostDetail({
    required int id,
    required String name,
    required String title,
    required String contents,
    required String profile,
    required String img,
    required String date,
    required int comments,
  }) = _PostDetail;

  factory PostDetail.fromJson(Map<String, dynamic> json) =>
      _$PostDetailFromJson(json);
}
