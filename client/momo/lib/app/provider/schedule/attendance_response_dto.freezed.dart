// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'attendance_response_dto.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$AttendanceResponseDtoTearOff {
  const _$AttendanceResponseDtoTearOff();

  _AttendanceResponseDto call(
      {required List<AttendanceResponse> attendances,
      required bool isLoading}) {
    return _AttendanceResponseDto(
      attendances: attendances,
      isLoading: isLoading,
    );
  }
}

/// @nodoc
const $AttendanceResponseDto = _$AttendanceResponseDtoTearOff();

/// @nodoc
mixin _$AttendanceResponseDto {
  List<AttendanceResponse> get attendances =>
      throw _privateConstructorUsedError;
  bool get isLoading => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $AttendanceResponseDtoCopyWith<AttendanceResponseDto> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $AttendanceResponseDtoCopyWith<$Res> {
  factory $AttendanceResponseDtoCopyWith(AttendanceResponseDto value,
          $Res Function(AttendanceResponseDto) then) =
      _$AttendanceResponseDtoCopyWithImpl<$Res>;
  $Res call({List<AttendanceResponse> attendances, bool isLoading});
}

/// @nodoc
class _$AttendanceResponseDtoCopyWithImpl<$Res>
    implements $AttendanceResponseDtoCopyWith<$Res> {
  _$AttendanceResponseDtoCopyWithImpl(this._value, this._then);

  final AttendanceResponseDto _value;
  // ignore: unused_field
  final $Res Function(AttendanceResponseDto) _then;

  @override
  $Res call({
    Object? attendances = freezed,
    Object? isLoading = freezed,
  }) {
    return _then(_value.copyWith(
      attendances: attendances == freezed
          ? _value.attendances
          : attendances // ignore: cast_nullable_to_non_nullable
              as List<AttendanceResponse>,
      isLoading: isLoading == freezed
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc
abstract class _$AttendanceResponseDtoCopyWith<$Res>
    implements $AttendanceResponseDtoCopyWith<$Res> {
  factory _$AttendanceResponseDtoCopyWith(_AttendanceResponseDto value,
          $Res Function(_AttendanceResponseDto) then) =
      __$AttendanceResponseDtoCopyWithImpl<$Res>;
  @override
  $Res call({List<AttendanceResponse> attendances, bool isLoading});
}

/// @nodoc
class __$AttendanceResponseDtoCopyWithImpl<$Res>
    extends _$AttendanceResponseDtoCopyWithImpl<$Res>
    implements _$AttendanceResponseDtoCopyWith<$Res> {
  __$AttendanceResponseDtoCopyWithImpl(_AttendanceResponseDto _value,
      $Res Function(_AttendanceResponseDto) _then)
      : super(_value, (v) => _then(v as _AttendanceResponseDto));

  @override
  _AttendanceResponseDto get _value => super._value as _AttendanceResponseDto;

  @override
  $Res call({
    Object? attendances = freezed,
    Object? isLoading = freezed,
  }) {
    return _then(_AttendanceResponseDto(
      attendances: attendances == freezed
          ? _value.attendances
          : attendances // ignore: cast_nullable_to_non_nullable
              as List<AttendanceResponse>,
      isLoading: isLoading == freezed
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc

class _$_AttendanceResponseDto implements _AttendanceResponseDto {
  _$_AttendanceResponseDto(
      {required this.attendances, required this.isLoading});

  @override
  final List<AttendanceResponse> attendances;
  @override
  final bool isLoading;

  @override
  String toString() {
    return 'AttendanceResponseDto(attendances: $attendances, isLoading: $isLoading)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _AttendanceResponseDto &&
            const DeepCollectionEquality()
                .equals(other.attendances, attendances) &&
            (identical(other.isLoading, isLoading) ||
                other.isLoading == isLoading));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType, const DeepCollectionEquality().hash(attendances), isLoading);

  @JsonKey(ignore: true)
  @override
  _$AttendanceResponseDtoCopyWith<_AttendanceResponseDto> get copyWith =>
      __$AttendanceResponseDtoCopyWithImpl<_AttendanceResponseDto>(
          this, _$identity);
}

abstract class _AttendanceResponseDto implements AttendanceResponseDto {
  factory _AttendanceResponseDto(
      {required List<AttendanceResponse> attendances,
      required bool isLoading}) = _$_AttendanceResponseDto;

  @override
  List<AttendanceResponse> get attendances;
  @override
  bool get isLoading;
  @override
  @JsonKey(ignore: true)
  _$AttendanceResponseDtoCopyWith<_AttendanceResponseDto> get copyWith =>
      throw _privateConstructorUsedError;
}
