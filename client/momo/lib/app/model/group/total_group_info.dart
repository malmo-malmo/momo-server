import 'package:freezed_annotation/freezed_annotation.dart';

part 'total_group_info.g.dart';
part 'total_group_info.freezed.dart';

@freezed
class TotalGroupInfo with _$TotalGroupInfo {
  factory TotalGroupInfo({
    required int id,
    required String name,
    required bool offline,
    required int participantCnt,
    required String startDate,
    String? imageUrl,
    required bool end,
  }) = _TotalGroupInfo;

  factory TotalGroupInfo.fromJson(Map<String, dynamic> json) =>
      _$TotalGroupInfoFromJson(json);
}
