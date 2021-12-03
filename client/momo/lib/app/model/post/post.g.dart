// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'post.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_Post _$$_PostFromJson(Map<String, dynamic> json) => _$_Post(
      id: json['id'] as int,
      authorNickname: json['authorNickname'] as String,
      authorImage: json['authorImage'] as String,
      title: json['title'] as String,
      contents: json['contents'] as String,
      commentCnt: json['commentCnt'] as int,
      createdDate: json['createdDate'] as String,
    );

Map<String, dynamic> _$$_PostToJson(_$_Post instance) => <String, dynamic>{
      'id': instance.id,
      'authorNickname': instance.authorNickname,
      'authorImage': instance.authorImage,
      'title': instance.title,
      'contents': instance.contents,
      'commentCnt': instance.commentCnt,
      'createdDate': instance.createdDate,
    };
