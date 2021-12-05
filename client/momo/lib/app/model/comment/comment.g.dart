// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'comment.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_Comment _$$_CommentFromJson(Map<String, dynamic> json) => _$_Comment(
      id: json['id'] as int,
      authorId: json['authorId'] as int,
      authorNickname: json['authorNickname'] as String,
      contents: json['contents'] as String,
      authorImage: json['authorImage'] as String?,
      createdDate: json['createdDate'] as String,
    );

Map<String, dynamic> _$$_CommentToJson(_$_Comment instance) =>
    <String, dynamic>{
      'id': instance.id,
      'authorId': instance.authorId,
      'authorNickname': instance.authorNickname,
      'contents': instance.contents,
      'authorImage': instance.authorImage,
      'createdDate': instance.createdDate,
    };
