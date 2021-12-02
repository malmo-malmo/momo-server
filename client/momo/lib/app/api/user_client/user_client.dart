import 'package:dio/dio.dart';
import 'package:momo/app/model/code_name_pair.dart';
import 'package:momo/app/model/user/category_request.dart';
import 'package:momo/app/model/user/university.dart';
import 'package:momo/app/model/user/user_info_request.dart';
import 'package:retrofit/retrofit.dart';

part 'user_client.g.dart';

@RestApi(baseUrl: 'http://10.0.2.2:8080/api')
abstract class UserClient {
  factory UserClient(
    Dio dio, {
    String baseUrl,
  }) = _UserClient;

  @PATCH('/user')
  Future<dynamic> updateUserInfo(
    @Body() UserInfoRequest userInfoRequest,
  );

  @PATCH('/user/categories')
  Future<dynamic> updateCategory(
    @Body() CategoryRequest categoryRequest,
  );

  @GET('/user/locations')
  Future<List<CodeNamePair>> getLocations();

  @GET('/user/validate/nickname/{nickname}')
  Future<bool> validateNickname(
    @Path() String nickname,
  );

  @GET('/universities')
  Future<List<University>> getUniversities(
    @Query('universityName') String universityName,
  );
}
