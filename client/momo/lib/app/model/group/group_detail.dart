import 'package:freezed_annotation/freezed_annotation.dart';

part 'group_detail.g.dart';
part 'group_detail.freezed.dart';

@freezed
class GroupDetail with _$GroupDetail {
  factory GroupDetail({
    required int id,
    required int managerId,
    required String city,
    required String district,
    String? imageUrl,
    required String introduction,
    required String name,
    required bool isOffline,
    required bool isParticipant,
    required int participantCnt,
    required String startDate,
    required String university,
    required bool isEnd,
    required int recruitmentCnt,
  }) = _GroupDetail;

  factory GroupDetail.fromJson(Map<String, dynamic> json) =>
      _$GroupDetailFromJson(json);
}
