// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'user_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_UserResponse _$$_UserResponseFromJson(Map<String, dynamic> json) =>
    _$_UserResponse(
      id: json['id'] as int,
      nickname: json['nickname'] as String,
      city: json['city'] as String,
      district: json['district'] as String,
      image: json['image'] as String,
      university: json['university'] as String,
    );

Map<String, dynamic> _$$_UserResponseToJson(_$_UserResponse instance) =>
    <String, dynamic>{
      'id': instance.id,
      'nickname': instance.nickname,
      'city': instance.city,
      'district': instance.district,
      'image': instance.image,
      'university': instance.university,
    };
