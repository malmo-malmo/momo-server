import 'package:freezed_annotation/freezed_annotation.dart';

part 'group_info.g.dart';
part 'group_info.freezed.dart';

@freezed
class GroupInfo with _$GroupInfo {
  factory GroupInfo({
    required int id,
    required String name,
    required bool offline,
    required int participantCnt,
    required String startDate,
    String? imageUrl,
    required bool favoriteGroup,
  }) = _GroupInfo;

  factory GroupInfo.fromJson(Map<String, dynamic> json) =>
      _$GroupInfoFromJson(json);
}
