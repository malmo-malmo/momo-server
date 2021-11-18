import 'package:freezed_annotation/freezed_annotation.dart';

part 'post_request.g.dart';
part 'post_request.freezed.dart';

@freezed
class PostRequest with _$PostRequest {
  factory PostRequest({
    required String title,
    required String contents,
    required String img,
  }) = _PostRequest;

  factory PostRequest.fromJson(Map<String, dynamic> json) =>
      _$PostRequestFromJson(json);
}
