import 'package:freezed_annotation/freezed_annotation.dart';

part 'attendance_provider_arg.freezed.dart';

@freezed
class AttendanceProviderArg with _$AttendanceProviderArg {
  factory AttendanceProviderArg({
    required int scheduleId,
    required List<int> userIds,
  }) = _AttendanceProviderArg;
}
