// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'group_request.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_GroupRequest _$$_GroupRequestFromJson(Map<String, dynamic> json) =>
    _$_GroupRequest(
      name: json['name'] as String,
      category: json['category'] as String,
      city: json['city'] as String,
      district: json['district'] as String,
      imageUrl: json['imageUrl'] as String,
      introduction: json['introduction'] as String,
      recruitmentCnt: json['recruitmentCnt'] as int,
      startDate: json['startDate'] as String,
      isUniversity: json['isUniversity'] as bool,
      isOffline: json['isOffline'] as bool,
    );

Map<String, dynamic> _$$_GroupRequestToJson(_$_GroupRequest instance) =>
    <String, dynamic>{
      'name': instance.name,
      'category': instance.category,
      'city': instance.city,
      'district': instance.district,
      'imageUrl': instance.imageUrl,
      'introduction': instance.introduction,
      'recruitmentCnt': instance.recruitmentCnt,
      'startDate': instance.startDate,
      'isUniversity': instance.isUniversity,
      'isOffline': instance.isOffline,
    };
