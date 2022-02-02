// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'count_response.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

CountResponse _$CountResponseFromJson(Map<String, dynamic> json) {
  return _CountResponse.fromJson(json);
}

/// @nodoc
class _$CountResponseTearOff {
  const _$CountResponseTearOff();

  _CountResponse call({required int count}) {
    return _CountResponse(
      count: count,
    );
  }

  CountResponse fromJson(Map<String, Object?> json) {
    return CountResponse.fromJson(json);
  }
}

/// @nodoc
const $CountResponse = _$CountResponseTearOff();

/// @nodoc
mixin _$CountResponse {
  int get count => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $CountResponseCopyWith<CountResponse> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $CountResponseCopyWith<$Res> {
  factory $CountResponseCopyWith(
          CountResponse value, $Res Function(CountResponse) then) =
      _$CountResponseCopyWithImpl<$Res>;
  $Res call({int count});
}

/// @nodoc
class _$CountResponseCopyWithImpl<$Res>
    implements $CountResponseCopyWith<$Res> {
  _$CountResponseCopyWithImpl(this._value, this._then);

  final CountResponse _value;
  // ignore: unused_field
  final $Res Function(CountResponse) _then;

  @override
  $Res call({
    Object? count = freezed,
  }) {
    return _then(_value.copyWith(
      count: count == freezed
          ? _value.count
          : count // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
abstract class _$CountResponseCopyWith<$Res>
    implements $CountResponseCopyWith<$Res> {
  factory _$CountResponseCopyWith(
          _CountResponse value, $Res Function(_CountResponse) then) =
      __$CountResponseCopyWithImpl<$Res>;
  @override
  $Res call({int count});
}

/// @nodoc
class __$CountResponseCopyWithImpl<$Res>
    extends _$CountResponseCopyWithImpl<$Res>
    implements _$CountResponseCopyWith<$Res> {
  __$CountResponseCopyWithImpl(
      _CountResponse _value, $Res Function(_CountResponse) _then)
      : super(_value, (v) => _then(v as _CountResponse));

  @override
  _CountResponse get _value => super._value as _CountResponse;

  @override
  $Res call({
    Object? count = freezed,
  }) {
    return _then(_CountResponse(
      count: count == freezed
          ? _value.count
          : count // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_CountResponse implements _CountResponse {
  _$_CountResponse({required this.count});

  factory _$_CountResponse.fromJson(Map<String, dynamic> json) =>
      _$$_CountResponseFromJson(json);

  @override
  final int count;

  @override
  String toString() {
    return 'CountResponse(count: $count)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _CountResponse &&
            (identical(other.count, count) || other.count == count));
  }

  @override
  int get hashCode => Object.hash(runtimeType, count);

  @JsonKey(ignore: true)
  @override
  _$CountResponseCopyWith<_CountResponse> get copyWith =>
      __$CountResponseCopyWithImpl<_CountResponse>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_CountResponseToJson(this);
  }
}

abstract class _CountResponse implements CountResponse {
  factory _CountResponse({required int count}) = _$_CountResponse;

  factory _CountResponse.fromJson(Map<String, dynamic> json) =
      _$_CountResponse.fromJson;

  @override
  int get count;
  @override
  @JsonKey(ignore: true)
  _$CountResponseCopyWith<_CountResponse> get copyWith =>
      throw _privateConstructorUsedError;
}
