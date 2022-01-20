import 'package:freezed_annotation/freezed_annotation.dart';

part 'post_request.freezed.dart';

@freezed
class PostRequest with _$PostRequest {
  factory PostRequest({
    required int groupId,
    required String title,
    required String contents,
    required List<String> images,
    required String typeName,
  }) = _PostRequest;
}
