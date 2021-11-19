// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'schedule_request.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

ScheduleRequest _$ScheduleRequestFromJson(Map<String, dynamic> json) {
  return _ScheduleRequest.fromJson(json);
}

/// @nodoc
class _$ScheduleRequestTearOff {
  const _$ScheduleRequestTearOff();

  _ScheduleRequest call(
      {required String name,
      required String onOff,
      required String date,
      required String time,
      required String texts}) {
    return _ScheduleRequest(
      name: name,
      onOff: onOff,
      date: date,
      time: time,
      texts: texts,
    );
  }

  ScheduleRequest fromJson(Map<String, Object?> json) {
    return ScheduleRequest.fromJson(json);
  }
}

/// @nodoc
const $ScheduleRequest = _$ScheduleRequestTearOff();

/// @nodoc
mixin _$ScheduleRequest {
  String get name => throw _privateConstructorUsedError;
  String get onOff => throw _privateConstructorUsedError;
  String get date => throw _privateConstructorUsedError;
  String get time => throw _privateConstructorUsedError;
  String get texts => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $ScheduleRequestCopyWith<ScheduleRequest> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $ScheduleRequestCopyWith<$Res> {
  factory $ScheduleRequestCopyWith(
          ScheduleRequest value, $Res Function(ScheduleRequest) then) =
      _$ScheduleRequestCopyWithImpl<$Res>;
  $Res call(
      {String name, String onOff, String date, String time, String texts});
}

/// @nodoc
class _$ScheduleRequestCopyWithImpl<$Res>
    implements $ScheduleRequestCopyWith<$Res> {
  _$ScheduleRequestCopyWithImpl(this._value, this._then);

  final ScheduleRequest _value;
  // ignore: unused_field
  final $Res Function(ScheduleRequest) _then;

  @override
  $Res call({
    Object? name = freezed,
    Object? onOff = freezed,
    Object? date = freezed,
    Object? time = freezed,
    Object? texts = freezed,
  }) {
    return _then(_value.copyWith(
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      onOff: onOff == freezed
          ? _value.onOff
          : onOff // ignore: cast_nullable_to_non_nullable
              as String,
      date: date == freezed
          ? _value.date
          : date // ignore: cast_nullable_to_non_nullable
              as String,
      time: time == freezed
          ? _value.time
          : time // ignore: cast_nullable_to_non_nullable
              as String,
      texts: texts == freezed
          ? _value.texts
          : texts // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
abstract class _$ScheduleRequestCopyWith<$Res>
    implements $ScheduleRequestCopyWith<$Res> {
  factory _$ScheduleRequestCopyWith(
          _ScheduleRequest value, $Res Function(_ScheduleRequest) then) =
      __$ScheduleRequestCopyWithImpl<$Res>;
  @override
  $Res call(
      {String name, String onOff, String date, String time, String texts});
}

/// @nodoc
class __$ScheduleRequestCopyWithImpl<$Res>
    extends _$ScheduleRequestCopyWithImpl<$Res>
    implements _$ScheduleRequestCopyWith<$Res> {
  __$ScheduleRequestCopyWithImpl(
      _ScheduleRequest _value, $Res Function(_ScheduleRequest) _then)
      : super(_value, (v) => _then(v as _ScheduleRequest));

  @override
  _ScheduleRequest get _value => super._value as _ScheduleRequest;

  @override
  $Res call({
    Object? name = freezed,
    Object? onOff = freezed,
    Object? date = freezed,
    Object? time = freezed,
    Object? texts = freezed,
  }) {
    return _then(_ScheduleRequest(
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      onOff: onOff == freezed
          ? _value.onOff
          : onOff // ignore: cast_nullable_to_non_nullable
              as String,
      date: date == freezed
          ? _value.date
          : date // ignore: cast_nullable_to_non_nullable
              as String,
      time: time == freezed
          ? _value.time
          : time // ignore: cast_nullable_to_non_nullable
              as String,
      texts: texts == freezed
          ? _value.texts
          : texts // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_ScheduleRequest implements _ScheduleRequest {
  _$_ScheduleRequest(
      {required this.name,
      required this.onOff,
      required this.date,
      required this.time,
      required this.texts});

  factory _$_ScheduleRequest.fromJson(Map<String, dynamic> json) =>
      _$$_ScheduleRequestFromJson(json);

  @override
  final String name;
  @override
  final String onOff;
  @override
  final String date;
  @override
  final String time;
  @override
  final String texts;

  @override
  String toString() {
    return 'ScheduleRequest(name: $name, onOff: $onOff, date: $date, time: $time, texts: $texts)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _ScheduleRequest &&
            (identical(other.name, name) || other.name == name) &&
            (identical(other.onOff, onOff) || other.onOff == onOff) &&
            (identical(other.date, date) || other.date == date) &&
            (identical(other.time, time) || other.time == time) &&
            (identical(other.texts, texts) || other.texts == texts));
  }

  @override
  int get hashCode => Object.hash(runtimeType, name, onOff, date, time, texts);

  @JsonKey(ignore: true)
  @override
  _$ScheduleRequestCopyWith<_ScheduleRequest> get copyWith =>
      __$ScheduleRequestCopyWithImpl<_ScheduleRequest>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_ScheduleRequestToJson(this);
  }
}

abstract class _ScheduleRequest implements ScheduleRequest {
  factory _ScheduleRequest(
      {required String name,
      required String onOff,
      required String date,
      required String time,
      required String texts}) = _$_ScheduleRequest;

  factory _ScheduleRequest.fromJson(Map<String, dynamic> json) =
      _$_ScheduleRequest.fromJson;

  @override
  String get name;
  @override
  String get onOff;
  @override
  String get date;
  @override
  String get time;
  @override
  String get texts;
  @override
  @JsonKey(ignore: true)
  _$ScheduleRequestCopyWith<_ScheduleRequest> get copyWith =>
      throw _privateConstructorUsedError;
}
