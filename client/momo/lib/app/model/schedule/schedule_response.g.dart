// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'schedule_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_ScheduleResponse _$$_ScheduleResponseFromJson(Map<String, dynamic> json) =>
    _$_ScheduleResponse(
      managerId: json['managerId'] as int,
      schedules: (json['schedules'] as List<dynamic>)
          .map((e) => ScheduleDetail.fromJson(e as Map<String, dynamic>))
          .toList(),
    );

Map<String, dynamic> _$$_ScheduleResponseToJson(_$_ScheduleResponse instance) =>
    <String, dynamic>{
      'managerId': instance.managerId,
      'schedules': instance.schedules,
    };
