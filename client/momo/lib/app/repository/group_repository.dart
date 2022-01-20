import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/api/api_provider.dart';
import 'package:momo/app/api/form_data_dio.dart';
import 'package:momo/app/api/group_client/group_client.dart';
import 'package:momo/app/model/common/code_name_pair.dart';
import 'package:momo/app/model/group/group_detail.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/model/group/group_request.dart';
import 'package:momo/app/model/user/participant_user.dart';
import 'package:momo/app/util/constant.dart';

final groupRepositoryProvider = Provider<GroupRepository>((ref) {
  final groupClient = ref.watch(groupClientProvider);
  final formDataDio = ref.watch(formDataDioProvider);
  return GroupRepository(
    groupClient: groupClient,
    formDataDio: formDataDio,
  );
});

class GroupRepository {
  final GroupClient groupClient;
  final FormDataDio formDataDio;

  GroupRepository({
    required this.groupClient,
    required this.formDataDio,
  });

  Future<dynamic> participantGroup(int groupId) async {
    final response = await groupClient.participantGroup(groupId);
    return response;
  }

  Future<List<CodeNamePair>> getCategories() async {
    final response = await groupClient.getGroupCategories();
    return response;
  }

  Future<List<GroupInfo>> getGroupsByCategories(int page) async {
    final response = await groupClient.getGroupsByCategories(page, pageSize);
    return response;
  }

  Future<List<GroupInfo>> getGroupsByDistrict(int page) async {
    final response = await groupClient.getGroupsByDistrict(page, pageSize);
    return response;
  }

  Future<List<GroupInfo>> getGroupsByUniversity(int page) async {
    final response = await groupClient.getGroupsByUniversity(page, pageSize);
    return response;
  }

  Future<dynamic> createGroup(GroupRequest groupRequest) async {
    final response = await formDataDio.createGroup(groupRequest);
    return response;
  }

  Future<GroupDetail> getGroupDetail(int groupId) async {
    final response = await groupClient.getGroupDetail(groupId);
    return response;
  }

  Future<List<GroupInfo>> getGroupBySearch(
    int page, {
    required List<String> categories,
    required List<String> cities,
  }) async {
    final response =
        await groupClient.getGroupsBySearch(page, pageSize, categories, cities);
    return response;
  }

  Future<List<ParticipantUser>> getParticipantUsers(int groupId) async {
    final response = await groupClient.getParticipantUsers(groupId);
    return response;
  }

  Future<dynamic> withdrawalGroup(int groupId) async {
    final response = await groupClient.withdrawalGroup(groupId);
    return response;
  }
}
