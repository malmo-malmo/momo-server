import 'package:intl/intl.dart';

String dayTitle(int year, int month, int day) {
  return DateFormat('EEEE').format(DateTime(year, month, day)).substring(0, 3);
}
