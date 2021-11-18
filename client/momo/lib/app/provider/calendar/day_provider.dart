import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/calendar/scroll_state_provider.dart';
import 'package:table_calendar/table_calendar.dart';

final selectdDayProvider = StateProvider<DateTime>((ref) => DateTime.now());

final focusedDayProvider = StateProvider<DateTime>((ref) => DateTime.now());

final calendarFormatProvder = Provider<CalendarFormat>((ref) {
  final scrollState = ref.watch(scrollProvider);
  switch (scrollState) {
    case 1:
      return CalendarFormat.twoWeeks;
    case 2:
      return CalendarFormat.week;
    default:
      return CalendarFormat.month;
  }
});
