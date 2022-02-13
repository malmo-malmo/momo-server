import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:hive_flutter/hive_flutter.dart';

part 'searched_data.freezed.dart';
part 'searched_data.g.dart';

@freezed
class SearchedData with _$SearchedData {
  @HiveType(typeId: 2, adapterName: 'SearchedDataAdapter')
  factory SearchedData({
    @HiveField(0) required List<String> words,
    @HiveField(1) required List<int> groupIds,
  }) = _SearchedData;
}
