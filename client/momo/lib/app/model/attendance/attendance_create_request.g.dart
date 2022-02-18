// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'attendance_create_request.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_AttendanceCreateRequest _$$_AttendanceCreateRequestFromJson(
        Map<String, dynamic> json) =>
    _$_AttendanceCreateRequest(
      participantId: json['participantId'] as int,
      attend: json['attend'] as bool,
    );

Map<String, dynamic> _$$_AttendanceCreateRequestToJson(
        _$_AttendanceCreateRequest instance) =>
    <String, dynamic>{
      'participantId': instance.participantId,
      'attend': instance.attend,
    };
