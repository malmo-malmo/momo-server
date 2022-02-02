import 'package:dio/dio.dart';
import 'package:momo/app/model/group/group_like_request.dart';
import 'package:momo/app/model/group/wish_group_response.dart';
import 'package:momo/app/model/user/category_request.dart';
import 'package:momo/app/model/user/count_response.dart';
import 'package:momo/app/model/user/university.dart';
import 'package:momo/app/model/user/user_response.dart';
import 'package:momo/app/model/user/user_update_response.dart';
import 'package:momo/app/util/constant.dart';
import 'package:retrofit/retrofit.dart';

part 'user_client.g.dart';

@RestApi(baseUrl: baseUrl)
abstract class UserClient {
  factory UserClient(
    Dio dio, {
    String baseUrl,
  }) = _UserClient;

  @GET('/user')
  Future<UserResponse> getUserInfo();

  @PUT('/user')
  Future<UserUpdateResponse> updateUserInfo(
    @Query('nickname') String nickname,
    @Query('university') String university,
    @Query('city') String city,
    @Query('district') String district,
  );

  @GET('/user/duplicate-nickname')
  Future<dynamic> validateNickname(
    @Query('nickname') String nickname,
  );

  @GET('/universities')
  Future<List<University>> getUniversities(
    @Query('universityName') String universityName,
  );

  //  관심 카테고리 수정
  @PATCH('/favorite/categories')
  Future<dynamic> updateCategory(
    @Body() CategoryRequest categoryRequest,
  );
  //  관심 모임 조회
  @GET('/favorite/groups')
  Future<List<WishGroupResponse>> getFavoriteGroups();

  //  관심 모임 추가
  @POST('/favorite/group')
  Future<dynamic> createGroupLike(
    @Body() GroupLikeRequest groupLikeRequest,
  );

  //  관심 모임 갯수 조회
  @GET('/favorite/group/count')
  Future<CountResponse> getFavoriteGroupCount();

  //  관심 모임 해제
  @DELETE('/favorite/group/{groupId}')
  Future<dynamic> deleteGroupLike(
    @Path() int groupId,
  );
}
