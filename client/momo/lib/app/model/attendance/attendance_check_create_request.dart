import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/attendance/attendance_create_request.dart';

part 'attendance_check_create_request.g.dart';
part 'attendance_check_create_request.freezed.dart';

@freezed
class AttendanceCheckCreateRequest with _$AttendanceCheckCreateRequest {
  factory AttendanceCheckCreateRequest({
    required int scheduleId,
    required List<AttendanceCreateRequest> attendanceCreateRequests,
  }) = _AttendanceCheckCreateRequest;

  factory AttendanceCheckCreateRequest.fromJson(Map<String, dynamic> json) =>
      _$AttendanceCheckCreateRequestFromJson(json);
}
