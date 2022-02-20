import 'package:freezed_annotation/freezed_annotation.dart';

part 'attendance_response.g.dart';
part 'attendance_response.freezed.dart';

@freezed
class AttendanceResponse with _$AttendanceResponse {
  factory AttendanceResponse({
    required int attendanceId,
    required String nickname,
    String? imageUrl,
    required bool isAttend,
    int? attendanceRate,
  }) = _AttendanceResponse;

  factory AttendanceResponse.fromJson(Map<String, dynamic> json) =>
      _$AttendanceResponseFromJson(json);
}
