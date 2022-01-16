// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'group_list_dto.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$GroupListDtoTearOff {
  const _$GroupListDtoTearOff();

  _GroupListDto call(
      {required List<GroupInfo> groups,
      required int nextPage,
      required bool hasNext}) {
    return _GroupListDto(
      groups: groups,
      nextPage: nextPage,
      hasNext: hasNext,
    );
  }
}

/// @nodoc
const $GroupListDto = _$GroupListDtoTearOff();

/// @nodoc
mixin _$GroupListDto {
  List<GroupInfo> get groups => throw _privateConstructorUsedError;
  int get nextPage => throw _privateConstructorUsedError;
  bool get hasNext => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $GroupListDtoCopyWith<GroupListDto> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $GroupListDtoCopyWith<$Res> {
  factory $GroupListDtoCopyWith(
          GroupListDto value, $Res Function(GroupListDto) then) =
      _$GroupListDtoCopyWithImpl<$Res>;
  $Res call({List<GroupInfo> groups, int nextPage, bool hasNext});
}

/// @nodoc
class _$GroupListDtoCopyWithImpl<$Res> implements $GroupListDtoCopyWith<$Res> {
  _$GroupListDtoCopyWithImpl(this._value, this._then);

  final GroupListDto _value;
  // ignore: unused_field
  final $Res Function(GroupListDto) _then;

  @override
  $Res call({
    Object? groups = freezed,
    Object? nextPage = freezed,
    Object? hasNext = freezed,
  }) {
    return _then(_value.copyWith(
      groups: groups == freezed
          ? _value.groups
          : groups // ignore: cast_nullable_to_non_nullable
              as List<GroupInfo>,
      nextPage: nextPage == freezed
          ? _value.nextPage
          : nextPage // ignore: cast_nullable_to_non_nullable
              as int,
      hasNext: hasNext == freezed
          ? _value.hasNext
          : hasNext // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc
abstract class _$GroupListDtoCopyWith<$Res>
    implements $GroupListDtoCopyWith<$Res> {
  factory _$GroupListDtoCopyWith(
          _GroupListDto value, $Res Function(_GroupListDto) then) =
      __$GroupListDtoCopyWithImpl<$Res>;
  @override
  $Res call({List<GroupInfo> groups, int nextPage, bool hasNext});
}

/// @nodoc
class __$GroupListDtoCopyWithImpl<$Res> extends _$GroupListDtoCopyWithImpl<$Res>
    implements _$GroupListDtoCopyWith<$Res> {
  __$GroupListDtoCopyWithImpl(
      _GroupListDto _value, $Res Function(_GroupListDto) _then)
      : super(_value, (v) => _then(v as _GroupListDto));

  @override
  _GroupListDto get _value => super._value as _GroupListDto;

  @override
  $Res call({
    Object? groups = freezed,
    Object? nextPage = freezed,
    Object? hasNext = freezed,
  }) {
    return _then(_GroupListDto(
      groups: groups == freezed
          ? _value.groups
          : groups // ignore: cast_nullable_to_non_nullable
              as List<GroupInfo>,
      nextPage: nextPage == freezed
          ? _value.nextPage
          : nextPage // ignore: cast_nullable_to_non_nullable
              as int,
      hasNext: hasNext == freezed
          ? _value.hasNext
          : hasNext // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc

class _$_GroupListDto implements _GroupListDto {
  _$_GroupListDto(
      {required this.groups, required this.nextPage, required this.hasNext});

  @override
  final List<GroupInfo> groups;
  @override
  final int nextPage;
  @override
  final bool hasNext;

  @override
  String toString() {
    return 'GroupListDto(groups: $groups, nextPage: $nextPage, hasNext: $hasNext)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _GroupListDto &&
            const DeepCollectionEquality().equals(other.groups, groups) &&
            (identical(other.nextPage, nextPage) ||
                other.nextPage == nextPage) &&
            (identical(other.hasNext, hasNext) || other.hasNext == hasNext));
  }

  @override
  int get hashCode => Object.hash(runtimeType,
      const DeepCollectionEquality().hash(groups), nextPage, hasNext);

  @JsonKey(ignore: true)
  @override
  _$GroupListDtoCopyWith<_GroupListDto> get copyWith =>
      __$GroupListDtoCopyWithImpl<_GroupListDto>(this, _$identity);
}

abstract class _GroupListDto implements GroupListDto {
  factory _GroupListDto(
      {required List<GroupInfo> groups,
      required int nextPage,
      required bool hasNext}) = _$_GroupListDto;

  @override
  List<GroupInfo> get groups;
  @override
  int get nextPage;
  @override
  bool get hasNext;
  @override
  @JsonKey(ignore: true)
  _$GroupListDtoCopyWith<_GroupListDto> get copyWith =>
      throw _privateConstructorUsedError;
}
