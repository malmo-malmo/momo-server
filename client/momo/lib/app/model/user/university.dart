import 'package:freezed_annotation/freezed_annotation.dart';

part 'university.g.dart';
part 'university.freezed.dart';

@freezed
class University with _$University {
  factory University({
    required String name,
  }) = _University;

  factory University.fromJson(Map<String, dynamic> json) =>
      _$UniversityFromJson(json);
}
