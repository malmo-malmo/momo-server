import 'package:intl/intl.dart';

String dayTitle(int year, int month, int day) =>
    DateFormat('EEEE').format(DateTime(year, month, day)).substring(0, 3);

String calSearchStartDay(int year, int month) =>
    DateFormat('yyyy-MM-dd').format(DateTime(year, month, 1));

String calSearchEndDay(int year, int month) => DateFormat('yyyy-MM-dd')
    .format(DateTime(year, month, calendarDay(year, month)));

String calStartTime(String startDateTime) =>
    DateFormat.jm().format(DateTime.parse(startDateTime));

String calDay(DateTime date) {
  final day = DateFormat('EEEE').format(date);
  switch (day) {
    case 'Monday':
      return '월';
    case 'Tuesday':
      return '화';
    case 'Wednesday':
      return '수';
    case 'Thursday':
      return '목';
    case 'Friday':
      return '금';
    case 'Saturday':
      return '토';
    case 'Sunday':
      return '일';
    default:
      return '';
  }
}

String changeTimeFormat(int hour, int minute) {
  if (hour == 12) {
    if (minute >= 0 && minute < 10) {
      return '오후 $hour:0$minute';
    } else {
      return '오후 $hour:$minute';
    }
  } else if (hour > 12) {
    if (minute >= 0 && minute < 10) {
      return '오후 ${hour - 12}:0$minute';
    } else {
      return '오후 ${hour - 12}:$minute';
    }
  } else {
    if (minute >= 0 && minute < 10) {
      return '오전 $hour:0$minute';
    } else {
      return '오전 $hour:$minute';
    }
  }
}

int calendarDay(int year, int month) {
  List<int> bigMonth = [1, 3, 5, 7, 8, 10, 12];

  if (bigMonth.contains(month)) {
    return 31;
  } else {
    if (month == 2) {
      if (isLeapMonth(year)) {
        return 29;
      }
      return 28;
    } else {
      return 30;
    }
  }
}

bool isLeapMonth(int year) {
  if ((year % 4) == 0 && (year % 100) != 0 || (year % 400) == 0) {
    return true;
  } else {
    return false;
  }
}

String groupDateFormat(String date) {
  var d = date.split('-');
  return d[1] + '/' + d[2] + '~';
}
