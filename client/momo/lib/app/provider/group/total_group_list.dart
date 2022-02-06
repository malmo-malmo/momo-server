import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/group/total_group_info.dart';

part 'total_group_list.freezed.dart';

@freezed
class TotalGroupList with _$TotalGroupList {
  factory TotalGroupList({
    required bool isLoading,
    required List<TotalGroupInfo> groups,
  }) = _TotalGroupList;
}
