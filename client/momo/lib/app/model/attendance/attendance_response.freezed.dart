// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'attendance_response.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

AttendanceResponse _$AttendanceResponseFromJson(Map<String, dynamic> json) {
  return _AttendanceResponse.fromJson(json);
}

/// @nodoc
class _$AttendanceResponseTearOff {
  const _$AttendanceResponseTearOff();

  _AttendanceResponse call(
      {required int attendanceId,
      required String nickname,
      String? imageUrl,
      required bool isAttend,
      required int attendanceRate}) {
    return _AttendanceResponse(
      attendanceId: attendanceId,
      nickname: nickname,
      imageUrl: imageUrl,
      isAttend: isAttend,
      attendanceRate: attendanceRate,
    );
  }

  AttendanceResponse fromJson(Map<String, Object?> json) {
    return AttendanceResponse.fromJson(json);
  }
}

/// @nodoc
const $AttendanceResponse = _$AttendanceResponseTearOff();

/// @nodoc
mixin _$AttendanceResponse {
  int get attendanceId => throw _privateConstructorUsedError;
  String get nickname => throw _privateConstructorUsedError;
  String? get imageUrl => throw _privateConstructorUsedError;
  bool get isAttend => throw _privateConstructorUsedError;
  int get attendanceRate => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $AttendanceResponseCopyWith<AttendanceResponse> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $AttendanceResponseCopyWith<$Res> {
  factory $AttendanceResponseCopyWith(
          AttendanceResponse value, $Res Function(AttendanceResponse) then) =
      _$AttendanceResponseCopyWithImpl<$Res>;
  $Res call(
      {int attendanceId,
      String nickname,
      String? imageUrl,
      bool isAttend,
      int attendanceRate});
}

/// @nodoc
class _$AttendanceResponseCopyWithImpl<$Res>
    implements $AttendanceResponseCopyWith<$Res> {
  _$AttendanceResponseCopyWithImpl(this._value, this._then);

  final AttendanceResponse _value;
  // ignore: unused_field
  final $Res Function(AttendanceResponse) _then;

  @override
  $Res call({
    Object? attendanceId = freezed,
    Object? nickname = freezed,
    Object? imageUrl = freezed,
    Object? isAttend = freezed,
    Object? attendanceRate = freezed,
  }) {
    return _then(_value.copyWith(
      attendanceId: attendanceId == freezed
          ? _value.attendanceId
          : attendanceId // ignore: cast_nullable_to_non_nullable
              as int,
      nickname: nickname == freezed
          ? _value.nickname
          : nickname // ignore: cast_nullable_to_non_nullable
              as String,
      imageUrl: imageUrl == freezed
          ? _value.imageUrl
          : imageUrl // ignore: cast_nullable_to_non_nullable
              as String?,
      isAttend: isAttend == freezed
          ? _value.isAttend
          : isAttend // ignore: cast_nullable_to_non_nullable
              as bool,
      attendanceRate: attendanceRate == freezed
          ? _value.attendanceRate
          : attendanceRate // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
abstract class _$AttendanceResponseCopyWith<$Res>
    implements $AttendanceResponseCopyWith<$Res> {
  factory _$AttendanceResponseCopyWith(
          _AttendanceResponse value, $Res Function(_AttendanceResponse) then) =
      __$AttendanceResponseCopyWithImpl<$Res>;
  @override
  $Res call(
      {int attendanceId,
      String nickname,
      String? imageUrl,
      bool isAttend,
      int attendanceRate});
}

/// @nodoc
class __$AttendanceResponseCopyWithImpl<$Res>
    extends _$AttendanceResponseCopyWithImpl<$Res>
    implements _$AttendanceResponseCopyWith<$Res> {
  __$AttendanceResponseCopyWithImpl(
      _AttendanceResponse _value, $Res Function(_AttendanceResponse) _then)
      : super(_value, (v) => _then(v as _AttendanceResponse));

  @override
  _AttendanceResponse get _value => super._value as _AttendanceResponse;

  @override
  $Res call({
    Object? attendanceId = freezed,
    Object? nickname = freezed,
    Object? imageUrl = freezed,
    Object? isAttend = freezed,
    Object? attendanceRate = freezed,
  }) {
    return _then(_AttendanceResponse(
      attendanceId: attendanceId == freezed
          ? _value.attendanceId
          : attendanceId // ignore: cast_nullable_to_non_nullable
              as int,
      nickname: nickname == freezed
          ? _value.nickname
          : nickname // ignore: cast_nullable_to_non_nullable
              as String,
      imageUrl: imageUrl == freezed
          ? _value.imageUrl
          : imageUrl // ignore: cast_nullable_to_non_nullable
              as String?,
      isAttend: isAttend == freezed
          ? _value.isAttend
          : isAttend // ignore: cast_nullable_to_non_nullable
              as bool,
      attendanceRate: attendanceRate == freezed
          ? _value.attendanceRate
          : attendanceRate // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_AttendanceResponse implements _AttendanceResponse {
  _$_AttendanceResponse(
      {required this.attendanceId,
      required this.nickname,
      this.imageUrl,
      required this.isAttend,
      required this.attendanceRate});

  factory _$_AttendanceResponse.fromJson(Map<String, dynamic> json) =>
      _$$_AttendanceResponseFromJson(json);

  @override
  final int attendanceId;
  @override
  final String nickname;
  @override
  final String? imageUrl;
  @override
  final bool isAttend;
  @override
  final int attendanceRate;

  @override
  String toString() {
    return 'AttendanceResponse(attendanceId: $attendanceId, nickname: $nickname, imageUrl: $imageUrl, isAttend: $isAttend, attendanceRate: $attendanceRate)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _AttendanceResponse &&
            (identical(other.attendanceId, attendanceId) ||
                other.attendanceId == attendanceId) &&
            (identical(other.nickname, nickname) ||
                other.nickname == nickname) &&
            (identical(other.imageUrl, imageUrl) ||
                other.imageUrl == imageUrl) &&
            (identical(other.isAttend, isAttend) ||
                other.isAttend == isAttend) &&
            (identical(other.attendanceRate, attendanceRate) ||
                other.attendanceRate == attendanceRate));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType, attendanceId, nickname, imageUrl, isAttend, attendanceRate);

  @JsonKey(ignore: true)
  @override
  _$AttendanceResponseCopyWith<_AttendanceResponse> get copyWith =>
      __$AttendanceResponseCopyWithImpl<_AttendanceResponse>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_AttendanceResponseToJson(this);
  }
}

abstract class _AttendanceResponse implements AttendanceResponse {
  factory _AttendanceResponse(
      {required int attendanceId,
      required String nickname,
      String? imageUrl,
      required bool isAttend,
      required int attendanceRate}) = _$_AttendanceResponse;

  factory _AttendanceResponse.fromJson(Map<String, dynamic> json) =
      _$_AttendanceResponse.fromJson;

  @override
  int get attendanceId;
  @override
  String get nickname;
  @override
  String? get imageUrl;
  @override
  bool get isAttend;
  @override
  int get attendanceRate;
  @override
  @JsonKey(ignore: true)
  _$AttendanceResponseCopyWith<_AttendanceResponse> get copyWith =>
      throw _privateConstructorUsedError;
}
