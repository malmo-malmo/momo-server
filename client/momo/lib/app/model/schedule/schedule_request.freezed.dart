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
      {required int groupId,
      required String title,
      required bool isOffline,
      required String startDateTime,
      required String contents}) {
    return _ScheduleRequest(
      groupId: groupId,
      title: title,
      isOffline: isOffline,
      startDateTime: startDateTime,
      contents: contents,
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
  int get groupId => throw _privateConstructorUsedError;
  String get title => throw _privateConstructorUsedError;
  bool get isOffline => throw _privateConstructorUsedError;
  String get startDateTime => throw _privateConstructorUsedError;
  String get contents => throw _privateConstructorUsedError;

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
      {int groupId,
      String title,
      bool isOffline,
      String startDateTime,
      String contents});
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
    Object? groupId = freezed,
    Object? title = freezed,
    Object? isOffline = freezed,
    Object? startDateTime = freezed,
    Object? contents = freezed,
  }) {
    return _then(_value.copyWith(
      groupId: groupId == freezed
          ? _value.groupId
          : groupId // ignore: cast_nullable_to_non_nullable
              as int,
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      isOffline: isOffline == freezed
          ? _value.isOffline
          : isOffline // ignore: cast_nullable_to_non_nullable
              as bool,
      startDateTime: startDateTime == freezed
          ? _value.startDateTime
          : startDateTime // ignore: cast_nullable_to_non_nullable
              as String,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
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
      {int groupId,
      String title,
      bool isOffline,
      String startDateTime,
      String contents});
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
    Object? groupId = freezed,
    Object? title = freezed,
    Object? isOffline = freezed,
    Object? startDateTime = freezed,
    Object? contents = freezed,
  }) {
    return _then(_ScheduleRequest(
      groupId: groupId == freezed
          ? _value.groupId
          : groupId // ignore: cast_nullable_to_non_nullable
              as int,
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      isOffline: isOffline == freezed
          ? _value.isOffline
          : isOffline // ignore: cast_nullable_to_non_nullable
              as bool,
      startDateTime: startDateTime == freezed
          ? _value.startDateTime
          : startDateTime // ignore: cast_nullable_to_non_nullable
              as String,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_ScheduleRequest implements _ScheduleRequest {
  _$_ScheduleRequest(
      {required this.groupId,
      required this.title,
      required this.isOffline,
      required this.startDateTime,
      required this.contents});

  factory _$_ScheduleRequest.fromJson(Map<String, dynamic> json) =>
      _$$_ScheduleRequestFromJson(json);

  @override
  final int groupId;
  @override
  final String title;
  @override
  final bool isOffline;
  @override
  final String startDateTime;
  @override
  final String contents;

  @override
  String toString() {
    return 'ScheduleRequest(groupId: $groupId, title: $title, isOffline: $isOffline, startDateTime: $startDateTime, contents: $contents)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _ScheduleRequest &&
            (identical(other.groupId, groupId) || other.groupId == groupId) &&
            (identical(other.title, title) || other.title == title) &&
            (identical(other.isOffline, isOffline) ||
                other.isOffline == isOffline) &&
            (identical(other.startDateTime, startDateTime) ||
                other.startDateTime == startDateTime) &&
            (identical(other.contents, contents) ||
                other.contents == contents));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType, groupId, title, isOffline, startDateTime, contents);

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
      {required int groupId,
      required String title,
      required bool isOffline,
      required String startDateTime,
      required String contents}) = _$_ScheduleRequest;

  factory _ScheduleRequest.fromJson(Map<String, dynamic> json) =
      _$_ScheduleRequest.fromJson;

  @override
  int get groupId;
  @override
  String get title;
  @override
  bool get isOffline;
  @override
  String get startDateTime;
  @override
  String get contents;
  @override
  @JsonKey(ignore: true)
  _$ScheduleRequestCopyWith<_ScheduleRequest> get copyWith =>
      throw _privateConstructorUsedError;
}
