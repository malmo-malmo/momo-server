// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'participant_user.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_ParticipantUser _$$_ParticipantUserFromJson(Map<String, dynamic> json) =>
    _$_ParticipantUser(
      userId: json['userId'] as int,
      nickname: json['nickname'] as String,
      image: json['image'] as String?,
      attendanceRate: json['attendanceRate'] as int,
    );

Map<String, dynamic> _$$_ParticipantUserToJson(_$_ParticipantUser instance) =>
    <String, dynamic>{
      'userId': instance.userId,
      'nickname': instance.nickname,
      'image': instance.image,
      'attendanceRate': instance.attendanceRate,
    };
