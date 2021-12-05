String groupDateFormat(String date) {
  var d = date.split('-');
  return d[1] + '/' + d[2] + '~';
}
