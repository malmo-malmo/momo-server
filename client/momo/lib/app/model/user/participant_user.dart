import 'package:freezed_annotation/freezed_annotation.dart';

part 'participant_user.g.dart';
part 'participant_user.freezed.dart';

@freezed
class ParticipantUser with _$ParticipantUser {
  factory ParticipantUser({
    required int userId,
    required String nickname,
    String? image,
    required int attendanceRate,
  }) = _ParticipantUser;

  factory ParticipantUser.fromJson(Map<String, dynamic> json) =>
      _$ParticipantUserFromJson(json);
}
