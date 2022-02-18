// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'attendance_update_request.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_AttendanceUpdateRequest _$$_AttendanceUpdateRequestFromJson(
        Map<String, dynamic> json) =>
    _$_AttendanceUpdateRequest(
      attendanceId: json['attendanceId'] as int,
      attend: json['attend'] as bool,
    );

Map<String, dynamic> _$$_AttendanceUpdateRequestToJson(
        _$_AttendanceUpdateRequest instance) =>
    <String, dynamic>{
      'attendanceId': instance.attendanceId,
      'attend': instance.attend,
    };
