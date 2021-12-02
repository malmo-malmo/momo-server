import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/code_name_pair.dart';
import 'package:momo/app/repository/user_repository.dart';

late List<CodeNamePair> locationCodeNamePair;

final locationResultProvider = FutureProvider((ref) async {
  final repository = ref.watch(userRepositoryProvider);
  final locations = await repository.getLocations();
  locationCodeNamePair = locations;
});
