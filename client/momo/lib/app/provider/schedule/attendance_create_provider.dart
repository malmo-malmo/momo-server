import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/attendance/attendance_check_create_request.dart';
import 'package:momo/app/model/attendance/attendance_create_request.dart';
import 'package:momo/app/provider/schedule/attendance_provider_arg.dart';
import 'package:momo/app/repository/schedule_repository.dart';

final attendacneCreateStateProvider = StateNotifierProvider.family.autoDispose<
    AttendanceCreateState,
    AttendanceCheckCreateRequest,
    AttendanceProviderArg>((ref, arg) {
  final scheduleRepository = ref.watch(scheduleRepositoryProvider);
  return AttendanceCreateState(
    arg: arg,
    scheduleRepository: scheduleRepository,
  );
});

class AttendanceCreateState
    extends StateNotifier<AttendanceCheckCreateRequest> {
  AttendanceCreateState({
    required this.arg,
    required this.scheduleRepository,
  }) : super(
          AttendanceCheckCreateRequest(
            scheduleId: arg.scheduleId,
            attendanceCreateRequests: List.generate(
              arg.userIds.length,
              (index) => AttendanceCreateRequest(
                participantId: arg.userIds[index],
                attend: false,
              ),
            ),
          ),
        );

  final AttendanceProviderArg arg;
  final ScheduleRepository scheduleRepository;

  void checkAttendacne({
    required userId,
    required bool isAttend,
  }) {
    state = state.copyWith(
      attendanceCreateRequests: state.attendanceCreateRequests
          .map(
            (e) => e.participantId == userId ? e.copyWith(attend: isAttend) : e,
          )
          .toList(),
    );
  }

  Future<dynamic> createAttendance() async {
    final response = await scheduleRepository.createAttendace(state);
    return response;
  }
}
