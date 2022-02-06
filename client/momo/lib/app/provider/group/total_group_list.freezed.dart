// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'total_group_list.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$TotalGroupListTearOff {
  const _$TotalGroupListTearOff();

  _TotalGroupList call(
      {required bool isLoading, required List<TotalGroupInfo> groups}) {
    return _TotalGroupList(
      isLoading: isLoading,
      groups: groups,
    );
  }
}

/// @nodoc
const $TotalGroupList = _$TotalGroupListTearOff();

/// @nodoc
mixin _$TotalGroupList {
  bool get isLoading => throw _privateConstructorUsedError;
  List<TotalGroupInfo> get groups => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $TotalGroupListCopyWith<TotalGroupList> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $TotalGroupListCopyWith<$Res> {
  factory $TotalGroupListCopyWith(
          TotalGroupList value, $Res Function(TotalGroupList) then) =
      _$TotalGroupListCopyWithImpl<$Res>;
  $Res call({bool isLoading, List<TotalGroupInfo> groups});
}

/// @nodoc
class _$TotalGroupListCopyWithImpl<$Res>
    implements $TotalGroupListCopyWith<$Res> {
  _$TotalGroupListCopyWithImpl(this._value, this._then);

  final TotalGroupList _value;
  // ignore: unused_field
  final $Res Function(TotalGroupList) _then;

  @override
  $Res call({
    Object? isLoading = freezed,
    Object? groups = freezed,
  }) {
    return _then(_value.copyWith(
      isLoading: isLoading == freezed
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
      groups: groups == freezed
          ? _value.groups
          : groups // ignore: cast_nullable_to_non_nullable
              as List<TotalGroupInfo>,
    ));
  }
}

/// @nodoc
abstract class _$TotalGroupListCopyWith<$Res>
    implements $TotalGroupListCopyWith<$Res> {
  factory _$TotalGroupListCopyWith(
          _TotalGroupList value, $Res Function(_TotalGroupList) then) =
      __$TotalGroupListCopyWithImpl<$Res>;
  @override
  $Res call({bool isLoading, List<TotalGroupInfo> groups});
}

/// @nodoc
class __$TotalGroupListCopyWithImpl<$Res>
    extends _$TotalGroupListCopyWithImpl<$Res>
    implements _$TotalGroupListCopyWith<$Res> {
  __$TotalGroupListCopyWithImpl(
      _TotalGroupList _value, $Res Function(_TotalGroupList) _then)
      : super(_value, (v) => _then(v as _TotalGroupList));

  @override
  _TotalGroupList get _value => super._value as _TotalGroupList;

  @override
  $Res call({
    Object? isLoading = freezed,
    Object? groups = freezed,
  }) {
    return _then(_TotalGroupList(
      isLoading: isLoading == freezed
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
      groups: groups == freezed
          ? _value.groups
          : groups // ignore: cast_nullable_to_non_nullable
              as List<TotalGroupInfo>,
    ));
  }
}

/// @nodoc

class _$_TotalGroupList implements _TotalGroupList {
  _$_TotalGroupList({required this.isLoading, required this.groups});

  @override
  final bool isLoading;
  @override
  final List<TotalGroupInfo> groups;

  @override
  String toString() {
    return 'TotalGroupList(isLoading: $isLoading, groups: $groups)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _TotalGroupList &&
            (identical(other.isLoading, isLoading) ||
                other.isLoading == isLoading) &&
            const DeepCollectionEquality().equals(other.groups, groups));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType, isLoading, const DeepCollectionEquality().hash(groups));

  @JsonKey(ignore: true)
  @override
  _$TotalGroupListCopyWith<_TotalGroupList> get copyWith =>
      __$TotalGroupListCopyWithImpl<_TotalGroupList>(this, _$identity);
}

abstract class _TotalGroupList implements TotalGroupList {
  factory _TotalGroupList(
      {required bool isLoading,
      required List<TotalGroupInfo> groups}) = _$_TotalGroupList;

  @override
  bool get isLoading;
  @override
  List<TotalGroupInfo> get groups;
  @override
  @JsonKey(ignore: true)
  _$TotalGroupListCopyWith<_TotalGroupList> get copyWith =>
      throw _privateConstructorUsedError;
}
