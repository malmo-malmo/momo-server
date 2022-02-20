// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'attendance_provider_arg.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$AttendanceProviderArgTearOff {
  const _$AttendanceProviderArgTearOff();

  _AttendanceProviderArg call(
      {required int scheduleId, required List<int> userIds}) {
    return _AttendanceProviderArg(
      scheduleId: scheduleId,
      userIds: userIds,
    );
  }
}

/// @nodoc
const $AttendanceProviderArg = _$AttendanceProviderArgTearOff();

/// @nodoc
mixin _$AttendanceProviderArg {
  int get scheduleId => throw _privateConstructorUsedError;
  List<int> get userIds => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $AttendanceProviderArgCopyWith<AttendanceProviderArg> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $AttendanceProviderArgCopyWith<$Res> {
  factory $AttendanceProviderArgCopyWith(AttendanceProviderArg value,
          $Res Function(AttendanceProviderArg) then) =
      _$AttendanceProviderArgCopyWithImpl<$Res>;
  $Res call({int scheduleId, List<int> userIds});
}

/// @nodoc
class _$AttendanceProviderArgCopyWithImpl<$Res>
    implements $AttendanceProviderArgCopyWith<$Res> {
  _$AttendanceProviderArgCopyWithImpl(this._value, this._then);

  final AttendanceProviderArg _value;
  // ignore: unused_field
  final $Res Function(AttendanceProviderArg) _then;

  @override
  $Res call({
    Object? scheduleId = freezed,
    Object? userIds = freezed,
  }) {
    return _then(_value.copyWith(
      scheduleId: scheduleId == freezed
          ? _value.scheduleId
          : scheduleId // ignore: cast_nullable_to_non_nullable
              as int,
      userIds: userIds == freezed
          ? _value.userIds
          : userIds // ignore: cast_nullable_to_non_nullable
              as List<int>,
    ));
  }
}

/// @nodoc
abstract class _$AttendanceProviderArgCopyWith<$Res>
    implements $AttendanceProviderArgCopyWith<$Res> {
  factory _$AttendanceProviderArgCopyWith(_AttendanceProviderArg value,
          $Res Function(_AttendanceProviderArg) then) =
      __$AttendanceProviderArgCopyWithImpl<$Res>;
  @override
  $Res call({int scheduleId, List<int> userIds});
}

/// @nodoc
class __$AttendanceProviderArgCopyWithImpl<$Res>
    extends _$AttendanceProviderArgCopyWithImpl<$Res>
    implements _$AttendanceProviderArgCopyWith<$Res> {
  __$AttendanceProviderArgCopyWithImpl(_AttendanceProviderArg _value,
      $Res Function(_AttendanceProviderArg) _then)
      : super(_value, (v) => _then(v as _AttendanceProviderArg));

  @override
  _AttendanceProviderArg get _value => super._value as _AttendanceProviderArg;

  @override
  $Res call({
    Object? scheduleId = freezed,
    Object? userIds = freezed,
  }) {
    return _then(_AttendanceProviderArg(
      scheduleId: scheduleId == freezed
          ? _value.scheduleId
          : scheduleId // ignore: cast_nullable_to_non_nullable
              as int,
      userIds: userIds == freezed
          ? _value.userIds
          : userIds // ignore: cast_nullable_to_non_nullable
              as List<int>,
    ));
  }
}

/// @nodoc

class _$_AttendanceProviderArg implements _AttendanceProviderArg {
  _$_AttendanceProviderArg({required this.scheduleId, required this.userIds});

  @override
  final int scheduleId;
  @override
  final List<int> userIds;

  @override
  String toString() {
    return 'AttendanceProviderArg(scheduleId: $scheduleId, userIds: $userIds)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _AttendanceProviderArg &&
            (identical(other.scheduleId, scheduleId) ||
                other.scheduleId == scheduleId) &&
            const DeepCollectionEquality().equals(other.userIds, userIds));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType, scheduleId, const DeepCollectionEquality().hash(userIds));

  @JsonKey(ignore: true)
  @override
  _$AttendanceProviderArgCopyWith<_AttendanceProviderArg> get copyWith =>
      __$AttendanceProviderArgCopyWithImpl<_AttendanceProviderArg>(
          this, _$identity);
}

abstract class _AttendanceProviderArg implements AttendanceProviderArg {
  factory _AttendanceProviderArg(
      {required int scheduleId,
      required List<int> userIds}) = _$_AttendanceProviderArg;

  @override
  int get scheduleId;
  @override
  List<int> get userIds;
  @override
  @JsonKey(ignore: true)
  _$AttendanceProviderArgCopyWith<_AttendanceProviderArg> get copyWith =>
      throw _privateConstructorUsedError;
}
