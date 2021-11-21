import 'package:freezed_annotation/freezed_annotation.dart';

part 'meet_request.freezed.dart';
part 'meet_request.g.dart';

@freezed
class MeetRequest with _$MeetRequest {
  factory MeetRequest({
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

  factory MeetRequest.fromJson(Map<String, dynamic> json) =>
      _$MeetRequestFromJson(json);
}
