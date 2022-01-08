// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'user_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_UserResponse _$$_UserResponseFromJson(Map<String, dynamic> json) =>
    _$_UserResponse(
      id: json['id'] as int,
      nickname: json['nickname'] as String,
      city: CodeNamePair.fromJson(json['city'] as Map<String, dynamic>),
      district: json['district'] as String,
      image: json['image'] as String?,
      university: json['university'] as String,
      categories: (json['categories'] as List<dynamic>)
          .map((e) => CodeNamePair.fromJson(e as Map<String, dynamic>))
          .toList(),
    );

Map<String, dynamic> _$$_UserResponseToJson(_$_UserResponse instance) =>
    <String, dynamic>{
      'id': instance.id,
      'nickname': instance.nickname,
      'city': instance.city,
      'district': instance.district,
      'image': instance.image,
      'university': instance.university,
      'categories': instance.categories,
    };
