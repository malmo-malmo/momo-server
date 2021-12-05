import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:intl/intl.dart';
import 'package:momo/app/model/schedule/calendar_schedule.dart';
import 'package:momo/app/repository/schedule_repository.dart';
import 'package:momo/app/util/format/schedule_request_date_format.dart';

final homeUserScheduleProvider =
    FutureProvider.autoDispose<List<List<CalendarSchedule>>>(
  (ref) async {
    final repository = ref.watch(scheduleRepositoryProvider);
    final schedules = await repository.getUserSchedules(
      calSearchStartDay(DateTime.now().year, DateTime.now().month),
      calSearchEndDay(DateTime.now().year, DateTime.now().month),
    );
    //  시간 순 정렬
    schedules.sort((a, b) => a.startDateTime.compareTo(b.startDateTime));

    // 날짜 확인
    Set<String> dates = schedules
        .map((e) =>
            DateFormat('yyyy-MM-dd').format(DateTime.parse(e.startDateTime)))
        .toSet();

    // 분리된 일정
    List<List<CalendarSchedule>> scheduleLists = [];

    // 같은 날의 일정끼리 묶기
    for (String date in dates) {
      List<CalendarSchedule> tmp = schedules
          .where((e) =>
              DateFormat('yyyy-MM-dd')
                  .format(DateTime.parse(e.startDateTime)) ==
              date)
          .toList();
      scheduleLists.add(tmp);
    }

    return scheduleLists;
  },
);
