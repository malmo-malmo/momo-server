import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/group/group_info.dart';

part 'wish_group_response.g.dart';
part 'wish_group_response.freezed.dart';

@freezed
class WishGroupResponse with _$WishGroupResponse {
  factory WishGroupResponse({
    required int id,
    required GroupInfo groupCardResponse,
  }) = _WishGroupResponse;

  factory WishGroupResponse.fromJson(Map<String, dynamic> json) =>
      _$WishGroupResponseFromJson(json);
}
