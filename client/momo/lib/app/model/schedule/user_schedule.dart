import 'package:freezed_annotation/freezed_annotation.dart';

part 'user_schedule.g.dart';
part 'user_schedule.freezed.dart';

@freezed
class UserSchedule with _$UserSchedule {
  factory UserSchedule({
    required int id,
    required String name,
    required String profile,
    required String title,
    required String contents,
    required bool attendance,
  }) = _UserSchedule;

  factory UserSchedule.fromJson(Map<String, dynamic> json) =>
      _$UserScheduleFromJson(json);
}
