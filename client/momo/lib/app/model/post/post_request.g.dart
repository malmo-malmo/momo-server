// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'post_request.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_PostRequest _$$_PostRequestFromJson(Map<String, dynamic> json) =>
    _$_PostRequest(
      groupId: json['groupId'] as int,
      title: json['title'] as String,
      contents: json['contents'] as String,
      imageUrls:
          (json['imageUrls'] as List<dynamic>).map((e) => e as String).toList(),
      postType: json['postType'] as String,
    );

Map<String, dynamic> _$$_PostRequestToJson(_$_PostRequest instance) =>
    <String, dynamic>{
      'groupId': instance.groupId,
      'title': instance.title,
      'contents': instance.contents,
      'imageUrls': instance.imageUrls,
      'postType': instance.postType,
    };
