import 'package:dio/dio.dart';
import 'package:momo/app/model/common/code_name_pair.dart';
import 'package:momo/app/model/group/group_detail.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/model/user/participant_user.dart';
import 'package:momo/app/util/constant.dart';
import 'package:retrofit/retrofit.dart';

part 'group_client.g.dart';

@RestApi(baseUrl: baseUrl)
abstract class GroupClient {
  factory GroupClient(
    Dio dio, {
    String baseUrl,
  }) = _GroupClient;

  @GET('/group/{groupId}')
  Future<GroupDetail> getGroupDetail(@Path() int groupId);

  @GET('/group/categories')
  Future<List<CodeNamePair>> getGroupCategories();

  @GET('/groups/search/paging')
  Future<List<GroupInfo>> getGroupsBySearch(
    @Query('page') int page,
    @Query('size') int size,
    @Query('categories') List<String> categories,
    @Query('cities') List<String> cities,
  );

  @GET('/groups/user-categories/paging')
  Future<List<GroupInfo>> getGroupsByCategories(
    @Query('page') int page,
    @Query('size') int size,
  );

  @GET('/groups/user-district/paging')
  Future<List<GroupInfo>> getGroupsByDistrict(
    @Query('page') int page,
    @Query('size') int size,
  );

  @GET('/groups/user-university/paging')
  Future<List<GroupInfo>> getGroupsByUniversity(
    @Query('page') int page,
    @Query('size') int size,
  );

  @POST('/group/apply-participant')
  Future<dynamic> participantGroup(@Body() int groupId);

  @DELETE('/group/{groupId}/participant')
  Future<dynamic> withdrawalGroup(@Path() int groupId);

  @GET('/group/participants')
  Future<List<ParticipantUser>> getParticipantUsers(
      @Query('groupId') int groupId);
}
