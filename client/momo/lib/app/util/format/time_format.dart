import 'package:intl/intl.dart';

String calStartTime(String startDateTime) =>
    DateFormat.jm().format(DateTime.parse(startDateTime));

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
