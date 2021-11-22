// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'schedule_request.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_ScheduleRequest _$$_ScheduleRequestFromJson(Map<String, dynamic> json) =>
    _$_ScheduleRequest(
      groupId: json['groupId'] as int,
      title: json['title'] as String,
      isOffline: json['isOffline'] as bool,
      startDateTime: json['startDateTime'] as String,
      contents: json['contents'] as String,
    );

Map<String, dynamic> _$$_ScheduleRequestToJson(_$_ScheduleRequest instance) =>
    <String, dynamic>{
      'groupId': instance.groupId,
      'title': instance.title,
      'isOffline': instance.isOffline,
      'startDateTime': instance.startDateTime,
      'contents': instance.contents,
    };
