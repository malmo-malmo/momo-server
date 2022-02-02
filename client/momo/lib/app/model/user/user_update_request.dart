import 'package:freezed_annotation/freezed_annotation.dart';

part 'user_update_request.freezed.dart';

@freezed
class UserUpdateRequest with _$UserUpdateRequest {
  factory UserUpdateRequest({
    required String city,
    required String district,
    required String nickname,
    required String university,
    required String imagePath,
  }) = _UserUpdateInfo;
}
