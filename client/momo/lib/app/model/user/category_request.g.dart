// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'category_request.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_CategoryRequest _$$_CategoryRequestFromJson(Map<String, dynamic> json) =>
    _$_CategoryRequest(
      favoriteCategories: (json['favoriteCategories'] as List<dynamic>)
          .map((e) => e as String)
          .toList(),
    );

Map<String, dynamic> _$$_CategoryRequestToJson(_$_CategoryRequest instance) =>
    <String, dynamic>{
      'favoriteCategories': instance.favoriteCategories,
    };
