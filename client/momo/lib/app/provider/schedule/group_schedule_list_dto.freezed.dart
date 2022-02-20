// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'group_schedule_list_dto.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$GroupScheduleListDtoTearOff {
  const _$GroupScheduleListDtoTearOff();

  _GroupScheduleListDto call(
      {required List<ScheduleDetail> schedules,
      required int managerId,
      required int nextPage,
      required bool hasNext,
      String? error}) {
    return _GroupScheduleListDto(
      schedules: schedules,
      managerId: managerId,
      nextPage: nextPage,
      hasNext: hasNext,
      error: error,
    );
  }
}

/// @nodoc
const $GroupScheduleListDto = _$GroupScheduleListDtoTearOff();

/// @nodoc
mixin _$GroupScheduleListDto {
  List<ScheduleDetail> get schedules => throw _privateConstructorUsedError;
  int get managerId => throw _privateConstructorUsedError;
  int get nextPage => throw _privateConstructorUsedError;
  bool get hasNext => throw _privateConstructorUsedError;
  String? get error => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $GroupScheduleListDtoCopyWith<GroupScheduleListDto> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $GroupScheduleListDtoCopyWith<$Res> {
  factory $GroupScheduleListDtoCopyWith(GroupScheduleListDto value,
          $Res Function(GroupScheduleListDto) then) =
      _$GroupScheduleListDtoCopyWithImpl<$Res>;
  $Res call(
      {List<ScheduleDetail> schedules,
      int managerId,
      int nextPage,
      bool hasNext,
      String? error});
}

/// @nodoc
class _$GroupScheduleListDtoCopyWithImpl<$Res>
    implements $GroupScheduleListDtoCopyWith<$Res> {
  _$GroupScheduleListDtoCopyWithImpl(this._value, this._then);

  final GroupScheduleListDto _value;
  // ignore: unused_field
  final $Res Function(GroupScheduleListDto) _then;

  @override
  $Res call({
    Object? schedules = freezed,
    Object? managerId = freezed,
    Object? nextPage = freezed,
    Object? hasNext = freezed,
    Object? error = freezed,
  }) {
    return _then(_value.copyWith(
      schedules: schedules == freezed
          ? _value.schedules
          : schedules // ignore: cast_nullable_to_non_nullable
              as List<ScheduleDetail>,
      managerId: managerId == freezed
          ? _value.managerId
          : managerId // ignore: cast_nullable_to_non_nullable
              as int,
      nextPage: nextPage == freezed
          ? _value.nextPage
          : nextPage // ignore: cast_nullable_to_non_nullable
              as int,
      hasNext: hasNext == freezed
          ? _value.hasNext
          : hasNext // ignore: cast_nullable_to_non_nullable
              as bool,
      error: error == freezed
          ? _value.error
          : error // ignore: cast_nullable_to_non_nullable
              as String?,
    ));
  }
}

/// @nodoc
abstract class _$GroupScheduleListDtoCopyWith<$Res>
    implements $GroupScheduleListDtoCopyWith<$Res> {
  factory _$GroupScheduleListDtoCopyWith(_GroupScheduleListDto value,
          $Res Function(_GroupScheduleListDto) then) =
      __$GroupScheduleListDtoCopyWithImpl<$Res>;
  @override
  $Res call(
      {List<ScheduleDetail> schedules,
      int managerId,
      int nextPage,
      bool hasNext,
      String? error});
}

/// @nodoc
class __$GroupScheduleListDtoCopyWithImpl<$Res>
    extends _$GroupScheduleListDtoCopyWithImpl<$Res>
    implements _$GroupScheduleListDtoCopyWith<$Res> {
  __$GroupScheduleListDtoCopyWithImpl(
      _GroupScheduleListDto _value, $Res Function(_GroupScheduleListDto) _then)
      : super(_value, (v) => _then(v as _GroupScheduleListDto));

  @override
  _GroupScheduleListDto get _value => super._value as _GroupScheduleListDto;

  @override
  $Res call({
    Object? schedules = freezed,
    Object? managerId = freezed,
    Object? nextPage = freezed,
    Object? hasNext = freezed,
    Object? error = freezed,
  }) {
    return _then(_GroupScheduleListDto(
      schedules: schedules == freezed
          ? _value.schedules
          : schedules // ignore: cast_nullable_to_non_nullable
              as List<ScheduleDetail>,
      managerId: managerId == freezed
          ? _value.managerId
          : managerId // ignore: cast_nullable_to_non_nullable
              as int,
      nextPage: nextPage == freezed
          ? _value.nextPage
          : nextPage // ignore: cast_nullable_to_non_nullable
              as int,
      hasNext: hasNext == freezed
          ? _value.hasNext
          : hasNext // ignore: cast_nullable_to_non_nullable
              as bool,
      error: error == freezed
          ? _value.error
          : error // ignore: cast_nullable_to_non_nullable
              as String?,
    ));
  }
}

/// @nodoc

class _$_GroupScheduleListDto implements _GroupScheduleListDto {
  _$_GroupScheduleListDto(
      {required this.schedules,
      required this.managerId,
      required this.nextPage,
      required this.hasNext,
      this.error});

  @override
  final List<ScheduleDetail> schedules;
  @override
  final int managerId;
  @override
  final int nextPage;
  @override
  final bool hasNext;
  @override
  final String? error;

  @override
  String toString() {
    return 'GroupScheduleListDto(schedules: $schedules, managerId: $managerId, nextPage: $nextPage, hasNext: $hasNext, error: $error)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _GroupScheduleListDto &&
            const DeepCollectionEquality().equals(other.schedules, schedules) &&
            (identical(other.managerId, managerId) ||
                other.managerId == managerId) &&
            (identical(other.nextPage, nextPage) ||
                other.nextPage == nextPage) &&
            (identical(other.hasNext, hasNext) || other.hasNext == hasNext) &&
            (identical(other.error, error) || other.error == error));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType,
      const DeepCollectionEquality().hash(schedules),
      managerId,
      nextPage,
      hasNext,
      error);

  @JsonKey(ignore: true)
  @override
  _$GroupScheduleListDtoCopyWith<_GroupScheduleListDto> get copyWith =>
      __$GroupScheduleListDtoCopyWithImpl<_GroupScheduleListDto>(
          this, _$identity);
}

abstract class _GroupScheduleListDto implements GroupScheduleListDto {
  factory _GroupScheduleListDto(
      {required List<ScheduleDetail> schedules,
      required int managerId,
      required int nextPage,
      required bool hasNext,
      String? error}) = _$_GroupScheduleListDto;

  @override
  List<ScheduleDetail> get schedules;
  @override
  int get managerId;
  @override
  int get nextPage;
  @override
  bool get hasNext;
  @override
  String? get error;
  @override
  @JsonKey(ignore: true)
  _$GroupScheduleListDtoCopyWith<_GroupScheduleListDto> get copyWith =>
      throw _privateConstructorUsedError;
}
