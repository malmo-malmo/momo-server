import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/api/api_provider.dart';
import 'package:momo/app/api/form_data_dio.dart';
import 'package:momo/app/api/management_client/management_client.dart';
import 'package:momo/app/api/user_client/user_client.dart';
import 'package:momo/app/model/group/group_like_request.dart';
import 'package:momo/app/model/group/group_summary_response.dart';
import 'package:momo/app/model/group/my_group.dart';
import 'package:momo/app/model/group/total_group_info.dart';
import 'package:momo/app/model/group/wish_group_response.dart';
import 'package:momo/app/model/post/management_post_response.dart';
import 'package:momo/app/model/user/category_request.dart';
import 'package:momo/app/model/user/count_response.dart';
import 'package:momo/app/model/user/university.dart';
import 'package:momo/app/model/user/user_update_request.dart';
import 'package:momo/app/model/user/user_response.dart';
import 'package:momo/app/model/user/user_update_response.dart';
import 'package:momo/app/util/constant.dart';

final userRepositoryProvider = Provider<UserRepository>((ref) {
  final userClient = ref.watch(userClientProvider);
  final formDataDio = ref.watch(formDataDioProvider);
  final managementClient = ref.watch(managementClientProvider);

  return UserRepository(
    userClient: userClient,
    formDataDio: formDataDio,
    managementClient: managementClient,
  );
});

class UserRepository {
  UserRepository({
    required this.userClient,
    required this.formDataDio,
    required this.managementClient,
  });

  final UserClient userClient;
  final FormDataDio formDataDio;
  final ManagementClient managementClient;

  Future<UserResponse> getUserData() async {
    final response = await userClient.getUserInfo();
    return response;
  }

  Future<UserUpdateResponse> updateUserInfo(
      UserUpdateRequest userInfoRequest) async {
    if (userInfoRequest.imagePath.isEmpty) {
      final response = await userClient.updateUserInfo(
        userInfoRequest.nickname,
        userInfoRequest.university,
        userInfoRequest.city,
        userInfoRequest.district,
      );
      return response;
    }
    final response = await formDataDio.updateUserInfo(userInfoRequest);
    return response;
  }

  Future<dynamic> validateNickname(String nickname) async {
    final isValidate = await userClient.validateNickname(nickname);
    return isValidate;
  }

  Future<List<University>> getUniversities(String universityName) async {
    final response = await userClient.getUniversities(universityName);
    return response;
  }

  Future<dynamic> updateUserCategory(CategoryRequest categoryRequest) async {
    final response = await userClient.updateCategory(categoryRequest);
    return response;
  }

  Future<List<WishGroupResponse>> getFavoriteGroups() async {
    final response = await userClient.getFavoriteGroups();
    return response;
  }

  Future<dynamic> createGroupLike(GroupLikeRequest groupLikeRequest) async {
    final response = await userClient.createGroupLike(groupLikeRequest);
    return response;
  }

  Future<dynamic> deleteGroupLike(int groupId) async {
    final response = await userClient.deleteGroupLike(groupId);
    return response;
  }

  Future<List<ManagementPostResponse>> getMyPosts(int page) async {
    final response = await managementClient.getManagementPosts(page, pageSize);
    return response;
  }

  Future<CountResponse> getFavoriteGroupCount() async {
    final response = await userClient.getFavoriteGroupCount();
    return response;
  }

  Future<CountResponse> getGroupCount() async {
    final response = await managementClient.getParticipationGroupCount();
    return response;
  }

  Future<List<TotalGroupInfo>> getTotalGroups() async {
    final response = await managementClient.getParticipationGroupDetail();
    return response;
  }

  Future<List<MyGroup>> getMyGruops() async {
    final response = await managementClient.getMyGroupDetail();
    return response;
  }

  Future<List<GroupSummaryReseponse>> getParticipationGroups() async {
    final response = await managementClient.getParticipationGroupSummary();
    return response;
  }

  Future<List<MyGroup>> getMyGroupSummary() async {
    final response = await managementClient.getMyGroupSummary();
    return response;
  }
}
