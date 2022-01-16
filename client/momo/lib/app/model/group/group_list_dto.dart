import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/group/group_info.dart';

part 'group_list_dto.freezed.dart';

@freezed
class GroupListDto with _$GroupListDto {
  factory GroupListDto({
    required List<GroupInfo> groups,
    required int nextPage,
    required bool hasNext,
  }) = _GroupListDto;
}
