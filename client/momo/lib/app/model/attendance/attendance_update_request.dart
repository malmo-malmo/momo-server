import 'package:freezed_annotation/freezed_annotation.dart';

part 'attendance_update_request.g.dart';
part 'attendance_update_request.freezed.dart';

@freezed
class AttendanceUpdateRequest with _$AttendanceUpdateRequest {
  factory AttendanceUpdateRequest({
    required int attendanceId,
    required bool attend,
  }) = _AttendanceUpdateRequest;

  factory AttendanceUpdateRequest.fromJson(Map<String, dynamic> json) =>
      _$AttendanceUpdateRequestFromJson(json);
}
