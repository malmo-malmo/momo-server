import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/api/api_provider.dart';
import 'package:momo/app/api/user_client/user_client.dart';
import 'package:momo/app/model/code_name_pair.dart';
import 'package:momo/app/model/user/category_request.dart';
import 'package:momo/app/model/user/university.dart';
import 'package:momo/app/model/user/user_info_request.dart';

final userRepositoryProvider = Provider<UserRepository>((ref) {
  final userClient = ref.watch(userClientProvider);
  return UserRepository(userClient: userClient);
});

class UserRepository {
  UserRepository({
    required this.userClient,
  });

  final UserClient userClient;

  Future<dynamic> updateUserInfo(UserInfoRequest userInfoRequest) async {
    final response = await userClient.updateUserInfo(userInfoRequest);
    return response;
  }

  Future<bool> validateNickname(String nickname) async {
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

  Future<List<CodeNamePair>> getLocations() async {
    await Future.delayed(const Duration(milliseconds: 500));
    // final response = await userClient.getLocations();
    // return response;

    return [
      CodeNamePair(code: 'EUNPYEONG_GU', name: '은평구'),
      CodeNamePair(code: 'MAPO_GU', name: '마포구'),
      CodeNamePair(code: 'SEODAEMUN_GU', name: '서대문구'),
      CodeNamePair(code: 'JONGNO_GU', name: '종로구'),
    ];
  }
}
