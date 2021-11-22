// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'schedule_response.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

ScheduleResponse _$ScheduleResponseFromJson(Map<String, dynamic> json) {
  return _ScheduleResponse.fromJson(json);
}

/// @nodoc
class _$ScheduleResponseTearOff {
  const _$ScheduleResponseTearOff();

  _ScheduleResponse call(
      {required bool manager, required List<ScheduleDetail> schedules}) {
    return _ScheduleResponse(
      manager: manager,
      schedules: schedules,
    );
  }

  ScheduleResponse fromJson(Map<String, Object?> json) {
    return ScheduleResponse.fromJson(json);
  }
}

/// @nodoc
const $ScheduleResponse = _$ScheduleResponseTearOff();

/// @nodoc
mixin _$ScheduleResponse {
  bool get manager => throw _privateConstructorUsedError;
  List<ScheduleDetail> get schedules => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $ScheduleResponseCopyWith<ScheduleResponse> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $ScheduleResponseCopyWith<$Res> {
  factory $ScheduleResponseCopyWith(
          ScheduleResponse value, $Res Function(ScheduleResponse) then) =
      _$ScheduleResponseCopyWithImpl<$Res>;
  $Res call({bool manager, List<ScheduleDetail> schedules});
}

/// @nodoc
class _$ScheduleResponseCopyWithImpl<$Res>
    implements $ScheduleResponseCopyWith<$Res> {
  _$ScheduleResponseCopyWithImpl(this._value, this._then);

  final ScheduleResponse _value;
  // ignore: unused_field
  final $Res Function(ScheduleResponse) _then;

  @override
  $Res call({
    Object? manager = freezed,
    Object? schedules = freezed,
  }) {
    return _then(_value.copyWith(
      manager: manager == freezed
          ? _value.manager
          : manager // ignore: cast_nullable_to_non_nullable
              as bool,
      schedules: schedules == freezed
          ? _value.schedules
          : schedules // ignore: cast_nullable_to_non_nullable
              as List<ScheduleDetail>,
    ));
  }
}

/// @nodoc
abstract class _$ScheduleResponseCopyWith<$Res>
    implements $ScheduleResponseCopyWith<$Res> {
  factory _$ScheduleResponseCopyWith(
          _ScheduleResponse value, $Res Function(_ScheduleResponse) then) =
      __$ScheduleResponseCopyWithImpl<$Res>;
  @override
  $Res call({bool manager, List<ScheduleDetail> schedules});
}

/// @nodoc
class __$ScheduleResponseCopyWithImpl<$Res>
    extends _$ScheduleResponseCopyWithImpl<$Res>
    implements _$ScheduleResponseCopyWith<$Res> {
  __$ScheduleResponseCopyWithImpl(
      _ScheduleResponse _value, $Res Function(_ScheduleResponse) _then)
      : super(_value, (v) => _then(v as _ScheduleResponse));

  @override
  _ScheduleResponse get _value => super._value as _ScheduleResponse;

  @override
  $Res call({
    Object? manager = freezed,
    Object? schedules = freezed,
  }) {
    return _then(_ScheduleResponse(
      manager: manager == freezed
          ? _value.manager
          : manager // ignore: cast_nullable_to_non_nullable
              as bool,
      schedules: schedules == freezed
          ? _value.schedules
          : schedules // ignore: cast_nullable_to_non_nullable
              as List<ScheduleDetail>,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_ScheduleResponse implements _ScheduleResponse {
  _$_ScheduleResponse({required this.manager, required this.schedules});

  factory _$_ScheduleResponse.fromJson(Map<String, dynamic> json) =>
      _$$_ScheduleResponseFromJson(json);

  @override
  final bool manager;
  @override
  final List<ScheduleDetail> schedules;

  @override
  String toString() {
    return 'ScheduleResponse(manager: $manager, schedules: $schedules)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _ScheduleResponse &&
            (identical(other.manager, manager) || other.manager == manager) &&
            const DeepCollectionEquality().equals(other.schedules, schedules));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType, manager, const DeepCollectionEquality().hash(schedules));

  @JsonKey(ignore: true)
  @override
  _$ScheduleResponseCopyWith<_ScheduleResponse> get copyWith =>
      __$ScheduleResponseCopyWithImpl<_ScheduleResponse>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_ScheduleResponseToJson(this);
  }
}

abstract class _ScheduleResponse implements ScheduleResponse {
  factory _ScheduleResponse(
      {required bool manager,
      required List<ScheduleDetail> schedules}) = _$_ScheduleResponse;

  factory _ScheduleResponse.fromJson(Map<String, dynamic> json) =
      _$_ScheduleResponse.fromJson;

  @override
  bool get manager;
  @override
  List<ScheduleDetail> get schedules;
  @override
  @JsonKey(ignore: true)
  _$ScheduleResponseCopyWith<_ScheduleResponse> get copyWith =>
      throw _privateConstructorUsedError;
}
