import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/common/code_name_pair.dart';
import 'package:momo/app/repository/district_repository.dart';

late List<CodeNamePair> cityCodeNamePair;

final locationResultProvider = FutureProvider((ref) async {
  final repository = ref.watch(districtRepositoryProvider);
  final cities = await repository.getCities();
  cityCodeNamePair = cities;
});
