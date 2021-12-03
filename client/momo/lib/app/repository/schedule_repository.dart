import 'package:momo/app/api/schedule_client/schedule_client.dart';
import 'package:momo/app/model/schedule/calendar_schedule.dart';
import 'package:momo/app/model/schedule/schedule_request.dart';
import 'package:momo/app/model/schedule/schedule_response.dart';
import 'package:momo/app/util/constant.dart';

class ScheduleRepository {
  final ScheduleClient scheduleClient;

  ScheduleRepository({required this.scheduleClient});

  Future<dynamic> createSchedule(ScheduleRequest scheduleRequest) async {
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
}
