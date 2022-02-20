// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'attendance_check_create_request.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

AttendanceCheckCreateRequest _$AttendanceCheckCreateRequestFromJson(
    Map<String, dynamic> json) {
  return _AttendanceCheckCreateRequest.fromJson(json);
}

/// @nodoc
class _$AttendanceCheckCreateRequestTearOff {
  const _$AttendanceCheckCreateRequestTearOff();

  _AttendanceCheckCreateRequest call(
      {required int scheduleId,
      required List<AttendanceCreateRequest> attendanceCreateRequests}) {
    return _AttendanceCheckCreateRequest(
      scheduleId: scheduleId,
      attendanceCreateRequests: attendanceCreateRequests,
    );
  }

  AttendanceCheckCreateRequest fromJson(Map<String, Object?> json) {
    return AttendanceCheckCreateRequest.fromJson(json);
  }
}

/// @nodoc
const $AttendanceCheckCreateRequest = _$AttendanceCheckCreateRequestTearOff();

/// @nodoc
mixin _$AttendanceCheckCreateRequest {
  int get scheduleId => throw _privateConstructorUsedError;
  List<AttendanceCreateRequest> get attendanceCreateRequests =>
      throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $AttendanceCheckCreateRequestCopyWith<AttendanceCheckCreateRequest>
      get copyWith => throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $AttendanceCheckCreateRequestCopyWith<$Res> {
  factory $AttendanceCheckCreateRequestCopyWith(
          AttendanceCheckCreateRequest value,
          $Res Function(AttendanceCheckCreateRequest) then) =
      _$AttendanceCheckCreateRequestCopyWithImpl<$Res>;
  $Res call(
      {int scheduleId, List<AttendanceCreateRequest> attendanceCreateRequests});
}

/// @nodoc
class _$AttendanceCheckCreateRequestCopyWithImpl<$Res>
    implements $AttendanceCheckCreateRequestCopyWith<$Res> {
  _$AttendanceCheckCreateRequestCopyWithImpl(this._value, this._then);

  final AttendanceCheckCreateRequest _value;
  // ignore: unused_field
  final $Res Function(AttendanceCheckCreateRequest) _then;

  @override
  $Res call({
    Object? scheduleId = freezed,
    Object? attendanceCreateRequests = freezed,
  }) {
    return _then(_value.copyWith(
      scheduleId: scheduleId == freezed
          ? _value.scheduleId
          : scheduleId // ignore: cast_nullable_to_non_nullable
              as int,
      attendanceCreateRequests: attendanceCreateRequests == freezed
          ? _value.attendanceCreateRequests
          : attendanceCreateRequests // ignore: cast_nullable_to_non_nullable
              as List<AttendanceCreateRequest>,
    ));
  }
}

/// @nodoc
abstract class _$AttendanceCheckCreateRequestCopyWith<$Res>
    implements $AttendanceCheckCreateRequestCopyWith<$Res> {
  factory _$AttendanceCheckCreateRequestCopyWith(
          _AttendanceCheckCreateRequest value,
          $Res Function(_AttendanceCheckCreateRequest) then) =
      __$AttendanceCheckCreateRequestCopyWithImpl<$Res>;
  @override
  $Res call(
      {int scheduleId, List<AttendanceCreateRequest> attendanceCreateRequests});
}

/// @nodoc
class __$AttendanceCheckCreateRequestCopyWithImpl<$Res>
    extends _$AttendanceCheckCreateRequestCopyWithImpl<$Res>
    implements _$AttendanceCheckCreateRequestCopyWith<$Res> {
  __$AttendanceCheckCreateRequestCopyWithImpl(
      _AttendanceCheckCreateRequest _value,
      $Res Function(_AttendanceCheckCreateRequest) _then)
      : super(_value, (v) => _then(v as _AttendanceCheckCreateRequest));

  @override
  _AttendanceCheckCreateRequest get _value =>
      super._value as _AttendanceCheckCreateRequest;

  @override
  $Res call({
    Object? scheduleId = freezed,
    Object? attendanceCreateRequests = freezed,
  }) {
    return _then(_AttendanceCheckCreateRequest(
      scheduleId: scheduleId == freezed
          ? _value.scheduleId
          : scheduleId // ignore: cast_nullable_to_non_nullable
              as int,
      attendanceCreateRequests: attendanceCreateRequests == freezed
          ? _value.attendanceCreateRequests
          : attendanceCreateRequests // ignore: cast_nullable_to_non_nullable
              as List<AttendanceCreateRequest>,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_AttendanceCheckCreateRequest implements _AttendanceCheckCreateRequest {
  _$_AttendanceCheckCreateRequest(
      {required this.scheduleId, required this.attendanceCreateRequests});

  factory _$_AttendanceCheckCreateRequest.fromJson(Map<String, dynamic> json) =>
      _$$_AttendanceCheckCreateRequestFromJson(json);

  @override
  final int scheduleId;
  @override
  final List<AttendanceCreateRequest> attendanceCreateRequests;

  @override
  String toString() {
    return 'AttendanceCheckCreateRequest(scheduleId: $scheduleId, attendanceCreateRequests: $attendanceCreateRequests)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _AttendanceCheckCreateRequest &&
            (identical(other.scheduleId, scheduleId) ||
                other.scheduleId == scheduleId) &&
            const DeepCollectionEquality().equals(
                other.attendanceCreateRequests, attendanceCreateRequests));
  }

  @override
  int get hashCode => Object.hash(runtimeType, scheduleId,
      const DeepCollectionEquality().hash(attendanceCreateRequests));

  @JsonKey(ignore: true)
  @override
  _$AttendanceCheckCreateRequestCopyWith<_AttendanceCheckCreateRequest>
      get copyWith => __$AttendanceCheckCreateRequestCopyWithImpl<
          _AttendanceCheckCreateRequest>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_AttendanceCheckCreateRequestToJson(this);
  }
}

abstract class _AttendanceCheckCreateRequest
    implements AttendanceCheckCreateRequest {
  factory _AttendanceCheckCreateRequest(
          {required int scheduleId,
          required List<AttendanceCreateRequest> attendanceCreateRequests}) =
      _$_AttendanceCheckCreateRequest;

  factory _AttendanceCheckCreateRequest.fromJson(Map<String, dynamic> json) =
      _$_AttendanceCheckCreateRequest.fromJson;

  @override
  int get scheduleId;
  @override
  List<AttendanceCreateRequest> get attendanceCreateRequests;
  @override
  @JsonKey(ignore: true)
  _$AttendanceCheckCreateRequestCopyWith<_AttendanceCheckCreateRequest>
      get copyWith => throw _privateConstructorUsedError;
}
