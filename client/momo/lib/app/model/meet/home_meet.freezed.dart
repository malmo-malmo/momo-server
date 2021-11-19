// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'home_meet.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

HomeMeet _$HomeMeetFromJson(Map<String, dynamic> json) {
  return _HomeMeet.fromJson(json);
}

/// @nodoc
class _$HomeMeetTearOff {
  const _$HomeMeetTearOff();

  _HomeMeet call(
      {required String title,
      required String onOff,
      required int headNum,
      required String startDay}) {
    return _HomeMeet(
      title: title,
      onOff: onOff,
      headNum: headNum,
      startDay: startDay,
    );
  }

  HomeMeet fromJson(Map<String, Object?> json) {
    return HomeMeet.fromJson(json);
  }
}

/// @nodoc
const $HomeMeet = _$HomeMeetTearOff();

/// @nodoc
mixin _$HomeMeet {
  String get title => throw _privateConstructorUsedError;
  String get onOff => throw _privateConstructorUsedError;
  int get headNum => throw _privateConstructorUsedError;
  String get startDay => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $HomeMeetCopyWith<HomeMeet> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $HomeMeetCopyWith<$Res> {
  factory $HomeMeetCopyWith(HomeMeet value, $Res Function(HomeMeet) then) =
      _$HomeMeetCopyWithImpl<$Res>;
  $Res call({String title, String onOff, int headNum, String startDay});
}

/// @nodoc
class _$HomeMeetCopyWithImpl<$Res> implements $HomeMeetCopyWith<$Res> {
  _$HomeMeetCopyWithImpl(this._value, this._then);

  final HomeMeet _value;
  // ignore: unused_field
  final $Res Function(HomeMeet) _then;

  @override
  $Res call({
    Object? title = freezed,
    Object? onOff = freezed,
    Object? headNum = freezed,
    Object? startDay = freezed,
  }) {
    return _then(_value.copyWith(
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      onOff: onOff == freezed
          ? _value.onOff
          : onOff // ignore: cast_nullable_to_non_nullable
              as String,
      headNum: headNum == freezed
          ? _value.headNum
          : headNum // ignore: cast_nullable_to_non_nullable
              as int,
      startDay: startDay == freezed
          ? _value.startDay
          : startDay // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
abstract class _$HomeMeetCopyWith<$Res> implements $HomeMeetCopyWith<$Res> {
  factory _$HomeMeetCopyWith(_HomeMeet value, $Res Function(_HomeMeet) then) =
      __$HomeMeetCopyWithImpl<$Res>;
  @override
  $Res call({String title, String onOff, int headNum, String startDay});
}

/// @nodoc
class __$HomeMeetCopyWithImpl<$Res> extends _$HomeMeetCopyWithImpl<$Res>
    implements _$HomeMeetCopyWith<$Res> {
  __$HomeMeetCopyWithImpl(_HomeMeet _value, $Res Function(_HomeMeet) _then)
      : super(_value, (v) => _then(v as _HomeMeet));

  @override
  _HomeMeet get _value => super._value as _HomeMeet;

  @override
  $Res call({
    Object? title = freezed,
    Object? onOff = freezed,
    Object? headNum = freezed,
    Object? startDay = freezed,
  }) {
    return _then(_HomeMeet(
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      onOff: onOff == freezed
          ? _value.onOff
          : onOff // ignore: cast_nullable_to_non_nullable
              as String,
      headNum: headNum == freezed
          ? _value.headNum
          : headNum // ignore: cast_nullable_to_non_nullable
              as int,
      startDay: startDay == freezed
          ? _value.startDay
          : startDay // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_HomeMeet implements _HomeMeet {
  _$_HomeMeet(
      {required this.title,
      required this.onOff,
      required this.headNum,
      required this.startDay});

  factory _$_HomeMeet.fromJson(Map<String, dynamic> json) =>
      _$$_HomeMeetFromJson(json);

  @override
  final String title;
  @override
  final String onOff;
  @override
  final int headNum;
  @override
  final String startDay;

  @override
  String toString() {
    return 'HomeMeet(title: $title, onOff: $onOff, headNum: $headNum, startDay: $startDay)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _HomeMeet &&
            (identical(other.title, title) || other.title == title) &&
            (identical(other.onOff, onOff) || other.onOff == onOff) &&
            (identical(other.headNum, headNum) || other.headNum == headNum) &&
            (identical(other.startDay, startDay) ||
                other.startDay == startDay));
  }

  @override
  int get hashCode => Object.hash(runtimeType, title, onOff, headNum, startDay);

  @JsonKey(ignore: true)
  @override
  _$HomeMeetCopyWith<_HomeMeet> get copyWith =>
      __$HomeMeetCopyWithImpl<_HomeMeet>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_HomeMeetToJson(this);
  }
}

abstract class _HomeMeet implements HomeMeet {
  factory _HomeMeet(
      {required String title,
      required String onOff,
      required int headNum,
      required String startDay}) = _$_HomeMeet;

  factory _HomeMeet.fromJson(Map<String, dynamic> json) = _$_HomeMeet.fromJson;

  @override
  String get title;
  @override
  String get onOff;
  @override
  int get headNum;
  @override
  String get startDay;
  @override
  @JsonKey(ignore: true)
  _$HomeMeetCopyWith<_HomeMeet> get copyWith =>
      throw _privateConstructorUsedError;
}
