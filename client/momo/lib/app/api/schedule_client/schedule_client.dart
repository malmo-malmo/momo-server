import 'package:dio/dio.dart';
import 'package:momo/app/model/schedule/calendar_schedule.dart';
import 'package:momo/app/model/schedule/schedule_request.dart';
import 'package:momo/app/model/schedule/schedule_response.dart';
import 'package:momo/app/util/constant.dart';
import 'package:retrofit/retrofit.dart';

part 'schedule_client.g.dart';

@RestApi(baseUrl: baseUrl)
abstract class ScheduleClient {
  factory ScheduleClient(
    Dio dio, {
    String baseUrl,
  }) = _ScheduleClient;

  @POST('/schedule')
  Future<dynamic> createSchedule(
    @Body() ScheduleRequest scheduleRequest,
  );

  @GET('/schedule/group-schedules')
  Future<ScheduleResponse> getSchedules(
    @Query('groupId') int groupId,
    @Query('page') int page,
    @Query('size') int size,
  );

  @GET('/schedule/user-schedules')
  Future<List<CalendarSchedule>> getUserSchedules(
    @Query('searchStartDate') String searchStartDate,
    @Query('searchEndDate') String searchEndDate,
  );
}
