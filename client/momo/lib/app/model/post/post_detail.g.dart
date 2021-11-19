// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'post_detail.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_PostDetail _$$_PostDetailFromJson(Map<String, dynamic> json) =>
    _$_PostDetail(
      id: json['id'] as int,
      name: json['name'] as String,
      title: json['title'] as String,
      contents: json['contents'] as String,
      profile: json['profile'] as String,
      img: json['img'] as String,
      date: json['date'] as String,
      comments: json['comments'] as int,
    );

Map<String, dynamic> _$$_PostDetailToJson(_$_PostDetail instance) =>
    <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'title': instance.title,
      'contents': instance.contents,
      'profile': instance.profile,
      'img': instance.img,
      'date': instance.date,
      'comments': instance.comments,
    };
