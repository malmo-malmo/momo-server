import 'package:freezed_annotation/freezed_annotation.dart';

part 'user_info_request.freezed.dart';

@freezed
class UserInfoRequest with _$UserInfoRequest {
  factory UserInfoRequest({
    required String city,
    required String district,
    required String nickname,
    required String university,
    required String imagePath,
  }) = _UserInfoRequest;
}
