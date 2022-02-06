import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:intl/intl.dart';
import 'package:momo/app/model/schedule/calendar_schedule.dart';
import 'package:momo/app/provider/schedule/calendar_schedule_data.dart';
import 'package:momo/app/repository/schedule_repository.dart';
import 'package:momo/app/util/format/schedule_request_date_format.dart';

final calendarScheduleProvider = StateNotifierProvider.autoDispose<
    CalendarScheduleState, CalendarScheduleData>((ref) {
  final scheduleRepository = ref.watch(scheduleRepositoryProvider);
  return CalendarScheduleState(scheduleRepository: scheduleRepository);
});

class CalendarScheduleState extends StateNotifier<CalendarScheduleData> {
  CalendarScheduleState({
    required this.scheduleRepository,
  }) : super(
          CalendarScheduleData(
            isLoading: true,
            selectedDate: DateTime.now(),
            requestDate: DateTime.now(),
            scheduleDateTimes: [],
            schedules: [],
          ),
        );

  final ScheduleRepository scheduleRepository;

  Future<void> getSchedules() async {
    state = state.copyWith(
      isLoading: true,
    );
    final schedules = await scheduleRepository.getUserSchedules(
      calSearchStartDay(state.requestDate.year, state.requestDate.month),
      calSearchEndDay(state.requestDate.year, state.requestDate.month),
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

    state = state.copyWith(
      isLoading: false,
      scheduleDateTimes: dates.map((e) => DateTime.parse(e)).toList(),
      schedules: [...scheduleLists],
    );
  }

  void changeSeletedDate(DateTime newDate) =>
      state = state.copyWith(selectedDate: newDate);

  void changeRequestDate(DateTime newDate) =>
      state = state.copyWith(requestDate: newDate);
}
