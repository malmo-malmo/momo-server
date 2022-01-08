// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'category_request.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

CategoryRequest _$CategoryRequestFromJson(Map<String, dynamic> json) {
  return _CategoryRequest.fromJson(json);
}

/// @nodoc
class _$CategoryRequestTearOff {
  const _$CategoryRequestTearOff();

  _CategoryRequest call({required List<String> favoriteCategories}) {
    return _CategoryRequest(
      favoriteCategories: favoriteCategories,
    );
  }

  CategoryRequest fromJson(Map<String, Object?> json) {
    return CategoryRequest.fromJson(json);
  }
}

/// @nodoc
const $CategoryRequest = _$CategoryRequestTearOff();

/// @nodoc
mixin _$CategoryRequest {
  List<String> get favoriteCategories => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $CategoryRequestCopyWith<CategoryRequest> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $CategoryRequestCopyWith<$Res> {
  factory $CategoryRequestCopyWith(
          CategoryRequest value, $Res Function(CategoryRequest) then) =
      _$CategoryRequestCopyWithImpl<$Res>;
  $Res call({List<String> favoriteCategories});
}

/// @nodoc
class _$CategoryRequestCopyWithImpl<$Res>
    implements $CategoryRequestCopyWith<$Res> {
  _$CategoryRequestCopyWithImpl(this._value, this._then);

  final CategoryRequest _value;
  // ignore: unused_field
  final $Res Function(CategoryRequest) _then;

  @override
  $Res call({
    Object? favoriteCategories = freezed,
  }) {
    return _then(_value.copyWith(
      favoriteCategories: favoriteCategories == freezed
          ? _value.favoriteCategories
          : favoriteCategories // ignore: cast_nullable_to_non_nullable
              as List<String>,
    ));
  }
}

/// @nodoc
abstract class _$CategoryRequestCopyWith<$Res>
    implements $CategoryRequestCopyWith<$Res> {
  factory _$CategoryRequestCopyWith(
          _CategoryRequest value, $Res Function(_CategoryRequest) then) =
      __$CategoryRequestCopyWithImpl<$Res>;
  @override
  $Res call({List<String> favoriteCategories});
}

/// @nodoc
class __$CategoryRequestCopyWithImpl<$Res>
    extends _$CategoryRequestCopyWithImpl<$Res>
    implements _$CategoryRequestCopyWith<$Res> {
  __$CategoryRequestCopyWithImpl(
      _CategoryRequest _value, $Res Function(_CategoryRequest) _then)
      : super(_value, (v) => _then(v as _CategoryRequest));

  @override
  _CategoryRequest get _value => super._value as _CategoryRequest;

  @override
  $Res call({
    Object? favoriteCategories = freezed,
  }) {
    return _then(_CategoryRequest(
      favoriteCategories: favoriteCategories == freezed
          ? _value.favoriteCategories
          : favoriteCategories // ignore: cast_nullable_to_non_nullable
              as List<String>,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_CategoryRequest implements _CategoryRequest {
  _$_CategoryRequest({required this.favoriteCategories});

  factory _$_CategoryRequest.fromJson(Map<String, dynamic> json) =>
      _$$_CategoryRequestFromJson(json);

  @override
  final List<String> favoriteCategories;

  @override
  String toString() {
    return 'CategoryRequest(favoriteCategories: $favoriteCategories)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _CategoryRequest &&
            const DeepCollectionEquality()
                .equals(other.favoriteCategories, favoriteCategories));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType, const DeepCollectionEquality().hash(favoriteCategories));

  @JsonKey(ignore: true)
  @override
  _$CategoryRequestCopyWith<_CategoryRequest> get copyWith =>
      __$CategoryRequestCopyWithImpl<_CategoryRequest>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_CategoryRequestToJson(this);
  }
}

abstract class _CategoryRequest implements CategoryRequest {
  factory _CategoryRequest({required List<String> favoriteCategories}) =
      _$_CategoryRequest;

  factory _CategoryRequest.fromJson(Map<String, dynamic> json) =
      _$_CategoryRequest.fromJson;

  @override
  List<String> get favoriteCategories;
  @override
  @JsonKey(ignore: true)
  _$CategoryRequestCopyWith<_CategoryRequest> get copyWith =>
      throw _privateConstructorUsedError;
}
