import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/group/group_info.dart';

part 'searched_custom_data.freezed.dart';

@freezed
class SearchedCustomData with _$SearchedCustomData {
  factory SearchedCustomData({
    required List<String> words,
    required List<int> groupIds,
    required List<GroupInfo> groups,
    required bool isLoading,
  }) = _SearchedCustomData;
}
