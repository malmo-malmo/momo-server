import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/api/api_provider.dart';
import 'package:momo/app/api/schedule_client/schedule_client.dart';
import 'package:momo/app/model/code_name_pair.dart';
import 'package:momo/app/model/schedule/calendar_schedule.dart';
import 'package:momo/app/model/schedule/schedule_request.dart';
import 'package:momo/app/model/schedule/schedule_response.dart';
import 'package:momo/app/provider/category_result_provider.dart';
import 'package:momo/app/util/constant.dart';

final scheduleRepositoryProvider = Provider<ScheduleRepository>((ref) {
  final scheduleClient = ref.watch(scheduleClientProvider);
  return ScheduleRepository(scheduleClient: scheduleClient);
});

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
    // return response;

    //  테스트 일정
    return List.generate(
      100,
      (index) => CalendarSchedule(
        category: CodeNamePair(
          code: categoryCodeNamePair[index % 7].code,
          name: categoryCodeNamePair[index % 7].name,
        ),
        groupId: 1,
        startDateTime: '2021-12-0${index % 9 + 1}T13:59:58.176Z',
        title: '일정 $index',
      ),
    );
  }
}
