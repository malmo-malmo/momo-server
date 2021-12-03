// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'post_detail.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_PostDetail _$$_PostDetailFromJson(Map<String, dynamic> json) =>
    _$_PostDetail(
      id: json['id'] as int,
      authorId: json['authorId'] as int,
      authorNickname: json['authorNickname'] as String,
      title: json['title'] as String,
      contents: json['contents'] as String,
      authorImage: json['authorImage'] as String,
      imageUrls:
          (json['imageUrls'] as List<dynamic>).map((e) => e as String).toList(),
      createdDate: json['createdDate'] as String,
    );

Map<String, dynamic> _$$_PostDetailToJson(_$_PostDetail instance) =>
    <String, dynamic>{
      'id': instance.id,
      'authorId': instance.authorId,
      'authorNickname': instance.authorNickname,
      'title': instance.title,
      'contents': instance.contents,
      'authorImage': instance.authorImage,
      'imageUrls': instance.imageUrls,
      'createdDate': instance.createdDate,
    };
