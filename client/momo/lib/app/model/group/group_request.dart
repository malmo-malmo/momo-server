import 'package:freezed_annotation/freezed_annotation.dart';

part 'group_request.freezed.dart';

@freezed
class GroupRequest with _$GroupRequest {
  factory GroupRequest({
    required String name,
    required String category,
    required String city,
    required String district,
    required String imagePath,
    required String introduction,
    required int recruitmentCnt,
    required String startDate,
    required bool isUniversity,
    required bool isOffline,
  }) = _GroupRequest;
}
