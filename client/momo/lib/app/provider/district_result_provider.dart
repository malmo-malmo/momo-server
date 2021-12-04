import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/repository/district_repository.dart';

final districtResultProvider = FutureProvider.family
    .autoDispose<List<String>, String>((ref, cityCode) async {
  final repository = ref.watch(districtRepositoryProvider);
  final districts = await repository.getDistricts(cityCode);
  return districts.map((e) => e.districtName).toList();
});
