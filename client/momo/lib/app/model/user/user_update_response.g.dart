// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'user_update_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_UserUpdateReponse _$$_UserUpdateReponseFromJson(Map<String, dynamic> json) =>
    _$_UserUpdateReponse(
      nickname: json['nickname'] as String,
      city: CodeNamePair.fromJson(json['city'] as Map<String, dynamic>),
      district: json['district'] as String,
      university: json['university'] as String,
      imageUrl: json['imageUrl'] as String?,
    );

Map<String, dynamic> _$$_UserUpdateReponseToJson(
        _$_UserUpdateReponse instance) =>
    <String, dynamic>{
      'nickname': instance.nickname,
      'city': instance.city,
      'district': instance.district,
      'university': instance.university,
      'imageUrl': instance.imageUrl,
    };
