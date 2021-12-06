import 'package:freezed_annotation/freezed_annotation.dart';

part 'code_check_pair.freezed.dart';

@freezed
class CodeCheckPair with _$CodeCheckPair {
  factory CodeCheckPair({
    required String code,
    required bool check,
  }) = _CodeCheckPair;
}
