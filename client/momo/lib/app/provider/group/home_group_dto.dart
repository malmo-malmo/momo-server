import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/group/group_info.dart';

part 'home_group_dto.freezed.dart';

@freezed
class HomeGroupDto with _$HomeGroupDto {
  factory HomeGroupDto({
    required bool isLoading,
    required List<GroupInfo> categoryGroup,
    required List<GroupInfo> districtGroup,
    required List<GroupInfo> universityGroup,
  }) = _HomeGroupDto;
}
