// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'meet_feed.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_MeetFeed _$$_MeetFeedFromJson(Map<String, dynamic> json) => _$_MeetFeed(
      id: json['id'] as int,
      userName: json['userName'] as String,
      title: json['title'] as String,
      contents: json['contents'] as String,
      comments: json['comments'] as int,
    );

Map<String, dynamic> _$$_MeetFeedToJson(_$_MeetFeed instance) =>
    <String, dynamic>{
      'id': instance.id,
      'userName': instance.userName,
      'title': instance.title,
      'contents': instance.contents,
      'comments': instance.comments,
    };
