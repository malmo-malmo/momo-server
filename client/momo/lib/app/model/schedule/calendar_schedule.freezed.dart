// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'calendar_schedule.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

CalendarSchedule _$CalendarScheduleFromJson(Map<String, dynamic> json) {
  return _CalendarSchedule.fromJson(json);
}

/// @nodoc
class _$CalendarScheduleTearOff {
  const _$CalendarScheduleTearOff();

  _CalendarSchedule call(
      {required int groupId,
      required String startDateTime,
      required String title,
      @JsonKey(name: 'groupCategory') required CodeNamePair category}) {
    return _CalendarSchedule(
      groupId: groupId,
      startDateTime: startDateTime,
      title: title,
      category: category,
    );
  }

  CalendarSchedule fromJson(Map<String, Object?> json) {
    return CalendarSchedule.fromJson(json);
  }
}

/// @nodoc
const $CalendarSchedule = _$CalendarScheduleTearOff();

/// @nodoc
mixin _$CalendarSchedule {
  int get groupId => throw _privateConstructorUsedError;
  String get startDateTime => throw _privateConstructorUsedError;
  String get title => throw _privateConstructorUsedError;
  @JsonKey(name: 'groupCategory')
  CodeNamePair get category => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $CalendarScheduleCopyWith<CalendarSchedule> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $CalendarScheduleCopyWith<$Res> {
  factory $CalendarScheduleCopyWith(
          CalendarSchedule value, $Res Function(CalendarSchedule) then) =
      _$CalendarScheduleCopyWithImpl<$Res>;
  $Res call(
      {int groupId,
      String startDateTime,
      String title,
      @JsonKey(name: 'groupCategory') CodeNamePair category});

  $CodeNamePairCopyWith<$Res> get category;
}

/// @nodoc
class _$CalendarScheduleCopyWithImpl<$Res>
    implements $CalendarScheduleCopyWith<$Res> {
  _$CalendarScheduleCopyWithImpl(this._value, this._then);

  final CalendarSchedule _value;
  // ignore: unused_field
  final $Res Function(CalendarSchedule) _then;

  @override
  $Res call({
    Object? groupId = freezed,
    Object? startDateTime = freezed,
    Object? title = freezed,
    Object? category = freezed,
  }) {
    return _then(_value.copyWith(
      groupId: groupId == freezed
          ? _value.groupId
          : groupId // ignore: cast_nullable_to_non_nullable
              as int,
      startDateTime: startDateTime == freezed
          ? _value.startDateTime
          : startDateTime // ignore: cast_nullable_to_non_nullable
              as String,
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      category: category == freezed
          ? _value.category
          : category // ignore: cast_nullable_to_non_nullable
              as CodeNamePair,
    ));
  }

  @override
  $CodeNamePairCopyWith<$Res> get category {
    return $CodeNamePairCopyWith<$Res>(_value.category, (value) {
      return _then(_value.copyWith(category: value));
    });
  }
}

/// @nodoc
abstract class _$CalendarScheduleCopyWith<$Res>
    implements $CalendarScheduleCopyWith<$Res> {
  factory _$CalendarScheduleCopyWith(
          _CalendarSchedule value, $Res Function(_CalendarSchedule) then) =
      __$CalendarScheduleCopyWithImpl<$Res>;
  @override
  $Res call(
      {int groupId,
      String startDateTime,
      String title,
      @JsonKey(name: 'groupCategory') CodeNamePair category});

  @override
  $CodeNamePairCopyWith<$Res> get category;
}

/// @nodoc
class __$CalendarScheduleCopyWithImpl<$Res>
    extends _$CalendarScheduleCopyWithImpl<$Res>
    implements _$CalendarScheduleCopyWith<$Res> {
  __$CalendarScheduleCopyWithImpl(
      _CalendarSchedule _value, $Res Function(_CalendarSchedule) _then)
      : super(_value, (v) => _then(v as _CalendarSchedule));

  @override
  _CalendarSchedule get _value => super._value as _CalendarSchedule;

  @override
  $Res call({
    Object? groupId = freezed,
    Object? startDateTime = freezed,
    Object? title = freezed,
    Object? category = freezed,
  }) {
    return _then(_CalendarSchedule(
      groupId: groupId == freezed
          ? _value.groupId
          : groupId // ignore: cast_nullable_to_non_nullable
              as int,
      startDateTime: startDateTime == freezed
          ? _value.startDateTime
          : startDateTime // ignore: cast_nullable_to_non_nullable
              as String,
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      category: category == freezed
          ? _value.category
          : category // ignore: cast_nullable_to_non_nullable
              as CodeNamePair,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_CalendarSchedule implements _CalendarSchedule {
  _$_CalendarSchedule(
      {required this.groupId,
      required this.startDateTime,
      required this.title,
      @JsonKey(name: 'groupCategory') required this.category});

  factory _$_CalendarSchedule.fromJson(Map<String, dynamic> json) =>
      _$$_CalendarScheduleFromJson(json);

  @override
  final int groupId;
  @override
  final String startDateTime;
  @override
  final String title;
  @override
  @JsonKey(name: 'groupCategory')
  final CodeNamePair category;

  @override
  String toString() {
    return 'CalendarSchedule(groupId: $groupId, startDateTime: $startDateTime, title: $title, category: $category)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _CalendarSchedule &&
            (identical(other.groupId, groupId) || other.groupId == groupId) &&
            (identical(other.startDateTime, startDateTime) ||
                other.startDateTime == startDateTime) &&
            (identical(other.title, title) || other.title == title) &&
            (identical(other.category, category) ||
                other.category == category));
  }

  @override
  int get hashCode =>
      Object.hash(runtimeType, groupId, startDateTime, title, category);

  @JsonKey(ignore: true)
  @override
  _$CalendarScheduleCopyWith<_CalendarSchedule> get copyWith =>
      __$CalendarScheduleCopyWithImpl<_CalendarSchedule>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_CalendarScheduleToJson(this);
  }
}

abstract class _CalendarSchedule implements CalendarSchedule {
  factory _CalendarSchedule(
          {required int groupId,
          required String startDateTime,
          required String title,
          @JsonKey(name: 'groupCategory') required CodeNamePair category}) =
      _$_CalendarSchedule;

  factory _CalendarSchedule.fromJson(Map<String, dynamic> json) =
      _$_CalendarSchedule.fromJson;

  @override
  int get groupId;
  @override
  String get startDateTime;
  @override
  String get title;
  @override
  @JsonKey(name: 'groupCategory')
  CodeNamePair get category;
  @override
  @JsonKey(ignore: true)
  _$CalendarScheduleCopyWith<_CalendarSchedule> get copyWith =>
      throw _privateConstructorUsedError;
}
