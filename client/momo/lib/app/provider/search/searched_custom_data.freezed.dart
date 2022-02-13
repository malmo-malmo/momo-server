// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'searched_custom_data.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$SearchedCustomDataTearOff {
  const _$SearchedCustomDataTearOff();

  _SearchedCustomData call(
      {required List<String> words,
      required List<int> groupIds,
      required List<GroupInfo> groups,
      required bool isLoading}) {
    return _SearchedCustomData(
      words: words,
      groupIds: groupIds,
      groups: groups,
      isLoading: isLoading,
    );
  }
}

/// @nodoc
const $SearchedCustomData = _$SearchedCustomDataTearOff();

/// @nodoc
mixin _$SearchedCustomData {
  List<String> get words => throw _privateConstructorUsedError;
  List<int> get groupIds => throw _privateConstructorUsedError;
  List<GroupInfo> get groups => throw _privateConstructorUsedError;
  bool get isLoading => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $SearchedCustomDataCopyWith<SearchedCustomData> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $SearchedCustomDataCopyWith<$Res> {
  factory $SearchedCustomDataCopyWith(
          SearchedCustomData value, $Res Function(SearchedCustomData) then) =
      _$SearchedCustomDataCopyWithImpl<$Res>;
  $Res call(
      {List<String> words,
      List<int> groupIds,
      List<GroupInfo> groups,
      bool isLoading});
}

/// @nodoc
class _$SearchedCustomDataCopyWithImpl<$Res>
    implements $SearchedCustomDataCopyWith<$Res> {
  _$SearchedCustomDataCopyWithImpl(this._value, this._then);

  final SearchedCustomData _value;
  // ignore: unused_field
  final $Res Function(SearchedCustomData) _then;

  @override
  $Res call({
    Object? words = freezed,
    Object? groupIds = freezed,
    Object? groups = freezed,
    Object? isLoading = freezed,
  }) {
    return _then(_value.copyWith(
      words: words == freezed
          ? _value.words
          : words // ignore: cast_nullable_to_non_nullable
              as List<String>,
      groupIds: groupIds == freezed
          ? _value.groupIds
          : groupIds // ignore: cast_nullable_to_non_nullable
              as List<int>,
      groups: groups == freezed
          ? _value.groups
          : groups // ignore: cast_nullable_to_non_nullable
              as List<GroupInfo>,
      isLoading: isLoading == freezed
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc
abstract class _$SearchedCustomDataCopyWith<$Res>
    implements $SearchedCustomDataCopyWith<$Res> {
  factory _$SearchedCustomDataCopyWith(
          _SearchedCustomData value, $Res Function(_SearchedCustomData) then) =
      __$SearchedCustomDataCopyWithImpl<$Res>;
  @override
  $Res call(
      {List<String> words,
      List<int> groupIds,
      List<GroupInfo> groups,
      bool isLoading});
}

/// @nodoc
class __$SearchedCustomDataCopyWithImpl<$Res>
    extends _$SearchedCustomDataCopyWithImpl<$Res>
    implements _$SearchedCustomDataCopyWith<$Res> {
  __$SearchedCustomDataCopyWithImpl(
      _SearchedCustomData _value, $Res Function(_SearchedCustomData) _then)
      : super(_value, (v) => _then(v as _SearchedCustomData));

  @override
  _SearchedCustomData get _value => super._value as _SearchedCustomData;

  @override
  $Res call({
    Object? words = freezed,
    Object? groupIds = freezed,
    Object? groups = freezed,
    Object? isLoading = freezed,
  }) {
    return _then(_SearchedCustomData(
      words: words == freezed
          ? _value.words
          : words // ignore: cast_nullable_to_non_nullable
              as List<String>,
      groupIds: groupIds == freezed
          ? _value.groupIds
          : groupIds // ignore: cast_nullable_to_non_nullable
              as List<int>,
      groups: groups == freezed
          ? _value.groups
          : groups // ignore: cast_nullable_to_non_nullable
              as List<GroupInfo>,
      isLoading: isLoading == freezed
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc

class _$_SearchedCustomData implements _SearchedCustomData {
  _$_SearchedCustomData(
      {required this.words,
      required this.groupIds,
      required this.groups,
      required this.isLoading});

  @override
  final List<String> words;
  @override
  final List<int> groupIds;
  @override
  final List<GroupInfo> groups;
  @override
  final bool isLoading;

  @override
  String toString() {
    return 'SearchedCustomData(words: $words, groupIds: $groupIds, groups: $groups, isLoading: $isLoading)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _SearchedCustomData &&
            const DeepCollectionEquality().equals(other.words, words) &&
            const DeepCollectionEquality().equals(other.groupIds, groupIds) &&
            const DeepCollectionEquality().equals(other.groups, groups) &&
            (identical(other.isLoading, isLoading) ||
                other.isLoading == isLoading));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType,
      const DeepCollectionEquality().hash(words),
      const DeepCollectionEquality().hash(groupIds),
      const DeepCollectionEquality().hash(groups),
      isLoading);

  @JsonKey(ignore: true)
  @override
  _$SearchedCustomDataCopyWith<_SearchedCustomData> get copyWith =>
      __$SearchedCustomDataCopyWithImpl<_SearchedCustomData>(this, _$identity);
}

abstract class _SearchedCustomData implements SearchedCustomData {
  factory _SearchedCustomData(
      {required List<String> words,
      required List<int> groupIds,
      required List<GroupInfo> groups,
      required bool isLoading}) = _$_SearchedCustomData;

  @override
  List<String> get words;
  @override
  List<int> get groupIds;
  @override
  List<GroupInfo> get groups;
  @override
  bool get isLoading;
  @override
  @JsonKey(ignore: true)
  _$SearchedCustomDataCopyWith<_SearchedCustomData> get copyWith =>
      throw _privateConstructorUsedError;
}
