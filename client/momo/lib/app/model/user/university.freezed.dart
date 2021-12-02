// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'university.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

University _$UniversityFromJson(Map<String, dynamic> json) {
  return _University.fromJson(json);
}

/// @nodoc
class _$UniversityTearOff {
  const _$UniversityTearOff();

  _University call({required String name}) {
    return _University(
      name: name,
    );
  }

  University fromJson(Map<String, Object?> json) {
    return University.fromJson(json);
  }
}

/// @nodoc
const $University = _$UniversityTearOff();

/// @nodoc
mixin _$University {
  String get name => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $UniversityCopyWith<University> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $UniversityCopyWith<$Res> {
  factory $UniversityCopyWith(
          University value, $Res Function(University) then) =
      _$UniversityCopyWithImpl<$Res>;
  $Res call({String name});
}

/// @nodoc
class _$UniversityCopyWithImpl<$Res> implements $UniversityCopyWith<$Res> {
  _$UniversityCopyWithImpl(this._value, this._then);

  final University _value;
  // ignore: unused_field
  final $Res Function(University) _then;

  @override
  $Res call({
    Object? name = freezed,
  }) {
    return _then(_value.copyWith(
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
abstract class _$UniversityCopyWith<$Res> implements $UniversityCopyWith<$Res> {
  factory _$UniversityCopyWith(
          _University value, $Res Function(_University) then) =
      __$UniversityCopyWithImpl<$Res>;
  @override
  $Res call({String name});
}

/// @nodoc
class __$UniversityCopyWithImpl<$Res> extends _$UniversityCopyWithImpl<$Res>
    implements _$UniversityCopyWith<$Res> {
  __$UniversityCopyWithImpl(
      _University _value, $Res Function(_University) _then)
      : super(_value, (v) => _then(v as _University));

  @override
  _University get _value => super._value as _University;

  @override
  $Res call({
    Object? name = freezed,
  }) {
    return _then(_University(
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_University implements _University {
  _$_University({required this.name});

  factory _$_University.fromJson(Map<String, dynamic> json) =>
      _$$_UniversityFromJson(json);

  @override
  final String name;

  @override
  String toString() {
    return 'University(name: $name)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _University &&
            (identical(other.name, name) || other.name == name));
  }

  @override
  int get hashCode => Object.hash(runtimeType, name);

  @JsonKey(ignore: true)
  @override
  _$UniversityCopyWith<_University> get copyWith =>
      __$UniversityCopyWithImpl<_University>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_UniversityToJson(this);
  }
}

abstract class _University implements University {
  factory _University({required String name}) = _$_University;

  factory _University.fromJson(Map<String, dynamic> json) =
      _$_University.fromJson;

  @override
  String get name;
  @override
  @JsonKey(ignore: true)
  _$UniversityCopyWith<_University> get copyWith =>
      throw _privateConstructorUsedError;
}
