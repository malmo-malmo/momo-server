import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:hive_flutter/hive_flutter.dart';

part 'token_data.freezed.dart';
part 'token_data.g.dart';

@freezed
class TokenData with _$TokenData {
  @HiveType(typeId: 1, adapterName: 'TokenDataAdapter')
  factory TokenData({
    @HiveField(0) required String accessToken,
    @HiveField(1) required String refreshToken,
    @HiveField(2) required String accessTokenType,
  }) = _TokenData;
}
