import 'package:intl/intl.dart';
import 'package:momo/app/util/format/calendar_max_day.dart';

String calSearchStartDay(int year, int month) =>
    DateFormat('yyyy-MM-dd').format(DateTime(year, month, 1));

String calSearchEndDay(int year, int month) => DateFormat('yyyy-MM-dd')
    .format(DateTime(year, month, calMaxDay(year, month)));
