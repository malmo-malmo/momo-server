// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'attendance_create_request.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

AttendanceCreateRequest _$AttendanceCreateRequestFromJson(
    Map<String, dynamic> json) {
  return _AttendanceCreateRequest.fromJson(json);
}

/// @nodoc
class _$AttendanceCreateRequestTearOff {
  const _$AttendanceCreateRequestTearOff();

  _AttendanceCreateRequest call(
      {required int participantId, required bool attend}) {
    return _AttendanceCreateRequest(
      participantId: participantId,
      attend: attend,
    );
  }

  AttendanceCreateRequest fromJson(Map<String, Object?> json) {
    return AttendanceCreateRequest.fromJson(json);
  }
}

/// @nodoc
const $AttendanceCreateRequest = _$AttendanceCreateRequestTearOff();

/// @nodoc
mixin _$AttendanceCreateRequest {
  int get participantId => throw _privateConstructorUsedError;
  bool get attend => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $AttendanceCreateRequestCopyWith<AttendanceCreateRequest> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $AttendanceCreateRequestCopyWith<$Res> {
  factory $AttendanceCreateRequestCopyWith(AttendanceCreateRequest value,
          $Res Function(AttendanceCreateRequest) then) =
      _$AttendanceCreateRequestCopyWithImpl<$Res>;
  $Res call({int participantId, bool attend});
}

/// @nodoc
class _$AttendanceCreateRequestCopyWithImpl<$Res>
    implements $AttendanceCreateRequestCopyWith<$Res> {
  _$AttendanceCreateRequestCopyWithImpl(this._value, this._then);

  final AttendanceCreateRequest _value;
  // ignore: unused_field
  final $Res Function(AttendanceCreateRequest) _then;

  @override
  $Res call({
    Object? participantId = freezed,
    Object? attend = freezed,
  }) {
    return _then(_value.copyWith(
      participantId: participantId == freezed
          ? _value.participantId
          : participantId // ignore: cast_nullable_to_non_nullable
              as int,
      attend: attend == freezed
          ? _value.attend
          : attend // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc
abstract class _$AttendanceCreateRequestCopyWith<$Res>
    implements $AttendanceCreateRequestCopyWith<$Res> {
  factory _$AttendanceCreateRequestCopyWith(_AttendanceCreateRequest value,
          $Res Function(_AttendanceCreateRequest) then) =
      __$AttendanceCreateRequestCopyWithImpl<$Res>;
  @override
  $Res call({int participantId, bool attend});
}

/// @nodoc
class __$AttendanceCreateRequestCopyWithImpl<$Res>
    extends _$AttendanceCreateRequestCopyWithImpl<$Res>
    implements _$AttendanceCreateRequestCopyWith<$Res> {
  __$AttendanceCreateRequestCopyWithImpl(_AttendanceCreateRequest _value,
      $Res Function(_AttendanceCreateRequest) _then)
      : super(_value, (v) => _then(v as _AttendanceCreateRequest));

  @override
  _AttendanceCreateRequest get _value =>
      super._value as _AttendanceCreateRequest;

  @override
  $Res call({
    Object? participantId = freezed,
    Object? attend = freezed,
  }) {
    return _then(_AttendanceCreateRequest(
      participantId: participantId == freezed
          ? _value.participantId
          : participantId // ignore: cast_nullable_to_non_nullable
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
class _$_AttendanceCreateRequest implements _AttendanceCreateRequest {
  _$_AttendanceCreateRequest(
      {required this.participantId, required this.attend});

  factory _$_AttendanceCreateRequest.fromJson(Map<String, dynamic> json) =>
      _$$_AttendanceCreateRequestFromJson(json);

  @override
  final int participantId;
  @override
  final bool attend;

  @override
  String toString() {
    return 'AttendanceCreateRequest(participantId: $participantId, attend: $attend)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _AttendanceCreateRequest &&
            (identical(other.participantId, participantId) ||
                other.participantId == participantId) &&
            (identical(other.attend, attend) || other.attend == attend));
  }

  @override
  int get hashCode => Object.hash(runtimeType, participantId, attend);

  @JsonKey(ignore: true)
  @override
  _$AttendanceCreateRequestCopyWith<_AttendanceCreateRequest> get copyWith =>
      __$AttendanceCreateRequestCopyWithImpl<_AttendanceCreateRequest>(
          this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_AttendanceCreateRequestToJson(this);
  }
}

abstract class _AttendanceCreateRequest implements AttendanceCreateRequest {
  factory _AttendanceCreateRequest(
      {required int participantId,
      required bool attend}) = _$_AttendanceCreateRequest;

  factory _AttendanceCreateRequest.fromJson(Map<String, dynamic> json) =
      _$_AttendanceCreateRequest.fromJson;

  @override
  int get participantId;
  @override
  bool get attend;
  @override
  @JsonKey(ignore: true)
  _$AttendanceCreateRequestCopyWith<_AttendanceCreateRequest> get copyWith =>
      throw _privateConstructorUsedError;
}
