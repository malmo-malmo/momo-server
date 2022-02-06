// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'my_group_list.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$MyGroupListTearOff {
  const _$MyGroupListTearOff();

  _MyGroupList call(
      {required bool isLoading,
      required List<MyGroup> myGroups,
      required List<GroupSummaryReseponse> participationGroups}) {
    return _MyGroupList(
      isLoading: isLoading,
      myGroups: myGroups,
      participationGroups: participationGroups,
    );
  }
}

/// @nodoc
const $MyGroupList = _$MyGroupListTearOff();

/// @nodoc
mixin _$MyGroupList {
  bool get isLoading => throw _privateConstructorUsedError;
  List<MyGroup> get myGroups => throw _privateConstructorUsedError;
  List<GroupSummaryReseponse> get participationGroups =>
      throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $MyGroupListCopyWith<MyGroupList> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $MyGroupListCopyWith<$Res> {
  factory $MyGroupListCopyWith(
          MyGroupList value, $Res Function(MyGroupList) then) =
      _$MyGroupListCopyWithImpl<$Res>;
  $Res call(
      {bool isLoading,
      List<MyGroup> myGroups,
      List<GroupSummaryReseponse> participationGroups});
}

/// @nodoc
class _$MyGroupListCopyWithImpl<$Res> implements $MyGroupListCopyWith<$Res> {
  _$MyGroupListCopyWithImpl(this._value, this._then);

  final MyGroupList _value;
  // ignore: unused_field
  final $Res Function(MyGroupList) _then;

  @override
  $Res call({
    Object? isLoading = freezed,
    Object? myGroups = freezed,
    Object? participationGroups = freezed,
  }) {
    return _then(_value.copyWith(
      isLoading: isLoading == freezed
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
      myGroups: myGroups == freezed
          ? _value.myGroups
          : myGroups // ignore: cast_nullable_to_non_nullable
              as List<MyGroup>,
      participationGroups: participationGroups == freezed
          ? _value.participationGroups
          : participationGroups // ignore: cast_nullable_to_non_nullable
              as List<GroupSummaryReseponse>,
    ));
  }
}

/// @nodoc
abstract class _$MyGroupListCopyWith<$Res>
    implements $MyGroupListCopyWith<$Res> {
  factory _$MyGroupListCopyWith(
          _MyGroupList value, $Res Function(_MyGroupList) then) =
      __$MyGroupListCopyWithImpl<$Res>;
  @override
  $Res call(
      {bool isLoading,
      List<MyGroup> myGroups,
      List<GroupSummaryReseponse> participationGroups});
}

/// @nodoc
class __$MyGroupListCopyWithImpl<$Res> extends _$MyGroupListCopyWithImpl<$Res>
    implements _$MyGroupListCopyWith<$Res> {
  __$MyGroupListCopyWithImpl(
      _MyGroupList _value, $Res Function(_MyGroupList) _then)
      : super(_value, (v) => _then(v as _MyGroupList));

  @override
  _MyGroupList get _value => super._value as _MyGroupList;

  @override
  $Res call({
    Object? isLoading = freezed,
    Object? myGroups = freezed,
    Object? participationGroups = freezed,
  }) {
    return _then(_MyGroupList(
      isLoading: isLoading == freezed
          ? _value.isLoading
          : isLoading // ignore: cast_nullable_to_non_nullable
              as bool,
      myGroups: myGroups == freezed
          ? _value.myGroups
          : myGroups // ignore: cast_nullable_to_non_nullable
              as List<MyGroup>,
      participationGroups: participationGroups == freezed
          ? _value.participationGroups
          : participationGroups // ignore: cast_nullable_to_non_nullable
              as List<GroupSummaryReseponse>,
    ));
  }
}

/// @nodoc

class _$_MyGroupList implements _MyGroupList {
  _$_MyGroupList(
      {required this.isLoading,
      required this.myGroups,
      required this.participationGroups});

  @override
  final bool isLoading;
  @override
  final List<MyGroup> myGroups;
  @override
  final List<GroupSummaryReseponse> participationGroups;

  @override
  String toString() {
    return 'MyGroupList(isLoading: $isLoading, myGroups: $myGroups, participationGroups: $participationGroups)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _MyGroupList &&
            (identical(other.isLoading, isLoading) ||
                other.isLoading == isLoading) &&
            const DeepCollectionEquality().equals(other.myGroups, myGroups) &&
            const DeepCollectionEquality()
                .equals(other.participationGroups, participationGroups));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType,
      isLoading,
      const DeepCollectionEquality().hash(myGroups),
      const DeepCollectionEquality().hash(participationGroups));

  @JsonKey(ignore: true)
  @override
  _$MyGroupListCopyWith<_MyGroupList> get copyWith =>
      __$MyGroupListCopyWithImpl<_MyGroupList>(this, _$identity);
}

abstract class _MyGroupList implements MyGroupList {
  factory _MyGroupList(
          {required bool isLoading,
          required List<MyGroup> myGroups,
          required List<GroupSummaryReseponse> participationGroups}) =
      _$_MyGroupList;

  @override
  bool get isLoading;
  @override
  List<MyGroup> get myGroups;
  @override
  List<GroupSummaryReseponse> get participationGroups;
  @override
  @JsonKey(ignore: true)
  _$MyGroupListCopyWith<_MyGroupList> get copyWith =>
      throw _privateConstructorUsedError;
}
