import 'package:freezed_annotation/freezed_annotation.dart';

part 'category_request.g.dart';
part 'category_request.freezed.dart';

@freezed
class CategoryRequest with _$CategoryRequest {
  factory CategoryRequest({
    required List<String> favoriteCategories,
  }) = _CategoryRequest;

  factory CategoryRequest.fromJson(Map<String, dynamic> json) =>
      _$CategoryRequestFromJson(json);
}
