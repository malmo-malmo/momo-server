import 'package:freezed_annotation/freezed_annotation.dart';

part 'group_summary_response.g.dart';
part 'group_summary_response.freezed.dart';

@freezed
class GroupSummaryReseponse with _$GroupSummaryReseponse {
  factory GroupSummaryReseponse({
    required int id,
    required String name,
    required String category,
  }) = _GroupSummaryReseponse;

  factory GroupSummaryReseponse.fromJson(Map<String, dynamic> json) =>
      _$GroupSummaryReseponseFromJson(json);
}
