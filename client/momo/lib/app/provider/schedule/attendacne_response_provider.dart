import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/attendance/attendance_check_update_request.dart';
import 'package:momo/app/model/attendance/attendance_update_request.dart';
import 'package:momo/app/provider/schedule/attendance_response_dto.dart';
import 'package:momo/app/repository/schedule_repository.dart';

import 'dart:developer';

final attendanceResponseStateProvider = StateNotifierProvider.family
    .autoDispose<AttendanceResponseState, AttendanceResponseDto, int>(
        (ref, scheduleId) {
  final scheduleRepository = ref.watch(scheduleRepositoryProvider);
  return AttendanceResponseState(
    scheduleId: scheduleId,
    scheduleRepository: scheduleRepository,
  )..getAttendance();
});

class AttendanceResponseState extends StateNotifier<AttendanceResponseDto> {
  AttendanceResponseState({
    required this.scheduleId,
    required this.scheduleRepository,
  }) : super(
          AttendanceResponseDto(
            attendances: [],
            isLoading: true,
          ),
        );

  final ScheduleRepository scheduleRepository;
  final int scheduleId;

  Future<void> getAttendance() async {
    state = state.copyWith(isLoading: true);
    try {
      log('요청');
      final response = await scheduleRepository.getAttendance(scheduleId);

      state = state.copyWith(
        isLoading: false,
        attendances: response,
      );
    } catch (e) {
      log(e.toString());
    }
  }

  void checkAttendance({required int userId, required bool isAttend}) {
    state = state.copyWith(
      attendances: state.attendances
          .map(
            (e) => e.attendanceId == userId
                ? e.copyWith(
                    isAttend: isAttend,
                  )
                : e,
          )
          .toList(),
    );
  }

  Future<dynamic> updateAttendance() async {
    final response = await scheduleRepository.updateAttendace(
      AttendanceCheckUpdateRequest(
        scheduleId: scheduleId,
        attendanceUpdateRequests: List.generate(
          state.attendances.length,
          (index) => AttendanceUpdateRequest(
            attendanceId: state.attendances[index].attendanceId,
            attend: state.attendances[index].isAttend,
          ),
        ),
      ),
    );

    return response;
  }
}
