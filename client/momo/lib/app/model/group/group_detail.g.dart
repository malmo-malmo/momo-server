// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'group_detail.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_GroupDetail _$$_GroupDetailFromJson(Map<String, dynamic> json) =>
    _$_GroupDetail(
      id: json['id'] as int,
      managerId: json['managerId'] as int,
      city: json['city'] as String,
      district: json['district'] as String,
      imageUrl: json['imageUrl'] as String?,
      introduction: json['introduction'] as String,
      name: json['name'] as String,
      isOffline: json['isOffline'] as bool,
      isParticipant: json['isParticipant'] as bool,
      participantCnt: json['participantCnt'] as int,
      startDate: json['startDate'] as String,
      university: json['university'] as String,
      isEnd: json['isEnd'] as bool,
      recruitmentCnt: json['recruitmentCnt'] as int,
    );

Map<String, dynamic> _$$_GroupDetailToJson(_$_GroupDetail instance) =>
    <String, dynamic>{
      'id': instance.id,
      'managerId': instance.managerId,
      'city': instance.city,
      'district': instance.district,
      'imageUrl': instance.imageUrl,
      'introduction': instance.introduction,
      'name': instance.name,
      'isOffline': instance.isOffline,
      'isParticipant': instance.isParticipant,
      'participantCnt': instance.participantCnt,
      'startDate': instance.startDate,
      'university': instance.university,
      'isEnd': instance.isEnd,
      'recruitmentCnt': instance.recruitmentCnt,
    };
