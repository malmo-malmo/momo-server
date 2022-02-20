import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:momo/app/model/schedule/schedule_detail.dart';
part 'group_schedule_list_dto.freezed.dart';

@freezed
class GroupScheduleListDto with _$GroupScheduleListDto {
  factory GroupScheduleListDto({
    required List<ScheduleDetail> schedules,
    required int managerId,
    required int nextPage,
    required bool hasNext,
    String? error,
  }) = _GroupScheduleListDto;
}
