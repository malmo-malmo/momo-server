import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/group/group_summary_response.dart';
import 'package:momo/app/model/group/my_group.dart';

part 'my_group_list.freezed.dart';

@freezed
class MyGroupList with _$MyGroupList {
  factory MyGroupList({
    required bool isLoading,
    required List<MyGroup> myGroups,
    required List<GroupSummaryReseponse> participationGroups,
  }) = _MyGroupList;
}
