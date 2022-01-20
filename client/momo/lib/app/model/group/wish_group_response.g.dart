// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'wish_group_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_WishGroupResponse _$$_WishGroupResponseFromJson(Map<String, dynamic> json) =>
    _$_WishGroupResponse(
      id: json['id'] as int,
      groupCardResponse:
          GroupInfo.fromJson(json['groupCardResponse'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$$_WishGroupResponseToJson(
        _$_WishGroupResponse instance) =>
    <String, dynamic>{
      'id': instance.id,
      'groupCardResponse': instance.groupCardResponse,
    };
