import 'package:flutter_riverpod/flutter_riverpod.dart';

final schoolResultProvider =
    FutureProvider.autoDispose<List<String>>((ref) async {
  await Future.delayed(const Duration(seconds: 1));
  return [
    '서울대학교',
    '홍익대학교',
    '서울여자대학교',
    '한양대학교',
    '중앙대학교',
    '서강대학교',
    '연세대학교',
    '고려대학교'
  ];
});
