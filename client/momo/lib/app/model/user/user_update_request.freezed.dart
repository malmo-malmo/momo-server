// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'user_update_request.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$UserUpdateRequestTearOff {
  const _$UserUpdateRequestTearOff();

  _UserUpdateInfo call(
      {required String city,
      required String district,
      required String nickname,
      required String university,
      required String imagePath}) {
    return _UserUpdateInfo(
      city: city,
      district: district,
      nickname: nickname,
      university: university,
      imagePath: imagePath,
    );
  }
}

/// @nodoc
const $UserUpdateRequest = _$UserUpdateRequestTearOff();

/// @nodoc
mixin _$UserUpdateRequest {
  String get city => throw _privateConstructorUsedError;
  String get district => throw _privateConstructorUsedError;
  String get nickname => throw _privateConstructorUsedError;
  String get university => throw _privateConstructorUsedError;
  String get imagePath => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $UserUpdateRequestCopyWith<UserUpdateRequest> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $UserUpdateRequestCopyWith<$Res> {
  factory $UserUpdateRequestCopyWith(
          UserUpdateRequest value, $Res Function(UserUpdateRequest) then) =
      _$UserUpdateRequestCopyWithImpl<$Res>;
  $Res call(
      {String city,
      String district,
      String nickname,
      String university,
      String imagePath});
}

/// @nodoc
class _$UserUpdateRequestCopyWithImpl<$Res>
    implements $UserUpdateRequestCopyWith<$Res> {
  _$UserUpdateRequestCopyWithImpl(this._value, this._then);

  final UserUpdateRequest _value;
  // ignore: unused_field
  final $Res Function(UserUpdateRequest) _then;

  @override
  $Res call({
    Object? city = freezed,
    Object? district = freezed,
    Object? nickname = freezed,
    Object? university = freezed,
    Object? imagePath = freezed,
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
      imagePath: imagePath == freezed
          ? _value.imagePath
          : imagePath // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
abstract class _$UserUpdateInfoCopyWith<$Res>
    implements $UserUpdateRequestCopyWith<$Res> {
  factory _$UserUpdateInfoCopyWith(
          _UserUpdateInfo value, $Res Function(_UserUpdateInfo) then) =
      __$UserUpdateInfoCopyWithImpl<$Res>;
  @override
  $Res call(
      {String city,
      String district,
      String nickname,
      String university,
      String imagePath});
}

/// @nodoc
class __$UserUpdateInfoCopyWithImpl<$Res>
    extends _$UserUpdateRequestCopyWithImpl<$Res>
    implements _$UserUpdateInfoCopyWith<$Res> {
  __$UserUpdateInfoCopyWithImpl(
      _UserUpdateInfo _value, $Res Function(_UserUpdateInfo) _then)
      : super(_value, (v) => _then(v as _UserUpdateInfo));

  @override
  _UserUpdateInfo get _value => super._value as _UserUpdateInfo;

  @override
  $Res call({
    Object? city = freezed,
    Object? district = freezed,
    Object? nickname = freezed,
    Object? university = freezed,
    Object? imagePath = freezed,
  }) {
    return _then(_UserUpdateInfo(
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
      imagePath: imagePath == freezed
          ? _value.imagePath
          : imagePath // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc

class _$_UserUpdateInfo implements _UserUpdateInfo {
  _$_UserUpdateInfo(
      {required this.city,
      required this.district,
      required this.nickname,
      required this.university,
      required this.imagePath});

  @override
  final String city;
  @override
  final String district;
  @override
  final String nickname;
  @override
  final String university;
  @override
  final String imagePath;

  @override
  String toString() {
    return 'UserUpdateRequest(city: $city, district: $district, nickname: $nickname, university: $university, imagePath: $imagePath)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _UserUpdateInfo &&
            (identical(other.city, city) || other.city == city) &&
            (identical(other.district, district) ||
                other.district == district) &&
            (identical(other.nickname, nickname) ||
                other.nickname == nickname) &&
            (identical(other.university, university) ||
                other.university == university) &&
            (identical(other.imagePath, imagePath) ||
                other.imagePath == imagePath));
  }

  @override
  int get hashCode =>
      Object.hash(runtimeType, city, district, nickname, university, imagePath);

  @JsonKey(ignore: true)
  @override
  _$UserUpdateInfoCopyWith<_UserUpdateInfo> get copyWith =>
      __$UserUpdateInfoCopyWithImpl<_UserUpdateInfo>(this, _$identity);
}

abstract class _UserUpdateInfo implements UserUpdateRequest {
  factory _UserUpdateInfo(
      {required String city,
      required String district,
      required String nickname,
      required String university,
      required String imagePath}) = _$_UserUpdateInfo;

  @override
  String get city;
  @override
  String get district;
  @override
  String get nickname;
  @override
  String get university;
  @override
  String get imagePath;
  @override
  @JsonKey(ignore: true)
  _$UserUpdateInfoCopyWith<_UserUpdateInfo> get copyWith =>
      throw _privateConstructorUsedError;
}
