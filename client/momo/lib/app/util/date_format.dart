import 'package:intl/intl.dart';

String dayTitle(int year, int month, int day) {
  return DateFormat('EEEE').format(DateTime(year, month, day)).substring(0, 3);
}

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
