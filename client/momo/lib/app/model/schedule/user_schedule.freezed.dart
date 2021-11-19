// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'user_schedule.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

UserSchedule _$UserScheduleFromJson(Map<String, dynamic> json) {
  return _UserSchedule.fromJson(json);
}

/// @nodoc
class _$UserScheduleTearOff {
  const _$UserScheduleTearOff();

  _UserSchedule call(
      {required int id,
      required String name,
      required String profile,
      required String title,
      required String contents,
      required bool attendance}) {
    return _UserSchedule(
      id: id,
      name: name,
      profile: profile,
      title: title,
      contents: contents,
      attendance: attendance,
    );
  }

  UserSchedule fromJson(Map<String, Object?> json) {
    return UserSchedule.fromJson(json);
  }
}

/// @nodoc
const $UserSchedule = _$UserScheduleTearOff();

/// @nodoc
mixin _$UserSchedule {
  int get id => throw _privateConstructorUsedError;
  String get name => throw _privateConstructorUsedError;
  String get profile => throw _privateConstructorUsedError;
  String get title => throw _privateConstructorUsedError;
  String get contents => throw _privateConstructorUsedError;
  bool get attendance => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $UserScheduleCopyWith<UserSchedule> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $UserScheduleCopyWith<$Res> {
  factory $UserScheduleCopyWith(
          UserSchedule value, $Res Function(UserSchedule) then) =
      _$UserScheduleCopyWithImpl<$Res>;
  $Res call(
      {int id,
      String name,
      String profile,
      String title,
      String contents,
      bool attendance});
}

/// @nodoc
class _$UserScheduleCopyWithImpl<$Res> implements $UserScheduleCopyWith<$Res> {
  _$UserScheduleCopyWithImpl(this._value, this._then);

  final UserSchedule _value;
  // ignore: unused_field
  final $Res Function(UserSchedule) _then;

  @override
  $Res call({
    Object? id = freezed,
    Object? name = freezed,
    Object? profile = freezed,
    Object? title = freezed,
    Object? contents = freezed,
    Object? attendance = freezed,
  }) {
    return _then(_value.copyWith(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      profile: profile == freezed
          ? _value.profile
          : profile // ignore: cast_nullable_to_non_nullable
              as String,
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
              as String,
      attendance: attendance == freezed
          ? _value.attendance
          : attendance // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc
abstract class _$UserScheduleCopyWith<$Res>
    implements $UserScheduleCopyWith<$Res> {
  factory _$UserScheduleCopyWith(
          _UserSchedule value, $Res Function(_UserSchedule) then) =
      __$UserScheduleCopyWithImpl<$Res>;
  @override
  $Res call(
      {int id,
      String name,
      String profile,
      String title,
      String contents,
      bool attendance});
}

/// @nodoc
class __$UserScheduleCopyWithImpl<$Res> extends _$UserScheduleCopyWithImpl<$Res>
    implements _$UserScheduleCopyWith<$Res> {
  __$UserScheduleCopyWithImpl(
      _UserSchedule _value, $Res Function(_UserSchedule) _then)
      : super(_value, (v) => _then(v as _UserSchedule));

  @override
  _UserSchedule get _value => super._value as _UserSchedule;

  @override
  $Res call({
    Object? id = freezed,
    Object? name = freezed,
    Object? profile = freezed,
    Object? title = freezed,
    Object? contents = freezed,
    Object? attendance = freezed,
  }) {
    return _then(_UserSchedule(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      profile: profile == freezed
          ? _value.profile
          : profile // ignore: cast_nullable_to_non_nullable
              as String,
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
              as String,
      attendance: attendance == freezed
          ? _value.attendance
          : attendance // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_UserSchedule implements _UserSchedule {
  _$_UserSchedule(
      {required this.id,
      required this.name,
      required this.profile,
      required this.title,
      required this.contents,
      required this.attendance});

  factory _$_UserSchedule.fromJson(Map<String, dynamic> json) =>
      _$$_UserScheduleFromJson(json);

  @override
  final int id;
  @override
  final String name;
  @override
  final String profile;
  @override
  final String title;
  @override
  final String contents;
  @override
  final bool attendance;

  @override
  String toString() {
    return 'UserSchedule(id: $id, name: $name, profile: $profile, title: $title, contents: $contents, attendance: $attendance)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _UserSchedule &&
            (identical(other.id, id) || other.id == id) &&
            (identical(other.name, name) || other.name == name) &&
            (identical(other.profile, profile) || other.profile == profile) &&
            (identical(other.title, title) || other.title == title) &&
            (identical(other.contents, contents) ||
                other.contents == contents) &&
            (identical(other.attendance, attendance) ||
                other.attendance == attendance));
  }

  @override
  int get hashCode =>
      Object.hash(runtimeType, id, name, profile, title, contents, attendance);

  @JsonKey(ignore: true)
  @override
  _$UserScheduleCopyWith<_UserSchedule> get copyWith =>
      __$UserScheduleCopyWithImpl<_UserSchedule>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_UserScheduleToJson(this);
  }
}

abstract class _UserSchedule implements UserSchedule {
  factory _UserSchedule(
      {required int id,
      required String name,
      required String profile,
      required String title,
      required String contents,
      required bool attendance}) = _$_UserSchedule;

  factory _UserSchedule.fromJson(Map<String, dynamic> json) =
      _$_UserSchedule.fromJson;

  @override
  int get id;
  @override
  String get name;
  @override
  String get profile;
  @override
  String get title;
  @override
  String get contents;
  @override
  bool get attendance;
  @override
  @JsonKey(ignore: true)
  _$UserScheduleCopyWith<_UserSchedule> get copyWith =>
      throw _privateConstructorUsedError;
}
