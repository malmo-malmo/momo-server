// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'user_update_response.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

UserUpdateResponse _$UserUpdateResponseFromJson(Map<String, dynamic> json) {
  return _UserUpdateReponse.fromJson(json);
}

/// @nodoc
class _$UserUpdateResponseTearOff {
  const _$UserUpdateResponseTearOff();

  _UserUpdateReponse call(
      {required String nickname,
      required CodeNamePair city,
      required String district,
      required String university,
      String? imageUrl}) {
    return _UserUpdateReponse(
      nickname: nickname,
      city: city,
      district: district,
      university: university,
      imageUrl: imageUrl,
    );
  }

  UserUpdateResponse fromJson(Map<String, Object?> json) {
    return UserUpdateResponse.fromJson(json);
  }
}

/// @nodoc
const $UserUpdateResponse = _$UserUpdateResponseTearOff();

/// @nodoc
mixin _$UserUpdateResponse {
  String get nickname => throw _privateConstructorUsedError;
  CodeNamePair get city => throw _privateConstructorUsedError;
  String get district => throw _privateConstructorUsedError;
  String get university => throw _privateConstructorUsedError;
  String? get imageUrl => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $UserUpdateResponseCopyWith<UserUpdateResponse> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $UserUpdateResponseCopyWith<$Res> {
  factory $UserUpdateResponseCopyWith(
          UserUpdateResponse value, $Res Function(UserUpdateResponse) then) =
      _$UserUpdateResponseCopyWithImpl<$Res>;
  $Res call(
      {String nickname,
      CodeNamePair city,
      String district,
      String university,
      String? imageUrl});

  $CodeNamePairCopyWith<$Res> get city;
}

/// @nodoc
class _$UserUpdateResponseCopyWithImpl<$Res>
    implements $UserUpdateResponseCopyWith<$Res> {
  _$UserUpdateResponseCopyWithImpl(this._value, this._then);

  final UserUpdateResponse _value;
  // ignore: unused_field
  final $Res Function(UserUpdateResponse) _then;

  @override
  $Res call({
    Object? nickname = freezed,
    Object? city = freezed,
    Object? district = freezed,
    Object? university = freezed,
    Object? imageUrl = freezed,
  }) {
    return _then(_value.copyWith(
      nickname: nickname == freezed
          ? _value.nickname
          : nickname // ignore: cast_nullable_to_non_nullable
              as String,
      city: city == freezed
          ? _value.city
          : city // ignore: cast_nullable_to_non_nullable
              as CodeNamePair,
      district: district == freezed
          ? _value.district
          : district // ignore: cast_nullable_to_non_nullable
              as String,
      university: university == freezed
          ? _value.university
          : university // ignore: cast_nullable_to_non_nullable
              as String,
      imageUrl: imageUrl == freezed
          ? _value.imageUrl
          : imageUrl // ignore: cast_nullable_to_non_nullable
              as String?,
    ));
  }

  @override
  $CodeNamePairCopyWith<$Res> get city {
    return $CodeNamePairCopyWith<$Res>(_value.city, (value) {
      return _then(_value.copyWith(city: value));
    });
  }
}

/// @nodoc
abstract class _$UserUpdateReponseCopyWith<$Res>
    implements $UserUpdateResponseCopyWith<$Res> {
  factory _$UserUpdateReponseCopyWith(
          _UserUpdateReponse value, $Res Function(_UserUpdateReponse) then) =
      __$UserUpdateReponseCopyWithImpl<$Res>;
  @override
  $Res call(
      {String nickname,
      CodeNamePair city,
      String district,
      String university,
      String? imageUrl});

  @override
  $CodeNamePairCopyWith<$Res> get city;
}

/// @nodoc
class __$UserUpdateReponseCopyWithImpl<$Res>
    extends _$UserUpdateResponseCopyWithImpl<$Res>
    implements _$UserUpdateReponseCopyWith<$Res> {
  __$UserUpdateReponseCopyWithImpl(
      _UserUpdateReponse _value, $Res Function(_UserUpdateReponse) _then)
      : super(_value, (v) => _then(v as _UserUpdateReponse));

  @override
  _UserUpdateReponse get _value => super._value as _UserUpdateReponse;

  @override
  $Res call({
    Object? nickname = freezed,
    Object? city = freezed,
    Object? district = freezed,
    Object? university = freezed,
    Object? imageUrl = freezed,
  }) {
    return _then(_UserUpdateReponse(
      nickname: nickname == freezed
          ? _value.nickname
          : nickname // ignore: cast_nullable_to_non_nullable
              as String,
      city: city == freezed
          ? _value.city
          : city // ignore: cast_nullable_to_non_nullable
              as CodeNamePair,
      district: district == freezed
          ? _value.district
          : district // ignore: cast_nullable_to_non_nullable
              as String,
      university: university == freezed
          ? _value.university
          : university // ignore: cast_nullable_to_non_nullable
              as String,
      imageUrl: imageUrl == freezed
          ? _value.imageUrl
          : imageUrl // ignore: cast_nullable_to_non_nullable
              as String?,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_UserUpdateReponse implements _UserUpdateReponse {
  _$_UserUpdateReponse(
      {required this.nickname,
      required this.city,
      required this.district,
      required this.university,
      this.imageUrl});

  factory _$_UserUpdateReponse.fromJson(Map<String, dynamic> json) =>
      _$$_UserUpdateReponseFromJson(json);

  @override
  final String nickname;
  @override
  final CodeNamePair city;
  @override
  final String district;
  @override
  final String university;
  @override
  final String? imageUrl;

  @override
  String toString() {
    return 'UserUpdateResponse(nickname: $nickname, city: $city, district: $district, university: $university, imageUrl: $imageUrl)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _UserUpdateReponse &&
            (identical(other.nickname, nickname) ||
                other.nickname == nickname) &&
            (identical(other.city, city) || other.city == city) &&
            (identical(other.district, district) ||
                other.district == district) &&
            (identical(other.university, university) ||
                other.university == university) &&
            (identical(other.imageUrl, imageUrl) ||
                other.imageUrl == imageUrl));
  }

  @override
  int get hashCode =>
      Object.hash(runtimeType, nickname, city, district, university, imageUrl);

  @JsonKey(ignore: true)
  @override
  _$UserUpdateReponseCopyWith<_UserUpdateReponse> get copyWith =>
      __$UserUpdateReponseCopyWithImpl<_UserUpdateReponse>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_UserUpdateReponseToJson(this);
  }
}

abstract class _UserUpdateReponse implements UserUpdateResponse {
  factory _UserUpdateReponse(
      {required String nickname,
      required CodeNamePair city,
      required String district,
      required String university,
      String? imageUrl}) = _$_UserUpdateReponse;

  factory _UserUpdateReponse.fromJson(Map<String, dynamic> json) =
      _$_UserUpdateReponse.fromJson;

  @override
  String get nickname;
  @override
  CodeNamePair get city;
  @override
  String get district;
  @override
  String get university;
  @override
  String? get imageUrl;
  @override
  @JsonKey(ignore: true)
  _$UserUpdateReponseCopyWith<_UserUpdateReponse> get copyWith =>
      throw _privateConstructorUsedError;
}
