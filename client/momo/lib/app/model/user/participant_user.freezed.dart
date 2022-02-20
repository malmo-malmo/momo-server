// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'participant_user.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

ParticipantUser _$ParticipantUserFromJson(Map<String, dynamic> json) {
  return _ParticipantUser.fromJson(json);
}

/// @nodoc
class _$ParticipantUserTearOff {
  const _$ParticipantUserTearOff();

  _ParticipantUser call(
      {required int participantId,
      required String nickname,
      String? imageUrl,
      required int attendanceRate}) {
    return _ParticipantUser(
      participantId: participantId,
      nickname: nickname,
      imageUrl: imageUrl,
      attendanceRate: attendanceRate,
    );
  }

  ParticipantUser fromJson(Map<String, Object?> json) {
    return ParticipantUser.fromJson(json);
  }
}

/// @nodoc
const $ParticipantUser = _$ParticipantUserTearOff();

/// @nodoc
mixin _$ParticipantUser {
  int get participantId => throw _privateConstructorUsedError;
  String get nickname => throw _privateConstructorUsedError;
  String? get imageUrl => throw _privateConstructorUsedError;
  int get attendanceRate => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $ParticipantUserCopyWith<ParticipantUser> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $ParticipantUserCopyWith<$Res> {
  factory $ParticipantUserCopyWith(
          ParticipantUser value, $Res Function(ParticipantUser) then) =
      _$ParticipantUserCopyWithImpl<$Res>;
  $Res call(
      {int participantId,
      String nickname,
      String? imageUrl,
      int attendanceRate});
}

/// @nodoc
class _$ParticipantUserCopyWithImpl<$Res>
    implements $ParticipantUserCopyWith<$Res> {
  _$ParticipantUserCopyWithImpl(this._value, this._then);

  final ParticipantUser _value;
  // ignore: unused_field
  final $Res Function(ParticipantUser) _then;

  @override
  $Res call({
    Object? participantId = freezed,
    Object? nickname = freezed,
    Object? imageUrl = freezed,
    Object? attendanceRate = freezed,
  }) {
    return _then(_value.copyWith(
      participantId: participantId == freezed
          ? _value.participantId
          : participantId // ignore: cast_nullable_to_non_nullable
              as int,
      nickname: nickname == freezed
          ? _value.nickname
          : nickname // ignore: cast_nullable_to_non_nullable
              as String,
      imageUrl: imageUrl == freezed
          ? _value.imageUrl
          : imageUrl // ignore: cast_nullable_to_non_nullable
              as String?,
      attendanceRate: attendanceRate == freezed
          ? _value.attendanceRate
          : attendanceRate // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
abstract class _$ParticipantUserCopyWith<$Res>
    implements $ParticipantUserCopyWith<$Res> {
  factory _$ParticipantUserCopyWith(
          _ParticipantUser value, $Res Function(_ParticipantUser) then) =
      __$ParticipantUserCopyWithImpl<$Res>;
  @override
  $Res call(
      {int participantId,
      String nickname,
      String? imageUrl,
      int attendanceRate});
}

/// @nodoc
class __$ParticipantUserCopyWithImpl<$Res>
    extends _$ParticipantUserCopyWithImpl<$Res>
    implements _$ParticipantUserCopyWith<$Res> {
  __$ParticipantUserCopyWithImpl(
      _ParticipantUser _value, $Res Function(_ParticipantUser) _then)
      : super(_value, (v) => _then(v as _ParticipantUser));

  @override
  _ParticipantUser get _value => super._value as _ParticipantUser;

  @override
  $Res call({
    Object? participantId = freezed,
    Object? nickname = freezed,
    Object? imageUrl = freezed,
    Object? attendanceRate = freezed,
  }) {
    return _then(_ParticipantUser(
      participantId: participantId == freezed
          ? _value.participantId
          : participantId // ignore: cast_nullable_to_non_nullable
              as int,
      nickname: nickname == freezed
          ? _value.nickname
          : nickname // ignore: cast_nullable_to_non_nullable
              as String,
      imageUrl: imageUrl == freezed
          ? _value.imageUrl
          : imageUrl // ignore: cast_nullable_to_non_nullable
              as String?,
      attendanceRate: attendanceRate == freezed
          ? _value.attendanceRate
          : attendanceRate // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_ParticipantUser implements _ParticipantUser {
  _$_ParticipantUser(
      {required this.participantId,
      required this.nickname,
      this.imageUrl,
      required this.attendanceRate});

  factory _$_ParticipantUser.fromJson(Map<String, dynamic> json) =>
      _$$_ParticipantUserFromJson(json);

  @override
  final int participantId;
  @override
  final String nickname;
  @override
  final String? imageUrl;
  @override
  final int attendanceRate;

  @override
  String toString() {
    return 'ParticipantUser(participantId: $participantId, nickname: $nickname, imageUrl: $imageUrl, attendanceRate: $attendanceRate)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _ParticipantUser &&
            (identical(other.participantId, participantId) ||
                other.participantId == participantId) &&
            (identical(other.nickname, nickname) ||
                other.nickname == nickname) &&
            (identical(other.imageUrl, imageUrl) ||
                other.imageUrl == imageUrl) &&
            (identical(other.attendanceRate, attendanceRate) ||
                other.attendanceRate == attendanceRate));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType, participantId, nickname, imageUrl, attendanceRate);

  @JsonKey(ignore: true)
  @override
  _$ParticipantUserCopyWith<_ParticipantUser> get copyWith =>
      __$ParticipantUserCopyWithImpl<_ParticipantUser>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_ParticipantUserToJson(this);
  }
}

abstract class _ParticipantUser implements ParticipantUser {
  factory _ParticipantUser(
      {required int participantId,
      required String nickname,
      String? imageUrl,
      required int attendanceRate}) = _$_ParticipantUser;

  factory _ParticipantUser.fromJson(Map<String, dynamic> json) =
      _$_ParticipantUser.fromJson;

  @override
  int get participantId;
  @override
  String get nickname;
  @override
  String? get imageUrl;
  @override
  int get attendanceRate;
  @override
  @JsonKey(ignore: true)
  _$ParticipantUserCopyWith<_ParticipantUser> get copyWith =>
      throw _privateConstructorUsedError;
}
