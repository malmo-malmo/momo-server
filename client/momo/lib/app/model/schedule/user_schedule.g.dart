// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'user_schedule.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_UserSchedule _$$_UserScheduleFromJson(Map<String, dynamic> json) =>
    _$_UserSchedule(
      id: json['id'] as int,
      name: json['name'] as String,
      profile: json['profile'] as String,
      title: json['title'] as String,
      contents: json['contents'] as String,
      attendance: json['attendance'] as bool,
    );

Map<String, dynamic> _$$_UserScheduleToJson(_$_UserSchedule instance) =>
    <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'profile': instance.profile,
      'title': instance.title,
      'contents': instance.contents,
      'attendance': instance.attendance,
    };
