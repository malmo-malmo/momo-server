import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/attendance/attendance_update_request.dart';

part 'attendance_check_update_request.g.dart';
part 'attendance_check_update_request.freezed.dart';

@freezed
class AttendanceCheckUpdateRequest with _$AttendanceCheckUpdateRequest {
  factory AttendanceCheckUpdateRequest({
    required int scheduleId,
    required List<AttendanceUpdateRequest> attendanceUpdateRequests,
  }) = _AttendanceCheckUpdateRequest;

  factory AttendanceCheckUpdateRequest.fromJson(Map<String, dynamic> json) =>
      _$AttendanceCheckUpdateRequestFromJson(json);
}
