String calStartTime(String startDateTime) => changeTimeFormat(
    DateTime.parse(startDateTime).hour, DateTime.parse(startDateTime).minute);

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
