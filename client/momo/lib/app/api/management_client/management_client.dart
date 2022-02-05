import 'package:dio/dio.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/model/group/group_summary_response.dart';
import 'package:momo/app/model/group/my_group.dart';
import 'package:momo/app/model/post/management_post_response.dart';
import 'package:momo/app/model/user/count_response.dart';
import 'package:momo/app/util/constant.dart';
import 'package:retrofit/retrofit.dart';

part 'management_client.g.dart';

@RestApi(baseUrl: baseUrl)
abstract class ManagementClient {
  factory ManagementClient(
    Dio dio, {
    String baseUrl,
  }) = _ManagementClient;

  @GET('/management/group/participation/count')
  Future<CountResponse> getParticipationGroupCount();

  //  만든모임 + 참여한 모임
  @GET('/management/groups/participation')
  Future<List<GroupInfo>> getParticipationGroupDetail();

  @GET('/management/my-posts')
  Future<List<ManagementPostResponse>> getManagementPosts(
    @Query('page') int page,
    @Query('size') int size,
  );

  //  참여한 모임
  @GET('/management/groups/participation')
  Future<List<GroupSummaryReseponse>> getParticipationGroupSummary();

  //  만든 모임
  @GET('/management/my-groups/details')
  Future<List<MyGroup>> getMyGroupDetail();

  //  만든 모임
  @GET('/management/my-groups/summary')
  Future<List<MyGroup>> getMyGroupSummary();
}
