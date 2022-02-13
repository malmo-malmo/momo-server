// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'searched_data.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$SearchedDataTearOff {
  const _$SearchedDataTearOff();

  _SearchedData call(
      {@HiveField(0) required List<String> words,
      @HiveField(1) required List<int> groupIds}) {
    return _SearchedData(
      words: words,
      groupIds: groupIds,
    );
  }
}

/// @nodoc
const $SearchedData = _$SearchedDataTearOff();

/// @nodoc
mixin _$SearchedData {
  @HiveField(0)
  List<String> get words => throw _privateConstructorUsedError;
  @HiveField(1)
  List<int> get groupIds => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $SearchedDataCopyWith<SearchedData> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $SearchedDataCopyWith<$Res> {
  factory $SearchedDataCopyWith(
          SearchedData value, $Res Function(SearchedData) then) =
      _$SearchedDataCopyWithImpl<$Res>;
  $Res call(
      {@HiveField(0) List<String> words, @HiveField(1) List<int> groupIds});
}

/// @nodoc
class _$SearchedDataCopyWithImpl<$Res> implements $SearchedDataCopyWith<$Res> {
  _$SearchedDataCopyWithImpl(this._value, this._then);

  final SearchedData _value;
  // ignore: unused_field
  final $Res Function(SearchedData) _then;

  @override
  $Res call({
    Object? words = freezed,
    Object? groupIds = freezed,
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
    ));
  }
}

/// @nodoc
abstract class _$SearchedDataCopyWith<$Res>
    implements $SearchedDataCopyWith<$Res> {
  factory _$SearchedDataCopyWith(
          _SearchedData value, $Res Function(_SearchedData) then) =
      __$SearchedDataCopyWithImpl<$Res>;
  @override
  $Res call(
      {@HiveField(0) List<String> words, @HiveField(1) List<int> groupIds});
}

/// @nodoc
class __$SearchedDataCopyWithImpl<$Res> extends _$SearchedDataCopyWithImpl<$Res>
    implements _$SearchedDataCopyWith<$Res> {
  __$SearchedDataCopyWithImpl(
      _SearchedData _value, $Res Function(_SearchedData) _then)
      : super(_value, (v) => _then(v as _SearchedData));

  @override
  _SearchedData get _value => super._value as _SearchedData;

  @override
  $Res call({
    Object? words = freezed,
    Object? groupIds = freezed,
  }) {
    return _then(_SearchedData(
      words: words == freezed
          ? _value.words
          : words // ignore: cast_nullable_to_non_nullable
              as List<String>,
      groupIds: groupIds == freezed
          ? _value.groupIds
          : groupIds // ignore: cast_nullable_to_non_nullable
              as List<int>,
    ));
  }
}

/// @nodoc

@HiveType(typeId: 2, adapterName: 'SearchedDataAdapter')
class _$_SearchedData implements _SearchedData {
  _$_SearchedData(
      {@HiveField(0) required this.words,
      @HiveField(1) required this.groupIds});

  @override
  @HiveField(0)
  final List<String> words;
  @override
  @HiveField(1)
  final List<int> groupIds;

  @override
  String toString() {
    return 'SearchedData(words: $words, groupIds: $groupIds)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _SearchedData &&
            const DeepCollectionEquality().equals(other.words, words) &&
            const DeepCollectionEquality().equals(other.groupIds, groupIds));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType,
      const DeepCollectionEquality().hash(words),
      const DeepCollectionEquality().hash(groupIds));

  @JsonKey(ignore: true)
  @override
  _$SearchedDataCopyWith<_SearchedData> get copyWith =>
      __$SearchedDataCopyWithImpl<_SearchedData>(this, _$identity);
}

abstract class _SearchedData implements SearchedData {
  factory _SearchedData(
      {@HiveField(0) required List<String> words,
      @HiveField(1) required List<int> groupIds}) = _$_SearchedData;

  @override
  @HiveField(0)
  List<String> get words;
  @override
  @HiveField(1)
  List<int> get groupIds;
  @override
  @JsonKey(ignore: true)
  _$SearchedDataCopyWith<_SearchedData> get copyWith =>
      throw _privateConstructorUsedError;
}
