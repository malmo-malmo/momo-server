import 'package:dio/dio.dart';
import 'package:momo/app/model/code_name_pair.dart';
import 'package:momo/app/model/group/group_detail.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/model/group/group_request.dart';
import 'package:retrofit/retrofit.dart';

part 'group_client.g.dart';

@RestApi(baseUrl: 'http://localhost:8080/api')
abstract class GroupClient {
  factory GroupClient(
    Dio dio, {
    String baseUrl,
  }) = _GroupClient;

  @POST('/group')
  Future<dynamic> createGroup(
    @Body() GroupRequest groupRequest,
  );

  @GET('/group/{groupId}')
  Future<GroupDetail> getGroupDetail(
    @Path() int groupId,
  );

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

  @GET('/groups/search/paging')
  Future<List<GroupInfo>> getGroupsByDistrict(
    @Query('page') int page,
    @Query('size') int size,
  );

  @GET('/groups/search/paging')
  Future<List<GroupInfo>> getGroupsByUniversity(
    @Query('page') int page,
    @Query('size') int size,
  );
}
