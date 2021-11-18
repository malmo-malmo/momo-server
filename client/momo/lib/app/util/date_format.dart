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
