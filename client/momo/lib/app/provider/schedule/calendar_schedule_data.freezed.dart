// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'calendar_schedule_data.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$CalendarScheduleDataTearOff {
  const _$CalendarScheduleDataTearOff();

  _CalendarScheduleData call(
      {required bool isLoading,
      required DateTime selectedDate,
      required DateTime requestDate,
      required List<DateTime> scheduleDateTimes,
      required List<List<CalendarSchedule>> schedules}) {
    return _CalendarScheduleData(
      isLoading: isLoading,
      selectedDate: selectedDate,
      requestDate: requestDate,
      scheduleDateTimes: scheduleDateTimes,
      schedules: schedules,
    );
  }
}

/// @nodoc
const $CalendarScheduleData = _$CalendarScheduleDataTearOff();

/// @nodoc
mixin _$CalendarScheduleData {
  bool get isLoading => throw _privateConstructorUsedError;
  DateTime get selectedDate => throw _privateConstructorUsedError;
  DateTime get requestDate => throw _privateConstructorUsedError;
  List<DateTime> get scheduleDateTimes => throw _privateConstructorUsedError;
  List<List<CalendarSchedule>> get schedules =>
      throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $CalendarScheduleDataCopyWith<CalendarScheduleData> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $CalendarScheduleDataCopyWith<$Res> {
  factory $CalendarScheduleDataCopyWith(CalendarScheduleData value,
          $Res Function(CalendarScheduleData) then) =
      _$CalendarScheduleDataCopyWithImpl<$Res>;
  $Res call(
      {bool isLoading,
      DateTime selectedDate,
      DateTime requestDate,
      List<DateTime> scheduleDateTimes,
      List<List<CalendarSchedule>> schedules});
}

/// @nodoc
class _$CalendarScheduleDataCopyWithImpl<$Res>
    implements $CalendarScheduleDataCopyWith<$Res> {
  _$CalendarScheduleDataCopyWithImpl(this._value, this._then);

  final CalendarScheduleData _value;
  // ignore: unused_field
  final $Res Function(CalendarScheduleData) _then;

  @override
  $Res call({
    Object? isLoading = freezed,
    Object? selectedDate = freezed,
    Object? requestDate = freezed,
    Object? scheduleDateTimes = freezed,
    Object? schedules = freezed,
  }) {
    return _then(_value.copyWith(
      isLoading: isLoading == freezed
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
      selectedDate: selectedDate == freezed
          ? _value.selectedDate
          : selectedDate // ignore: cast_nullable_to_non_nullable
              as DateTime,
      requestDate: requestDate == freezed
          ? _value.requestDate
          : requestDate // ignore: cast_nullable_to_non_nullable
              as DateTime,
      scheduleDateTimes: scheduleDateTimes == freezed
          ? _value.scheduleDateTimes
          : scheduleDateTimes // ignore: cast_nullable_to_non_nullable
              as List<DateTime>,
      schedules: schedules == freezed
          ? _value.schedules
          : schedules // ignore: cast_nullable_to_non_nullable
              as List<List<CalendarSchedule>>,
    ));
  }
}

/// @nodoc
abstract class _$CalendarScheduleDataCopyWith<$Res>
    implements $CalendarScheduleDataCopyWith<$Res> {
  factory _$CalendarScheduleDataCopyWith(_CalendarScheduleData value,
          $Res Function(_CalendarScheduleData) then) =
      __$CalendarScheduleDataCopyWithImpl<$Res>;
  @override
  $Res call(
      {bool isLoading,
      DateTime selectedDate,
      DateTime requestDate,
      List<DateTime> scheduleDateTimes,
      List<List<CalendarSchedule>> schedules});
}

/// @nodoc
class __$CalendarScheduleDataCopyWithImpl<$Res>
    extends _$CalendarScheduleDataCopyWithImpl<$Res>
    implements _$CalendarScheduleDataCopyWith<$Res> {
  __$CalendarScheduleDataCopyWithImpl(
      _CalendarScheduleData _value, $Res Function(_CalendarScheduleData) _then)
      : super(_value, (v) => _then(v as _CalendarScheduleData));

  @override
  _CalendarScheduleData get _value => super._value as _CalendarScheduleData;

  @override
  $Res call({
    Object? isLoading = freezed,
    Object? selectedDate = freezed,
    Object? requestDate = freezed,
    Object? scheduleDateTimes = freezed,
    Object? schedules = freezed,
  }) {
    return _then(_CalendarScheduleData(
      isLoading: isLoading == freezed
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
      selectedDate: selectedDate == freezed
          ? _value.selectedDate
          : selectedDate // ignore: cast_nullable_to_non_nullable
              as DateTime,
      requestDate: requestDate == freezed
          ? _value.requestDate
          : requestDate // ignore: cast_nullable_to_non_nullable
              as DateTime,
      scheduleDateTimes: scheduleDateTimes == freezed
          ? _value.scheduleDateTimes
          : scheduleDateTimes // ignore: cast_nullable_to_non_nullable
              as List<DateTime>,
      schedules: schedules == freezed
          ? _value.schedules
          : schedules // ignore: cast_nullable_to_non_nullable
              as List<List<CalendarSchedule>>,
    ));
  }
}

/// @nodoc

class _$_CalendarScheduleData implements _CalendarScheduleData {
  _$_CalendarScheduleData(
      {required this.isLoading,
      required this.selectedDate,
      required this.requestDate,
      required this.scheduleDateTimes,
      required this.schedules});

  @override
  final bool isLoading;
  @override
  final DateTime selectedDate;
  @override
  final DateTime requestDate;
  @override
  final List<DateTime> scheduleDateTimes;
  @override
  final List<List<CalendarSchedule>> schedules;

  @override
  String toString() {
    return 'CalendarScheduleData(isLoading: $isLoading, selectedDate: $selectedDate, requestDate: $requestDate, scheduleDateTimes: $scheduleDateTimes, schedules: $schedules)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _CalendarScheduleData &&
            (identical(other.isLoading, isLoading) ||
                other.isLoading == isLoading) &&
            (identical(other.selectedDate, selectedDate) ||
                other.selectedDate == selectedDate) &&
            (identical(other.requestDate, requestDate) ||
                other.requestDate == requestDate) &&
            const DeepCollectionEquality()
                .equals(other.scheduleDateTimes, scheduleDateTimes) &&
            const DeepCollectionEquality().equals(other.schedules, schedules));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType,
      isLoading,
      selectedDate,
      requestDate,
      const DeepCollectionEquality().hash(scheduleDateTimes),
      const DeepCollectionEquality().hash(schedules));

  @JsonKey(ignore: true)
  @override
  _$CalendarScheduleDataCopyWith<_CalendarScheduleData> get copyWith =>
      __$CalendarScheduleDataCopyWithImpl<_CalendarScheduleData>(
          this, _$identity);
}

abstract class _CalendarScheduleData implements CalendarScheduleData {
  factory _CalendarScheduleData(
          {required bool isLoading,
          required DateTime selectedDate,
          required DateTime requestDate,
          required List<DateTime> scheduleDateTimes,
          required List<List<CalendarSchedule>> schedules}) =
      _$_CalendarScheduleData;

  @override
  bool get isLoading;
  @override
  DateTime get selectedDate;
  @override
  DateTime get requestDate;
  @override
  List<DateTime> get scheduleDateTimes;
  @override
  List<List<CalendarSchedule>> get schedules;
  @override
  @JsonKey(ignore: true)
  _$CalendarScheduleDataCopyWith<_CalendarScheduleData> get copyWith =>
      throw _privateConstructorUsedError;
}
