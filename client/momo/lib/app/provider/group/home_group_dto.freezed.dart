// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'home_group_dto.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$HomeGroupDtoTearOff {
  const _$HomeGroupDtoTearOff();

  _HomeGroupDto call(
      {required bool isLoading,
      required List<GroupInfo> categoryGroup,
      required List<GroupInfo> districtGroup,
      required List<GroupInfo> universityGroup}) {
    return _HomeGroupDto(
      isLoading: isLoading,
      categoryGroup: categoryGroup,
      districtGroup: districtGroup,
      universityGroup: universityGroup,
    );
  }
}

/// @nodoc
const $HomeGroupDto = _$HomeGroupDtoTearOff();

/// @nodoc
mixin _$HomeGroupDto {
  bool get isLoading => throw _privateConstructorUsedError;
  List<GroupInfo> get categoryGroup => throw _privateConstructorUsedError;
  List<GroupInfo> get districtGroup => throw _privateConstructorUsedError;
  List<GroupInfo> get universityGroup => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $HomeGroupDtoCopyWith<HomeGroupDto> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $HomeGroupDtoCopyWith<$Res> {
  factory $HomeGroupDtoCopyWith(
          HomeGroupDto value, $Res Function(HomeGroupDto) then) =
      _$HomeGroupDtoCopyWithImpl<$Res>;
  $Res call(
      {bool isLoading,
      List<GroupInfo> categoryGroup,
      List<GroupInfo> districtGroup,
      List<GroupInfo> universityGroup});
}

/// @nodoc
class _$HomeGroupDtoCopyWithImpl<$Res> implements $HomeGroupDtoCopyWith<$Res> {
  _$HomeGroupDtoCopyWithImpl(this._value, this._then);

  final HomeGroupDto _value;
  // ignore: unused_field
  final $Res Function(HomeGroupDto) _then;

  @override
  $Res call({
    Object? isLoading = freezed,
    Object? categoryGroup = freezed,
    Object? districtGroup = freezed,
    Object? universityGroup = freezed,
  }) {
    return _then(_value.copyWith(
      isLoading: isLoading == freezed
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
      categoryGroup: categoryGroup == freezed
          ? _value.categoryGroup
          : categoryGroup // ignore: cast_nullable_to_non_nullable
              as List<GroupInfo>,
      districtGroup: districtGroup == freezed
          ? _value.districtGroup
          : districtGroup // ignore: cast_nullable_to_non_nullable
              as List<GroupInfo>,
      universityGroup: universityGroup == freezed
          ? _value.universityGroup
          : universityGroup // ignore: cast_nullable_to_non_nullable
              as List<GroupInfo>,
    ));
  }
}

/// @nodoc
abstract class _$HomeGroupDtoCopyWith<$Res>
    implements $HomeGroupDtoCopyWith<$Res> {
  factory _$HomeGroupDtoCopyWith(
          _HomeGroupDto value, $Res Function(_HomeGroupDto) then) =
      __$HomeGroupDtoCopyWithImpl<$Res>;
  @override
  $Res call(
      {bool isLoading,
      List<GroupInfo> categoryGroup,
      List<GroupInfo> districtGroup,
      List<GroupInfo> universityGroup});
}

/// @nodoc
class __$HomeGroupDtoCopyWithImpl<$Res> extends _$HomeGroupDtoCopyWithImpl<$Res>
    implements _$HomeGroupDtoCopyWith<$Res> {
  __$HomeGroupDtoCopyWithImpl(
      _HomeGroupDto _value, $Res Function(_HomeGroupDto) _then)
      : super(_value, (v) => _then(v as _HomeGroupDto));

  @override
  _HomeGroupDto get _value => super._value as _HomeGroupDto;

  @override
  $Res call({
    Object? isLoading = freezed,
    Object? categoryGroup = freezed,
    Object? districtGroup = freezed,
    Object? universityGroup = freezed,
  }) {
    return _then(_HomeGroupDto(
      isLoading: isLoading == freezed
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
      categoryGroup: categoryGroup == freezed
          ? _value.categoryGroup
          : categoryGroup // ignore: cast_nullable_to_non_nullable
              as List<GroupInfo>,
      districtGroup: districtGroup == freezed
          ? _value.districtGroup
          : districtGroup // ignore: cast_nullable_to_non_nullable
              as List<GroupInfo>,
      universityGroup: universityGroup == freezed
          ? _value.universityGroup
          : universityGroup // ignore: cast_nullable_to_non_nullable
              as List<GroupInfo>,
    ));
  }
}

/// @nodoc

class _$_HomeGroupDto implements _HomeGroupDto {
  _$_HomeGroupDto(
      {required this.isLoading,
      required this.categoryGroup,
      required this.districtGroup,
      required this.universityGroup});

  @override
  final bool isLoading;
  @override
  final List<GroupInfo> categoryGroup;
  @override
  final List<GroupInfo> districtGroup;
  @override
  final List<GroupInfo> universityGroup;

  @override
  String toString() {
    return 'HomeGroupDto(isLoading: $isLoading, categoryGroup: $categoryGroup, districtGroup: $districtGroup, universityGroup: $universityGroup)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _HomeGroupDto &&
            (identical(other.isLoading, isLoading) ||
                other.isLoading == isLoading) &&
            const DeepCollectionEquality()
                .equals(other.categoryGroup, categoryGroup) &&
            const DeepCollectionEquality()
                .equals(other.districtGroup, districtGroup) &&
            const DeepCollectionEquality()
                .equals(other.universityGroup, universityGroup));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType,
      isLoading,
      const DeepCollectionEquality().hash(categoryGroup),
      const DeepCollectionEquality().hash(districtGroup),
      const DeepCollectionEquality().hash(universityGroup));

  @JsonKey(ignore: true)
  @override
  _$HomeGroupDtoCopyWith<_HomeGroupDto> get copyWith =>
      __$HomeGroupDtoCopyWithImpl<_HomeGroupDto>(this, _$identity);
}

abstract class _HomeGroupDto implements HomeGroupDto {
  factory _HomeGroupDto(
      {required bool isLoading,
      required List<GroupInfo> categoryGroup,
      required List<GroupInfo> districtGroup,
      required List<GroupInfo> universityGroup}) = _$_HomeGroupDto;

  @override
  bool get isLoading;
  @override
  List<GroupInfo> get categoryGroup;
  @override
  List<GroupInfo> get districtGroup;
  @override
  List<GroupInfo> get universityGroup;
  @override
  @JsonKey(ignore: true)
  _$HomeGroupDtoCopyWith<_HomeGroupDto> get copyWith =>
      throw _privateConstructorUsedError;
}
