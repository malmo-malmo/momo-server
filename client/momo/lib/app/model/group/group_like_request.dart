import 'package:freezed_annotation/freezed_annotation.dart';

part 'group_like_request.g.dart';

@JsonSerializable()
class GroupLikeRequest {
  final int groupId;

  GroupLikeRequest({
    required this.groupId,
  });

  factory GroupLikeRequest.fromJson(Map<String, dynamic> json) =>
      _$GroupLikeRequestFromJson(json);

  Map<String, dynamic> toJson() => _$GroupLikeRequestToJson(this);
}
