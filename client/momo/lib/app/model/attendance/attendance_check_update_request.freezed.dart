// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'attendance_check_update_request.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

AttendanceCheckUpdateRequest _$AttendanceCheckUpdateRequestFromJson(
    Map<String, dynamic> json) {
  return _AttendanceCheckUpdateRequest.fromJson(json);
}

/// @nodoc
class _$AttendanceCheckUpdateRequestTearOff {
  const _$AttendanceCheckUpdateRequestTearOff();

  _AttendanceCheckUpdateRequest call(
      {required int scheduleId,
      required List<AttendanceUpdateRequest> attendanceUpdateRequests}) {
    return _AttendanceCheckUpdateRequest(
      scheduleId: scheduleId,
      attendanceUpdateRequests: attendanceUpdateRequests,
    );
  }

  AttendanceCheckUpdateRequest fromJson(Map<String, Object?> json) {
    return AttendanceCheckUpdateRequest.fromJson(json);
  }
}

/// @nodoc
const $AttendanceCheckUpdateRequest = _$AttendanceCheckUpdateRequestTearOff();

/// @nodoc
mixin _$AttendanceCheckUpdateRequest {
  int get scheduleId => throw _privateConstructorUsedError;
  List<AttendanceUpdateRequest> get attendanceUpdateRequests =>
      throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $AttendanceCheckUpdateRequestCopyWith<AttendanceCheckUpdateRequest>
      get copyWith => throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $AttendanceCheckUpdateRequestCopyWith<$Res> {
  factory $AttendanceCheckUpdateRequestCopyWith(
          AttendanceCheckUpdateRequest value,
          $Res Function(AttendanceCheckUpdateRequest) then) =
      _$AttendanceCheckUpdateRequestCopyWithImpl<$Res>;
  $Res call(
      {int scheduleId, List<AttendanceUpdateRequest> attendanceUpdateRequests});
}

/// @nodoc
class _$AttendanceCheckUpdateRequestCopyWithImpl<$Res>
    implements $AttendanceCheckUpdateRequestCopyWith<$Res> {
  _$AttendanceCheckUpdateRequestCopyWithImpl(this._value, this._then);

  final AttendanceCheckUpdateRequest _value;
  // ignore: unused_field
  final $Res Function(AttendanceCheckUpdateRequest) _then;

  @override
  $Res call({
    Object? scheduleId = freezed,
    Object? attendanceUpdateRequests = freezed,
  }) {
    return _then(_value.copyWith(
      scheduleId: scheduleId == freezed
          ? _value.scheduleId
          : scheduleId // ignore: cast_nullable_to_non_nullable
              as int,
      attendanceUpdateRequests: attendanceUpdateRequests == freezed
          ? _value.attendanceUpdateRequests
          : attendanceUpdateRequests // ignore: cast_nullable_to_non_nullable
              as List<AttendanceUpdateRequest>,
    ));
  }
}

/// @nodoc
abstract class _$AttendanceCheckUpdateRequestCopyWith<$Res>
    implements $AttendanceCheckUpdateRequestCopyWith<$Res> {
  factory _$AttendanceCheckUpdateRequestCopyWith(
          _AttendanceCheckUpdateRequest value,
          $Res Function(_AttendanceCheckUpdateRequest) then) =
      __$AttendanceCheckUpdateRequestCopyWithImpl<$Res>;
  @override
  $Res call(
      {int scheduleId, List<AttendanceUpdateRequest> attendanceUpdateRequests});
}

/// @nodoc
class __$AttendanceCheckUpdateRequestCopyWithImpl<$Res>
    extends _$AttendanceCheckUpdateRequestCopyWithImpl<$Res>
    implements _$AttendanceCheckUpdateRequestCopyWith<$Res> {
  __$AttendanceCheckUpdateRequestCopyWithImpl(
      _AttendanceCheckUpdateRequest _value,
      $Res Function(_AttendanceCheckUpdateRequest) _then)
      : super(_value, (v) => _then(v as _AttendanceCheckUpdateRequest));

  @override
  _AttendanceCheckUpdateRequest get _value =>
      super._value as _AttendanceCheckUpdateRequest;

  @override
  $Res call({
    Object? scheduleId = freezed,
    Object? attendanceUpdateRequests = freezed,
  }) {
    return _then(_AttendanceCheckUpdateRequest(
      scheduleId: scheduleId == freezed
          ? _value.scheduleId
          : scheduleId // ignore: cast_nullable_to_non_nullable
              as int,
      attendanceUpdateRequests: attendanceUpdateRequests == freezed
          ? _value.attendanceUpdateRequests
          : attendanceUpdateRequests // ignore: cast_nullable_to_non_nullable
              as List<AttendanceUpdateRequest>,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_AttendanceCheckUpdateRequest implements _AttendanceCheckUpdateRequest {
  _$_AttendanceCheckUpdateRequest(
      {required this.scheduleId, required this.attendanceUpdateRequests});

  factory _$_AttendanceCheckUpdateRequest.fromJson(Map<String, dynamic> json) =>
      _$$_AttendanceCheckUpdateRequestFromJson(json);

  @override
  final int scheduleId;
  @override
  final List<AttendanceUpdateRequest> attendanceUpdateRequests;

  @override
  String toString() {
    return 'AttendanceCheckUpdateRequest(scheduleId: $scheduleId, attendanceUpdateRequests: $attendanceUpdateRequests)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _AttendanceCheckUpdateRequest &&
            (identical(other.scheduleId, scheduleId) ||
                other.scheduleId == scheduleId) &&
            const DeepCollectionEquality().equals(
                other.attendanceUpdateRequests, attendanceUpdateRequests));
  }

  @override
  int get hashCode => Object.hash(runtimeType, scheduleId,
      const DeepCollectionEquality().hash(attendanceUpdateRequests));

  @JsonKey(ignore: true)
  @override
  _$AttendanceCheckUpdateRequestCopyWith<_AttendanceCheckUpdateRequest>
      get copyWith => __$AttendanceCheckUpdateRequestCopyWithImpl<
          _AttendanceCheckUpdateRequest>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_AttendanceCheckUpdateRequestToJson(this);
  }
}

abstract class _AttendanceCheckUpdateRequest
    implements AttendanceCheckUpdateRequest {
  factory _AttendanceCheckUpdateRequest(
          {required int scheduleId,
          required List<AttendanceUpdateRequest> attendanceUpdateRequests}) =
      _$_AttendanceCheckUpdateRequest;

  factory _AttendanceCheckUpdateRequest.fromJson(Map<String, dynamic> json) =
      _$_AttendanceCheckUpdateRequest.fromJson;

  @override
  int get scheduleId;
  @override
  List<AttendanceUpdateRequest> get attendanceUpdateRequests;
  @override
  @JsonKey(ignore: true)
  _$AttendanceCheckUpdateRequestCopyWith<_AttendanceCheckUpdateRequest>
      get copyWith => throw _privateConstructorUsedError;
}
