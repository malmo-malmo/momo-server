import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/common/code_name_pair.dart';
import 'package:momo/app/repository/group_repository.dart';

late List<CodeNamePair> categoryCodeNamePair;

final categoryResultProvider = FutureProvider((ref) async {
  final repository = ref.watch(groupRepositoryProvider);
  final categories = await repository.getCategories();
  categoryCodeNamePair = categories;
});
