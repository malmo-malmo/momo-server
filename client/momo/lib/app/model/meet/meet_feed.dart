import 'package:freezed_annotation/freezed_annotation.dart';

part 'meet_feed.g.dart';
part 'meet_feed.freezed.dart';

@freezed
class MeetFeed with _$MeetFeed {
  factory MeetFeed({
    required int id,
    required String userName,
    required String title,
    required String contents,
    required int comments,
  }) = _MeetFeed;

  factory MeetFeed.fromJson(Map<String, dynamic> json) =>
      _$MeetFeedFromJson(json);
}
