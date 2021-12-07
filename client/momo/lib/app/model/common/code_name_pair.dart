import 'package:freezed_annotation/freezed_annotation.dart';

part 'code_name_pair.g.dart';
part 'code_name_pair.freezed.dart';

@freezed
class CodeNamePair with _$CodeNamePair {
  factory CodeNamePair({
    required String code,
    required String name,
  }) = _CodeNamePair;

  factory CodeNamePair.fromJson(Map<String, dynamic> json) =>
      _$CodeNamePairFromJson(json);
}
