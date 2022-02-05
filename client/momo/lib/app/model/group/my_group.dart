import 'package:freezed_annotation/freezed_annotation.dart';

part 'my_group.g.dart';
part 'my_group.freezed.dart';

@freezed
class MyGroup with _$MyGroup {
  factory MyGroup({
    required int id,
    required String name,
    String? imageUrl,
    int? achievementRate,
  }) = _MyGroup;

  factory MyGroup.fromJson(Map<String, dynamic> json) =>
      _$MyGroupFromJson(json);
}
