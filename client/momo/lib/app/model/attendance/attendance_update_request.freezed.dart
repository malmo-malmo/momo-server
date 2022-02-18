// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'attendance_update_request.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

AttendanceUpdateRequest _$AttendanceUpdateRequestFromJson(
    Map<String, dynamic> json) {
  return _AttendanceUpdateRequest.fromJson(json);
}

/// @nodoc
class _$AttendanceUpdateRequestTearOff {
  const _$AttendanceUpdateRequestTearOff();

  _AttendanceUpdateRequest call(
      {required int attendanceId, required bool attend}) {
    return _AttendanceUpdateRequest(
      attendanceId: attendanceId,
      attend: attend,
    );
  }

  AttendanceUpdateRequest fromJson(Map<String, Object?> json) {
    return AttendanceUpdateRequest.fromJson(json);
  }
}

/// @nodoc
const $AttendanceUpdateRequest = _$AttendanceUpdateRequestTearOff();

/// @nodoc
mixin _$AttendanceUpdateRequest {
  int get attendanceId => throw _privateConstructorUsedError;
  bool get attend => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $AttendanceUpdateRequestCopyWith<AttendanceUpdateRequest> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $AttendanceUpdateRequestCopyWith<$Res> {
  factory $AttendanceUpdateRequestCopyWith(AttendanceUpdateRequest value,
          $Res Function(AttendanceUpdateRequest) then) =
      _$AttendanceUpdateRequestCopyWithImpl<$Res>;
  $Res call({int attendanceId, bool attend});
}

/// @nodoc
class _$AttendanceUpdateRequestCopyWithImpl<$Res>
    implements $AttendanceUpdateRequestCopyWith<$Res> {
  _$AttendanceUpdateRequestCopyWithImpl(this._value, this._then);

  final AttendanceUpdateRequest _value;
  // ignore: unused_field
  final $Res Function(AttendanceUpdateRequest) _then;

  @override
  $Res call({
    Object? attendanceId = freezed,
    Object? attend = freezed,
  }) {
    return _then(_value.copyWith(
      attendanceId: attendanceId == freezed
          ? _value.attendanceId
          : attendanceId // ignore: cast_nullable_to_non_nullable
              as int,
      attend: attend == freezed
          ? _value.attend
          : attend // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc
abstract class _$AttendanceUpdateRequestCopyWith<$Res>
    implements $AttendanceUpdateRequestCopyWith<$Res> {
  factory _$AttendanceUpdateRequestCopyWith(_AttendanceUpdateRequest value,
          $Res Function(_AttendanceUpdateRequest) then) =
      __$AttendanceUpdateRequestCopyWithImpl<$Res>;
  @override
  $Res call({int attendanceId, bool attend});
}

/// @nodoc
class __$AttendanceUpdateRequestCopyWithImpl<$Res>
    extends _$AttendanceUpdateRequestCopyWithImpl<$Res>
    implements _$AttendanceUpdateRequestCopyWith<$Res> {
  __$AttendanceUpdateRequestCopyWithImpl(_AttendanceUpdateRequest _value,
      $Res Function(_AttendanceUpdateRequest) _then)
      : super(_value, (v) => _then(v as _AttendanceUpdateRequest));

  @override
  _AttendanceUpdateRequest get _value =>
      super._value as _AttendanceUpdateRequest;

  @override
  $Res call({
    Object? attendanceId = freezed,
    Object? attend = freezed,
  }) {
    return _then(_AttendanceUpdateRequest(
      attendanceId: attendanceId == freezed
          ? _value.attendanceId
          : attendanceId // ignore: cast_nullable_to_non_nullable
              as int,
      attend: attend == freezed
          ? _value.attend
          : attend // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_AttendanceUpdateRequest implements _AttendanceUpdateRequest {
  _$_AttendanceUpdateRequest(
      {required this.attendanceId, required this.attend});

  factory _$_AttendanceUpdateRequest.fromJson(Map<String, dynamic> json) =>
      _$$_AttendanceUpdateRequestFromJson(json);

  @override
  final int attendanceId;
  @override
  final bool attend;

  @override
  String toString() {
    return 'AttendanceUpdateRequest(attendanceId: $attendanceId, attend: $attend)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _AttendanceUpdateRequest &&
            (identical(other.attendanceId, attendanceId) ||
                other.attendanceId == attendanceId) &&
            (identical(other.attend, attend) || other.attend == attend));
  }

  @override
  int get hashCode => Object.hash(runtimeType, attendanceId, attend);

  @JsonKey(ignore: true)
  @override
  _$AttendanceUpdateRequestCopyWith<_AttendanceUpdateRequest> get copyWith =>
      __$AttendanceUpdateRequestCopyWithImpl<_AttendanceUpdateRequest>(
          this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_AttendanceUpdateRequestToJson(this);
  }
}

abstract class _AttendanceUpdateRequest implements AttendanceUpdateRequest {
  factory _AttendanceUpdateRequest(
      {required int attendanceId,
      required bool attend}) = _$_AttendanceUpdateRequest;

  factory _AttendanceUpdateRequest.fromJson(Map<String, dynamic> json) =
      _$_AttendanceUpdateRequest.fromJson;

  @override
  int get attendanceId;
  @override
  bool get attend;
  @override
  @JsonKey(ignore: true)
  _$AttendanceUpdateRequestCopyWith<_AttendanceUpdateRequest> get copyWith =>
      throw _privateConstructorUsedError;
}
