import 'package:freezed_annotation/freezed_annotation.dart';

part 'home_meet.g.dart';
part 'home_meet.freezed.dart';

@freezed
class HomeMeet with _$HomeMeet {
  factory HomeMeet({
    required int id,
    required String title,
    required String onOff,
    required int headNum,
    required String startDay,
    required String img,
  }) = _HomeMeet;

  factory HomeMeet.fromJson(Map<String, dynamic> json) =>
      _$HomeMeetFromJson(json);
}
