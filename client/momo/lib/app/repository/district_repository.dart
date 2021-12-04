import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/api/api_provider.dart';
import 'package:momo/app/api/district_client/district_client.dart';
import 'package:momo/app/model/code_name_pair.dart';
import 'package:momo/app/model/district_response.dart';

final districtRepositoryProvider = Provider<DistrictRepository>((ref) {
  final districtClient = ref.watch(districtClientProvider);
  return DistrictRepository(districtClient: districtClient);
});

class DistrictRepository {
  final DistrictClient districtClient;

  DistrictRepository({
    required this.districtClient,
  });

  Future<List<CodeNamePair>> getCities() async {
    final response = await districtClient.getCities();
    return response;
  }

  Future<List<DistrictResponse>> getDistricts(String cityCode) async {
    final response = await districtClient.getDistricts(cityCode);
    return response;
  }
}
