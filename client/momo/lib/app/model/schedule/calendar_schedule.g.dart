// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'calendar_schedule.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_CalendarSchedule _$$_CalendarScheduleFromJson(Map<String, dynamic> json) =>
    _$_CalendarSchedule(
      groupId: json['groupId'] as int,
      startDateTime: json['startDateTime'] as String,
      title: json['title'] as String,
      category:
          CodeNamePair.fromJson(json['groupCategory'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$$_CalendarScheduleToJson(_$_CalendarSchedule instance) =>
    <String, dynamic>{
      'groupId': instance.groupId,
      'startDateTime': instance.startDateTime,
      'title': instance.title,
      'groupCategory': instance.category,
    };
