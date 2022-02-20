// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'attendance_check_update_request.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_AttendanceCheckUpdateRequest _$$_AttendanceCheckUpdateRequestFromJson(
        Map<String, dynamic> json) =>
    _$_AttendanceCheckUpdateRequest(
      scheduleId: json['scheduleId'] as int,
      attendanceUpdateRequests:
          (json['attendanceUpdateRequests'] as List<dynamic>)
              .map((e) =>
                  AttendanceUpdateRequest.fromJson(e as Map<String, dynamic>))
              .toList(),
    );

Map<String, dynamic> _$$_AttendanceCheckUpdateRequestToJson(
        _$_AttendanceCheckUpdateRequest instance) =>
    <String, dynamic>{
      'scheduleId': instance.scheduleId,
      'attendanceUpdateRequests': instance.attendanceUpdateRequests,
    };
