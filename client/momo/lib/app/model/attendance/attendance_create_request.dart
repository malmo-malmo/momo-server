import 'package:freezed_annotation/freezed_annotation.dart';

part 'attendance_create_request.g.dart';
part 'attendance_create_request.freezed.dart';

@freezed
class AttendanceCreateRequest with _$AttendanceCreateRequest {
  factory AttendanceCreateRequest({
    required int participantId,
    required bool attend,
  }) = _AttendanceCreateRequest;

  factory AttendanceCreateRequest.fromJson(Map<String, dynamic> json) =>
      _$AttendanceCreateRequestFromJson(json);
}
