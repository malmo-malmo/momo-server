import 'package:momo/app/util/format/time_format.dart';

String postDateFormat(String dateTime) {
  final date = DateTime.parse(dateTime);
  final time = changeTimeFormat(date.hour, date.minute);

  return '${date.year}년 ${date.month}월 ${date.day}일 $time';
}
