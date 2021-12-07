// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'district_response.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

DistrictResponse _$DistrictResponseFromJson(Map<String, dynamic> json) {
  return _DistrictResponse.fromJson(json);
}

/// @nodoc
class _$DistrictResponseTearOff {
  const _$DistrictResponseTearOff();

  _DistrictResponse call({required String districtName}) {
    return _DistrictResponse(
      districtName: districtName,
    );
  }

  DistrictResponse fromJson(Map<String, Object?> json) {
    return DistrictResponse.fromJson(json);
  }
}

/// @nodoc
const $DistrictResponse = _$DistrictResponseTearOff();

/// @nodoc
mixin _$DistrictResponse {
  String get districtName => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $DistrictResponseCopyWith<DistrictResponse> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $DistrictResponseCopyWith<$Res> {
  factory $DistrictResponseCopyWith(
          DistrictResponse value, $Res Function(DistrictResponse) then) =
      _$DistrictResponseCopyWithImpl<$Res>;
  $Res call({String districtName});
}

/// @nodoc
class _$DistrictResponseCopyWithImpl<$Res>
    implements $DistrictResponseCopyWith<$Res> {
  _$DistrictResponseCopyWithImpl(this._value, this._then);

  final DistrictResponse _value;
  // ignore: unused_field
  final $Res Function(DistrictResponse) _then;

  @override
  $Res call({
    Object? districtName = freezed,
  }) {
    return _then(_value.copyWith(
      districtName: districtName == freezed
          ? _value.districtName
          : districtName // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
abstract class _$DistrictResponseCopyWith<$Res>
    implements $DistrictResponseCopyWith<$Res> {
  factory _$DistrictResponseCopyWith(
          _DistrictResponse value, $Res Function(_DistrictResponse) then) =
      __$DistrictResponseCopyWithImpl<$Res>;
  @override
  $Res call({String districtName});
}

/// @nodoc
class __$DistrictResponseCopyWithImpl<$Res>
    extends _$DistrictResponseCopyWithImpl<$Res>
    implements _$DistrictResponseCopyWith<$Res> {
  __$DistrictResponseCopyWithImpl(
      _DistrictResponse _value, $Res Function(_DistrictResponse) _then)
      : super(_value, (v) => _then(v as _DistrictResponse));

  @override
  _DistrictResponse get _value => super._value as _DistrictResponse;

  @override
  $Res call({
    Object? districtName = freezed,
  }) {
    return _then(_DistrictResponse(
      districtName: districtName == freezed
          ? _value.districtName
          : districtName // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_DistrictResponse implements _DistrictResponse {
  _$_DistrictResponse({required this.districtName});

  factory _$_DistrictResponse.fromJson(Map<String, dynamic> json) =>
      _$$_DistrictResponseFromJson(json);

  @override
  final String districtName;

  @override
  String toString() {
    return 'DistrictResponse(districtName: $districtName)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _DistrictResponse &&
            (identical(other.districtName, districtName) ||
                other.districtName == districtName));
  }

  @override
  int get hashCode => Object.hash(runtimeType, districtName);

  @JsonKey(ignore: true)
  @override
  _$DistrictResponseCopyWith<_DistrictResponse> get copyWith =>
      __$DistrictResponseCopyWithImpl<_DistrictResponse>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_DistrictResponseToJson(this);
  }
}

abstract class _DistrictResponse implements DistrictResponse {
  factory _DistrictResponse({required String districtName}) =
      _$_DistrictResponse;

  factory _DistrictResponse.fromJson(Map<String, dynamic> json) =
      _$_DistrictResponse.fromJson;

  @override
  String get districtName;
  @override
  @JsonKey(ignore: true)
  _$DistrictResponseCopyWith<_DistrictResponse> get copyWith =>
      throw _privateConstructorUsedError;
}
