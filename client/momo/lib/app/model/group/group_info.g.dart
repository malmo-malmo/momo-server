// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'group_info.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_GroupInfo _$$_GroupInfoFromJson(Map<String, dynamic> json) => _$_GroupInfo(
      id: json['id'] as int,
      name: json['name'] as String,
      offline: json['offline'] as bool,
      participantCnt: json['participantCnt'] as int,
      startDate: json['startDate'] as String,
      imageUrl: json['imageUrl'] as String?,
    );

Map<String, dynamic> _$$_GroupInfoToJson(_$_GroupInfo instance) =>
    <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'offline': instance.offline,
      'participantCnt': instance.participantCnt,
      'startDate': instance.startDate,
      'imageUrl': instance.imageUrl,
    };
