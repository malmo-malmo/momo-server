import 'package:freezed_annotation/freezed_annotation.dart';

part 'user_response.g.dart';
part 'user_response.freezed.dart';

@freezed
class UserResponse with _$UserResponse {
  factory UserResponse({
    required int id,
    required String nickname,
    required String city,
    required String district,
    required String image,
    required String university,
  }) = _UserResponse;

  factory UserResponse.fromJson(Map<String, dynamic> json) =>
      _$UserResponseFromJson(json);
}
