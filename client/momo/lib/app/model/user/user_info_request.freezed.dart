// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'user_info_request.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

UserInfoRequest _$UserInfoRequestFromJson(Map<String, dynamic> json) {
  return _UserInfoRequest.fromJson(json);
}

/// @nodoc
class _$UserInfoRequestTearOff {
  const _$UserInfoRequestTearOff();

  _UserInfoRequest call(
      {required String city,
      required String district,
      required String nickname,
      required String university}) {
    return _UserInfoRequest(
      city: city,
      district: district,
      nickname: nickname,
      university: university,
    );
  }

  UserInfoRequest fromJson(Map<String, Object?> json) {
    return UserInfoRequest.fromJson(json);
  }
}

/// @nodoc
const $UserInfoRequest = _$UserInfoRequestTearOff();

/// @nodoc
mixin _$UserInfoRequest {
  String get city => throw _privateConstructorUsedError;
  String get district => throw _privateConstructorUsedError;
  String get nickname => throw _privateConstructorUsedError;
  String get university => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $UserInfoRequestCopyWith<UserInfoRequest> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $UserInfoRequestCopyWith<$Res> {
  factory $UserInfoRequestCopyWith(
          UserInfoRequest value, $Res Function(UserInfoRequest) then) =
      _$UserInfoRequestCopyWithImpl<$Res>;
  $Res call({String city, String district, String nickname, String university});
}

/// @nodoc
class _$UserInfoRequestCopyWithImpl<$Res>
    implements $UserInfoRequestCopyWith<$Res> {
  _$UserInfoRequestCopyWithImpl(this._value, this._then);

  final UserInfoRequest _value;
  // ignore: unused_field
  final $Res Function(UserInfoRequest) _then;

  @override
  $Res call({
    Object? city = freezed,
    Object? district = freezed,
    Object? nickname = freezed,
    Object? university = freezed,
  }) {
    return _then(_value.copyWith(
      city: city == freezed
          ? _value.city
          : city // ignore: cast_nullable_to_non_nullable
              as String,
      district: district == freezed
          ? _value.district
          : district // ignore: cast_nullable_to_non_nullable
              as String,
      nickname: nickname == freezed
          ? _value.nickname
          : nickname // ignore: cast_nullable_to_non_nullable
              as String,
      university: university == freezed
          ? _value.university
          : university // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
abstract class _$UserInfoRequestCopyWith<$Res>
    implements $UserInfoRequestCopyWith<$Res> {
  factory _$UserInfoRequestCopyWith(
          _UserInfoRequest value, $Res Function(_UserInfoRequest) then) =
      __$UserInfoRequestCopyWithImpl<$Res>;
  @override
  $Res call({String city, String district, String nickname, String university});
}

/// @nodoc
class __$UserInfoRequestCopyWithImpl<$Res>
    extends _$UserInfoRequestCopyWithImpl<$Res>
    implements _$UserInfoRequestCopyWith<$Res> {
  __$UserInfoRequestCopyWithImpl(
      _UserInfoRequest _value, $Res Function(_UserInfoRequest) _then)
      : super(_value, (v) => _then(v as _UserInfoRequest));

  @override
  _UserInfoRequest get _value => super._value as _UserInfoRequest;

  @override
  $Res call({
    Object? city = freezed,
    Object? district = freezed,
    Object? nickname = freezed,
    Object? university = freezed,
  }) {
    return _then(_UserInfoRequest(
      city: city == freezed
          ? _value.city
          : city // ignore: cast_nullable_to_non_nullable
              as String,
      district: district == freezed
          ? _value.district
          : district // ignore: cast_nullable_to_non_nullable
              as String,
      nickname: nickname == freezed
          ? _value.nickname
          : nickname // ignore: cast_nullable_to_non_nullable
              as String,
      university: university == freezed
          ? _value.university
          : university // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_UserInfoRequest implements _UserInfoRequest {
  _$_UserInfoRequest(
      {required this.city,
      required this.district,
      required this.nickname,
      required this.university});

  factory _$_UserInfoRequest.fromJson(Map<String, dynamic> json) =>
      _$$_UserInfoRequestFromJson(json);

  @override
  final String city;
  @override
  final String district;
  @override
  final String nickname;
  @override
  final String university;

  @override
  String toString() {
    return 'UserInfoRequest(city: $city, district: $district, nickname: $nickname, university: $university)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _UserInfoRequest &&
            (identical(other.city, city) || other.city == city) &&
            (identical(other.district, district) ||
                other.district == district) &&
            (identical(other.nickname, nickname) ||
                other.nickname == nickname) &&
            (identical(other.university, university) ||
                other.university == university));
  }

  @override
  int get hashCode =>
      Object.hash(runtimeType, city, district, nickname, university);

  @JsonKey(ignore: true)
  @override
  _$UserInfoRequestCopyWith<_UserInfoRequest> get copyWith =>
      __$UserInfoRequestCopyWithImpl<_UserInfoRequest>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_UserInfoRequestToJson(this);
  }
}

abstract class _UserInfoRequest implements UserInfoRequest {
  factory _UserInfoRequest(
      {required String city,
      required String district,
      required String nickname,
      required String university}) = _$_UserInfoRequest;

  factory _UserInfoRequest.fromJson(Map<String, dynamic> json) =
      _$_UserInfoRequest.fromJson;

  @override
  String get city;
  @override
  String get district;
  @override
  String get nickname;
  @override
  String get university;
  @override
  @JsonKey(ignore: true)
  _$UserInfoRequestCopyWith<_UserInfoRequest> get copyWith =>
      throw _privateConstructorUsedError;
}
