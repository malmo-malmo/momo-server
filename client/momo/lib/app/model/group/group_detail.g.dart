// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'group_detail.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_GroupDetail _$$_GroupDetailFromJson(Map<String, dynamic> json) =>
    _$_GroupDetail(
      id: json['id'] as int,
      city: json['city'] as String,
      district: json['district'] as String,
      imageUrl: json['imageUrl'] as String,
      introduction: json['introduction'] as String,
      manager: json['manager'] as bool,
      name: json['name'] as String,
      offline: json['offline'] as bool,
      participant: json['participant'] as bool,
      participantCnt: json['participantCnt'] as int,
      startDate: json['startDate'] as String,
      university: json['university'] as String,
    );

Map<String, dynamic> _$$_GroupDetailToJson(_$_GroupDetail instance) =>
    <String, dynamic>{
      'id': instance.id,
      'city': instance.city,
      'district': instance.district,
      'imageUrl': instance.imageUrl,
      'introduction': instance.introduction,
      'manager': instance.manager,
      'name': instance.name,
      'offline': instance.offline,
      'participant': instance.participant,
      'participantCnt': instance.participantCnt,
      'startDate': instance.startDate,
      'university': instance.university,
    };
