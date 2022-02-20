import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/api/api_provider.dart';
import 'package:momo/app/api/attendance_client/attendance_client.dart';
import 'package:momo/app/api/schedule_client/schedule_client.dart';
import 'package:momo/app/model/attendance/attendance_check_create_request.dart';
import 'package:momo/app/model/attendance/attendance_check_update_request.dart';
import 'package:momo/app/model/attendance/attendance_response.dart';
import 'package:momo/app/model/schedule/calendar_schedule.dart';
import 'package:momo/app/model/schedule/schedule_detail.dart';
import 'package:momo/app/model/schedule/schedule_request.dart';
import 'package:momo/app/model/schedule/schedule_response.dart';
import 'package:momo/app/util/constant.dart';

final scheduleRepositoryProvider = Provider<ScheduleRepository>((ref) {
  final scheduleClient = ref.watch(scheduleClientProvider);
  final attendanceClient = ref.watch(attendanceClientProvider);
  return ScheduleRepository(
    scheduleClient: scheduleClient,
    attendanceClient: attendanceClient,
  );
});

class ScheduleRepository {
  final ScheduleClient scheduleClient;
  final AttendanceClient attendanceClient;

  ScheduleRepository({
    required this.scheduleClient,
    required this.attendanceClient,
  });

  Future<ScheduleDetail> createSchedule(ScheduleRequest scheduleRequest) async {
    final response = await scheduleClient.createSchedule(scheduleRequest);
    return response;
  }

  Future<ScheduleResponse> getSchedules(
    int page, {
    required int groupId,
  }) async {
    final response = await scheduleClient.getSchedules(groupId, page, pageSize);
    return response;
  }

  Future<List<CalendarSchedule>> getUserSchedules(
      String searchStartDate, String searchEndDate) async {
    final response =
        await scheduleClient.getUserSchedules(searchStartDate, searchEndDate);
    return response;
  }

  Future<dynamic> createAttendace(
      AttendanceCheckCreateRequest attendanceCheckCreateRequest) async {
    final response =
        await attendanceClient.createAttendance(attendanceCheckCreateRequest);
    return response;
  }

  Future<dynamic> updateAttendace(
      AttendanceCheckUpdateRequest attendanceCheckUpdateRequest) async {
    final response =
        await attendanceClient.updateAttendance(attendanceCheckUpdateRequest);
    return response;
  }

  Future<List<AttendanceResponse>> getAttendance(int scheduleId) async {
    final response = await attendanceClient.getAttendances(scheduleId);
    return response;
  }
}
