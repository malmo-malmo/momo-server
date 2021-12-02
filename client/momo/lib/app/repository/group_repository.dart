import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/api/api_provider.dart';
import 'package:momo/app/api/group_client/group_client.dart';
import 'package:momo/app/model/code_name_pair.dart';

final groupRepositoryProvider = Provider<GroupRepository>((ref) {
  final groupClient = ref.watch(groupClientProvider);
  return GroupRepository(groupClient: groupClient);
});

class GroupRepository {
  final GroupClient groupClient;

  GroupRepository({required this.groupClient});

  Future<List<CodeNamePair>> getCategories() async {
    final response = await groupClient.getGroupCategories();
    return response;
  }
}
