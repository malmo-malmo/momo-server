// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'wish_group_dto.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$WishGroupDtoTearOff {
  const _$WishGroupDtoTearOff();

  _WishGroupDto call(
      {required List<WishGroupResponse> groups,
      required bool isLoading,
      String? error}) {
    return _WishGroupDto(
      groups: groups,
      isLoading: isLoading,
      error: error,
    );
  }
}

/// @nodoc
const $WishGroupDto = _$WishGroupDtoTearOff();

/// @nodoc
mixin _$WishGroupDto {
  List<WishGroupResponse> get groups => throw _privateConstructorUsedError;
  bool get isLoading => throw _privateConstructorUsedError;
  String? get error => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $WishGroupDtoCopyWith<WishGroupDto> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $WishGroupDtoCopyWith<$Res> {
  factory $WishGroupDtoCopyWith(
          WishGroupDto value, $Res Function(WishGroupDto) then) =
      _$WishGroupDtoCopyWithImpl<$Res>;
  $Res call({List<WishGroupResponse> groups, bool isLoading, String? error});
}

/// @nodoc
class _$WishGroupDtoCopyWithImpl<$Res> implements $WishGroupDtoCopyWith<$Res> {
  _$WishGroupDtoCopyWithImpl(this._value, this._then);

  final WishGroupDto _value;
  // ignore: unused_field
  final $Res Function(WishGroupDto) _then;

  @override
  $Res call({
    Object? groups = freezed,
    Object? isLoading = freezed,
    Object? error = freezed,
  }) {
    return _then(_value.copyWith(
      groups: groups == freezed
          ? _value.groups
          : groups // ignore: cast_nullable_to_non_nullable
              as List<WishGroupResponse>,
      isLoading: isLoading == freezed
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
      error: error == freezed
          ? _value.error
          : error // ignore: cast_nullable_to_non_nullable
              as String?,
    ));
  }
}

/// @nodoc
abstract class _$WishGroupDtoCopyWith<$Res>
    implements $WishGroupDtoCopyWith<$Res> {
  factory _$WishGroupDtoCopyWith(
          _WishGroupDto value, $Res Function(_WishGroupDto) then) =
      __$WishGroupDtoCopyWithImpl<$Res>;
  @override
  $Res call({List<WishGroupResponse> groups, bool isLoading, String? error});
}

/// @nodoc
class __$WishGroupDtoCopyWithImpl<$Res> extends _$WishGroupDtoCopyWithImpl<$Res>
    implements _$WishGroupDtoCopyWith<$Res> {
  __$WishGroupDtoCopyWithImpl(
      _WishGroupDto _value, $Res Function(_WishGroupDto) _then)
      : super(_value, (v) => _then(v as _WishGroupDto));

  @override
  _WishGroupDto get _value => super._value as _WishGroupDto;

  @override
  $Res call({
    Object? groups = freezed,
    Object? isLoading = freezed,
    Object? error = freezed,
  }) {
    return _then(_WishGroupDto(
      groups: groups == freezed
          ? _value.groups
          : groups // ignore: cast_nullable_to_non_nullable
              as List<WishGroupResponse>,
      isLoading: isLoading == freezed
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
      error: error == freezed
          ? _value.error
          : error // ignore: cast_nullable_to_non_nullable
              as String?,
    ));
  }
}

/// @nodoc

class _$_WishGroupDto implements _WishGroupDto {
  _$_WishGroupDto({required this.groups, required this.isLoading, this.error});

  @override
  final List<WishGroupResponse> groups;
  @override
  final bool isLoading;
  @override
  final String? error;

  @override
  String toString() {
    return 'WishGroupDto(groups: $groups, isLoading: $isLoading, error: $error)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _WishGroupDto &&
            const DeepCollectionEquality().equals(other.groups, groups) &&
            (identical(other.isLoading, isLoading) ||
                other.isLoading == isLoading) &&
            (identical(other.error, error) || other.error == error));
  }

  @override
  int get hashCode => Object.hash(runtimeType,
      const DeepCollectionEquality().hash(groups), isLoading, error);

  @JsonKey(ignore: true)
  @override
  _$WishGroupDtoCopyWith<_WishGroupDto> get copyWith =>
      __$WishGroupDtoCopyWithImpl<_WishGroupDto>(this, _$identity);
}

abstract class _WishGroupDto implements WishGroupDto {
  factory _WishGroupDto(
      {required List<WishGroupResponse> groups,
      required bool isLoading,
      String? error}) = _$_WishGroupDto;

  @override
  List<WishGroupResponse> get groups;
  @override
  bool get isLoading;
  @override
  String? get error;
  @override
  @JsonKey(ignore: true)
  _$WishGroupDtoCopyWith<_WishGroupDto> get copyWith =>
      throw _privateConstructorUsedError;
}
