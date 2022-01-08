import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/common/code_name_pair.dart';

part 'user_response.g.dart';
part 'user_response.freezed.dart';

@freezed
class UserResponse with _$UserResponse {
  factory UserResponse({
    required int id,
    required String nickname,
    required CodeNamePair city,
    required String district,
    String? image,
    required String university,
    required List<CodeNamePair> categories,
  }) = _UserResponse;

  factory UserResponse.fromJson(Map<String, dynamic> json) =>
      _$UserResponseFromJson(json);
}
