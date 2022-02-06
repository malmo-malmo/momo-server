import 'package:freezed_annotation/freezed_annotation.dart';

part 'my_page_group_counts.freezed.dart';

@freezed
class MypageGroupCounts with _$MypageGroupCounts {
  factory MypageGroupCounts({
    required bool isLoading,
    required int groupCount,
    required int favoriteGroupCount,
    required int badgeCount,
  }) = _MypageGroupCounts;
}
