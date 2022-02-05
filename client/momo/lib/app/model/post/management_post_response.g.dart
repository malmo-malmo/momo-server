// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'management_post_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_ManagementPostResponse _$$_ManagementPostResponseFromJson(
        Map<String, dynamic> json) =>
    _$_ManagementPostResponse(
      groupName: json['groupName'] as String,
      post: Post.fromJson(json['postCardResponse'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$$_ManagementPostResponseToJson(
        _$_ManagementPostResponse instance) =>
    <String, dynamic>{
      'groupName': instance.groupName,
      'postCardResponse': instance.post,
    };
