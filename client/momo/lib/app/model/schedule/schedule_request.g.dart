// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'schedule_request.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_ScheduleRequest _$$_ScheduleRequestFromJson(Map<String, dynamic> json) =>
    _$_ScheduleRequest(
      meetName: json['meetName'] as String,
      name: json['name'] as String,
      onOff: json['onOff'] as String,
      date: json['date'] as String,
      time: json['time'] as String,
      texts: json['texts'] as String,
    );

Map<String, dynamic> _$$_ScheduleRequestToJson(_$_ScheduleRequest instance) =>
    <String, dynamic>{
      'meetName': instance.meetName,
      'name': instance.name,
      'onOff': instance.onOff,
      'date': instance.date,
      'time': instance.time,
      'texts': instance.texts,
    };
