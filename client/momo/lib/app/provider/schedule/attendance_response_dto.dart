import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/attendance/attendance_response.dart';

part 'attendance_response_dto.freezed.dart';

@freezed
class AttendanceResponseDto with _$AttendanceResponseDto {
  factory AttendanceResponseDto({
    required List<AttendanceResponse> attendances,
    required bool isLoading,
  }) = _AttendanceResponseDto;
}
