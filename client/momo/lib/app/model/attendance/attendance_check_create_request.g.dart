// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'attendance_check_create_request.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_AttendanceCheckCreateRequest _$$_AttendanceCheckCreateRequestFromJson(
        Map<String, dynamic> json) =>
    _$_AttendanceCheckCreateRequest(
      scheduleId: json['scheduleId'] as int,
      attendanceCreateRequests:
          (json['attendanceCreateRequests'] as List<dynamic>)
              .map((e) =>
                  AttendanceCreateRequest.fromJson(e as Map<String, dynamic>))
              .toList(),
    );

Map<String, dynamic> _$$_AttendanceCheckCreateRequestToJson(
        _$_AttendanceCheckCreateRequest instance) =>
    <String, dynamic>{
      'scheduleId': instance.scheduleId,
      'attendanceCreateRequests': instance.attendanceCreateRequests,
    };
