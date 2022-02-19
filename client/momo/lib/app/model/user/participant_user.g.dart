// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'participant_user.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_ParticipantUser _$$_ParticipantUserFromJson(Map<String, dynamic> json) =>
    _$_ParticipantUser(
      participantId: json['participantId'] as int,
      nickname: json['nickname'] as String,
      imageUrl: json['imageUrl'] as String?,
      attendanceRate: json['attendanceRate'] as int,
    );

Map<String, dynamic> _$$_ParticipantUserToJson(_$_ParticipantUser instance) =>
    <String, dynamic>{
      'participantId': instance.participantId,
      'nickname': instance.nickname,
      'imageUrl': instance.imageUrl,
      'attendanceRate': instance.attendanceRate,
    };
