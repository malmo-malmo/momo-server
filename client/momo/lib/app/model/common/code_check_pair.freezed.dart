// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'code_check_pair.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$CodeCheckPairTearOff {
  const _$CodeCheckPairTearOff();

  _CodeCheckPair call({required String code, required bool check}) {
    return _CodeCheckPair(
      code: code,
      check: check,
    );
  }
}

/// @nodoc
const $CodeCheckPair = _$CodeCheckPairTearOff();

/// @nodoc
mixin _$CodeCheckPair {
  String get code => throw _privateConstructorUsedError;
  bool get check => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $CodeCheckPairCopyWith<CodeCheckPair> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $CodeCheckPairCopyWith<$Res> {
  factory $CodeCheckPairCopyWith(
          CodeCheckPair value, $Res Function(CodeCheckPair) then) =
      _$CodeCheckPairCopyWithImpl<$Res>;
  $Res call({String code, bool check});
}

/// @nodoc
class _$CodeCheckPairCopyWithImpl<$Res>
    implements $CodeCheckPairCopyWith<$Res> {
  _$CodeCheckPairCopyWithImpl(this._value, this._then);

  final CodeCheckPair _value;
  // ignore: unused_field
  final $Res Function(CodeCheckPair) _then;

  @override
  $Res call({
    Object? code = freezed,
    Object? check = freezed,
  }) {
    return _then(_value.copyWith(
      code: code == freezed
          ? _value.code
          : code // ignore: cast_nullable_to_non_nullable
              as String,
      check: check == freezed
          ? _value.check
          : check // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc
abstract class _$CodeCheckPairCopyWith<$Res>
    implements $CodeCheckPairCopyWith<$Res> {
  factory _$CodeCheckPairCopyWith(
          _CodeCheckPair value, $Res Function(_CodeCheckPair) then) =
      __$CodeCheckPairCopyWithImpl<$Res>;
  @override
  $Res call({String code, bool check});
}

/// @nodoc
class __$CodeCheckPairCopyWithImpl<$Res>
    extends _$CodeCheckPairCopyWithImpl<$Res>
    implements _$CodeCheckPairCopyWith<$Res> {
  __$CodeCheckPairCopyWithImpl(
      _CodeCheckPair _value, $Res Function(_CodeCheckPair) _then)
      : super(_value, (v) => _then(v as _CodeCheckPair));

  @override
  _CodeCheckPair get _value => super._value as _CodeCheckPair;

  @override
  $Res call({
    Object? code = freezed,
    Object? check = freezed,
  }) {
    return _then(_CodeCheckPair(
      code: code == freezed
          ? _value.code
          : code // ignore: cast_nullable_to_non_nullable
              as String,
      check: check == freezed
          ? _value.check
          : check // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc

class _$_CodeCheckPair implements _CodeCheckPair {
  _$_CodeCheckPair({required this.code, required this.check});

  @override
  final String code;
  @override
  final bool check;

  @override
  String toString() {
    return 'CodeCheckPair(code: $code, check: $check)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _CodeCheckPair &&
            (identical(other.code, code) || other.code == code) &&
            (identical(other.check, check) || other.check == check));
  }

  @override
  int get hashCode => Object.hash(runtimeType, code, check);

  @JsonKey(ignore: true)
  @override
  _$CodeCheckPairCopyWith<_CodeCheckPair> get copyWith =>
      __$CodeCheckPairCopyWithImpl<_CodeCheckPair>(this, _$identity);
}

abstract class _CodeCheckPair implements CodeCheckPair {
  factory _CodeCheckPair({required String code, required bool check}) =
      _$_CodeCheckPair;

  @override
  String get code;
  @override
  bool get check;
  @override
  @JsonKey(ignore: true)
  _$CodeCheckPairCopyWith<_CodeCheckPair> get copyWith =>
      throw _privateConstructorUsedError;
}
