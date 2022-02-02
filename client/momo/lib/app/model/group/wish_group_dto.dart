import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/group/wish_group_response.dart';

part 'wish_group_dto.freezed.dart';

@freezed
class WishGroupDto with _$WishGroupDto {
  factory WishGroupDto({
    required List<WishGroupResponse> groups,
    required bool isLoading,
    String? error,
  }) = _WishGroupDto;
}
