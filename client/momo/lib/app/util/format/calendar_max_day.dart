int calMaxDay(int year, int month) {
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
