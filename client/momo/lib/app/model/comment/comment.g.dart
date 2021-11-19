// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'comment.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_Comment _$$_CommentFromJson(Map<String, dynamic> json) => _$_Comment(
      id: json['id'] as int,
      name: json['name'] as String,
      contents: json['contents'] as String,
      profile: json['profile'] as String,
      date: json['date'] as String,
    );

Map<String, dynamic> _$$_CommentToJson(_$_Comment instance) =>
    <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'contents': instance.contents,
      'profile': instance.profile,
      'date': instance.date,
    };
