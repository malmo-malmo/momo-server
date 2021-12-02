// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'code_name_pair.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

CodeNamePair _$CodeNamePairFromJson(Map<String, dynamic> json) {
  return _CodeNamePair.fromJson(json);
}

/// @nodoc
class _$CodeNamePairTearOff {
  const _$CodeNamePairTearOff();

  _CodeNamePair call({required String code, required String name}) {
    return _CodeNamePair(
      code: code,
      name: name,
    );
  }

  CodeNamePair fromJson(Map<String, Object?> json) {
    return CodeNamePair.fromJson(json);
  }
}

/// @nodoc
const $CodeNamePair = _$CodeNamePairTearOff();

/// @nodoc
mixin _$CodeNamePair {
  String get code => throw _privateConstructorUsedError;
  String get name => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $CodeNamePairCopyWith<CodeNamePair> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $CodeNamePairCopyWith<$Res> {
  factory $CodeNamePairCopyWith(
          CodeNamePair value, $Res Function(CodeNamePair) then) =
      _$CodeNamePairCopyWithImpl<$Res>;
  $Res call({String code, String name});
}

/// @nodoc
class _$CodeNamePairCopyWithImpl<$Res> implements $CodeNamePairCopyWith<$Res> {
  _$CodeNamePairCopyWithImpl(this._value, this._then);

  final CodeNamePair _value;
  // ignore: unused_field
  final $Res Function(CodeNamePair) _then;

  @override
  $Res call({
    Object? code = freezed,
    Object? name = freezed,
  }) {
    return _then(_value.copyWith(
      code: code == freezed
          ? _value.code
          : code // ignore: cast_nullable_to_non_nullable
              as String,
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
abstract class _$CodeNamePairCopyWith<$Res>
    implements $CodeNamePairCopyWith<$Res> {
  factory _$CodeNamePairCopyWith(
          _CodeNamePair value, $Res Function(_CodeNamePair) then) =
      __$CodeNamePairCopyWithImpl<$Res>;
  @override
  $Res call({String code, String name});
}

/// @nodoc
class __$CodeNamePairCopyWithImpl<$Res> extends _$CodeNamePairCopyWithImpl<$Res>
    implements _$CodeNamePairCopyWith<$Res> {
  __$CodeNamePairCopyWithImpl(
      _CodeNamePair _value, $Res Function(_CodeNamePair) _then)
      : super(_value, (v) => _then(v as _CodeNamePair));

  @override
  _CodeNamePair get _value => super._value as _CodeNamePair;

  @override
  $Res call({
    Object? code = freezed,
    Object? name = freezed,
  }) {
    return _then(_CodeNamePair(
      code: code == freezed
          ? _value.code
          : code // ignore: cast_nullable_to_non_nullable
              as String,
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_CodeNamePair implements _CodeNamePair {
  _$_CodeNamePair({required this.code, required this.name});

  factory _$_CodeNamePair.fromJson(Map<String, dynamic> json) =>
      _$$_CodeNamePairFromJson(json);

  @override
  final String code;
  @override
  final String name;

  @override
  String toString() {
    return 'CodeNamePair(code: $code, name: $name)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _CodeNamePair &&
            (identical(other.code, code) || other.code == code) &&
            (identical(other.name, name) || other.name == name));
  }

  @override
  int get hashCode => Object.hash(runtimeType, code, name);

  @JsonKey(ignore: true)
  @override
  _$CodeNamePairCopyWith<_CodeNamePair> get copyWith =>
      __$CodeNamePairCopyWithImpl<_CodeNamePair>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_CodeNamePairToJson(this);
  }
}

abstract class _CodeNamePair implements CodeNamePair {
  factory _CodeNamePair({required String code, required String name}) =
      _$_CodeNamePair;

  factory _CodeNamePair.fromJson(Map<String, dynamic> json) =
      _$_CodeNamePair.fromJson;

  @override
  String get code;
  @override
  String get name;
  @override
  @JsonKey(ignore: true)
  _$CodeNamePairCopyWith<_CodeNamePair> get copyWith =>
      throw _privateConstructorUsedError;
}
