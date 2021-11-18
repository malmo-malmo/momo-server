import 'package:freezed_annotation/freezed_annotation.dart';

part 'new_meet.freezed.dart';
part 'new_meet.g.dart';

@freezed
class NewMeet with _$NewMeet {
  factory NewMeet({
    required String meetName,
    required String category,
    required String onOff,
    required int headNum,
    required String startDay,
    required String school,
    required String city,
    required String country,
    required String contents,
    required String img,
  }) = _NewMeet;

  factory NewMeet.fromJson(Map<String, dynamic> json) =>
      _$NewMeetFromJson(json);
}
