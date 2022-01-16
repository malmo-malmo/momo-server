// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'group_info.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

GroupInfo _$GroupInfoFromJson(Map<String, dynamic> json) {
  return _GroupInfo.fromJson(json);
}

/// @nodoc
class _$GroupInfoTearOff {
  const _$GroupInfoTearOff();

  _GroupInfo call(
      {required int id,
      required String name,
      required bool offline,
      required int participantCnt,
      required String startDate,
      String? imageUrl,
      required bool favoriteGroup}) {
    return _GroupInfo(
      id: id,
      name: name,
      offline: offline,
      participantCnt: participantCnt,
      startDate: startDate,
      imageUrl: imageUrl,
      favoriteGroup: favoriteGroup,
    );
  }

  GroupInfo fromJson(Map<String, Object?> json) {
    return GroupInfo.fromJson(json);
  }
}

/// @nodoc
const $GroupInfo = _$GroupInfoTearOff();

/// @nodoc
mixin _$GroupInfo {
  int get id => throw _privateConstructorUsedError;
  String get name => throw _privateConstructorUsedError;
  bool get offline => throw _privateConstructorUsedError;
  int get participantCnt => throw _privateConstructorUsedError;
  String get startDate => throw _privateConstructorUsedError;
  String? get imageUrl => throw _privateConstructorUsedError;
  bool get favoriteGroup => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $GroupInfoCopyWith<GroupInfo> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $GroupInfoCopyWith<$Res> {
  factory $GroupInfoCopyWith(GroupInfo value, $Res Function(GroupInfo) then) =
      _$GroupInfoCopyWithImpl<$Res>;
  $Res call(
      {int id,
      String name,
      bool offline,
      int participantCnt,
      String startDate,
      String? imageUrl,
      bool favoriteGroup});
}

/// @nodoc
class _$GroupInfoCopyWithImpl<$Res> implements $GroupInfoCopyWith<$Res> {
  _$GroupInfoCopyWithImpl(this._value, this._then);

  final GroupInfo _value;
  // ignore: unused_field
  final $Res Function(GroupInfo) _then;

  @override
  $Res call({
    Object? id = freezed,
    Object? name = freezed,
    Object? offline = freezed,
    Object? participantCnt = freezed,
    Object? startDate = freezed,
    Object? imageUrl = freezed,
    Object? favoriteGroup = freezed,
  }) {
    return _then(_value.copyWith(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      offline: offline == freezed
          ? _value.offline
          : offline // ignore: cast_nullable_to_non_nullable
              as bool,
      participantCnt: participantCnt == freezed
          ? _value.participantCnt
          : participantCnt // ignore: cast_nullable_to_non_nullable
              as int,
      startDate: startDate == freezed
          ? _value.startDate
          : startDate // ignore: cast_nullable_to_non_nullable
              as String,
      imageUrl: imageUrl == freezed
          ? _value.imageUrl
          : imageUrl // ignore: cast_nullable_to_non_nullable
              as String?,
      favoriteGroup: favoriteGroup == freezed
          ? _value.favoriteGroup
          : favoriteGroup // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc
abstract class _$GroupInfoCopyWith<$Res> implements $GroupInfoCopyWith<$Res> {
  factory _$GroupInfoCopyWith(
          _GroupInfo value, $Res Function(_GroupInfo) then) =
      __$GroupInfoCopyWithImpl<$Res>;
  @override
  $Res call(
      {int id,
      String name,
      bool offline,
      int participantCnt,
      String startDate,
      String? imageUrl,
      bool favoriteGroup});
}

/// @nodoc
class __$GroupInfoCopyWithImpl<$Res> extends _$GroupInfoCopyWithImpl<$Res>
    implements _$GroupInfoCopyWith<$Res> {
  __$GroupInfoCopyWithImpl(_GroupInfo _value, $Res Function(_GroupInfo) _then)
      : super(_value, (v) => _then(v as _GroupInfo));

  @override
  _GroupInfo get _value => super._value as _GroupInfo;

  @override
  $Res call({
    Object? id = freezed,
    Object? name = freezed,
    Object? offline = freezed,
    Object? participantCnt = freezed,
    Object? startDate = freezed,
    Object? imageUrl = freezed,
    Object? favoriteGroup = freezed,
  }) {
    return _then(_GroupInfo(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      offline: offline == freezed
          ? _value.offline
          : offline // ignore: cast_nullable_to_non_nullable
              as bool,
      participantCnt: participantCnt == freezed
          ? _value.participantCnt
          : participantCnt // ignore: cast_nullable_to_non_nullable
              as int,
      startDate: startDate == freezed
          ? _value.startDate
          : startDate // ignore: cast_nullable_to_non_nullable
              as String,
      imageUrl: imageUrl == freezed
          ? _value.imageUrl
          : imageUrl // ignore: cast_nullable_to_non_nullable
              as String?,
      favoriteGroup: favoriteGroup == freezed
          ? _value.favoriteGroup
          : favoriteGroup // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_GroupInfo implements _GroupInfo {
  _$_GroupInfo(
      {required this.id,
      required this.name,
      required this.offline,
      required this.participantCnt,
      required this.startDate,
      this.imageUrl,
      required this.favoriteGroup});

  factory _$_GroupInfo.fromJson(Map<String, dynamic> json) =>
      _$$_GroupInfoFromJson(json);

  @override
  final int id;
  @override
  final String name;
  @override
  final bool offline;
  @override
  final int participantCnt;
  @override
  final String startDate;
  @override
  final String? imageUrl;
  @override
  final bool favoriteGroup;

  @override
  String toString() {
    return 'GroupInfo(id: $id, name: $name, offline: $offline, participantCnt: $participantCnt, startDate: $startDate, imageUrl: $imageUrl, favoriteGroup: $favoriteGroup)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _GroupInfo &&
            (identical(other.id, id) || other.id == id) &&
            (identical(other.name, name) || other.name == name) &&
            (identical(other.offline, offline) || other.offline == offline) &&
            (identical(other.participantCnt, participantCnt) ||
                other.participantCnt == participantCnt) &&
            (identical(other.startDate, startDate) ||
                other.startDate == startDate) &&
            (identical(other.imageUrl, imageUrl) ||
                other.imageUrl == imageUrl) &&
            (identical(other.favoriteGroup, favoriteGroup) ||
                other.favoriteGroup == favoriteGroup));
  }

  @override
  int get hashCode => Object.hash(runtimeType, id, name, offline,
      participantCnt, startDate, imageUrl, favoriteGroup);

  @JsonKey(ignore: true)
  @override
  _$GroupInfoCopyWith<_GroupInfo> get copyWith =>
      __$GroupInfoCopyWithImpl<_GroupInfo>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_GroupInfoToJson(this);
  }
}

abstract class _GroupInfo implements GroupInfo {
  factory _GroupInfo(
      {required int id,
      required String name,
      required bool offline,
      required int participantCnt,
      required String startDate,
      String? imageUrl,
      required bool favoriteGroup}) = _$_GroupInfo;

  factory _GroupInfo.fromJson(Map<String, dynamic> json) =
      _$_GroupInfo.fromJson;

  @override
  int get id;
  @override
  String get name;
  @override
  bool get offline;
  @override
  int get participantCnt;
  @override
  String get startDate;
  @override
  String? get imageUrl;
  @override
  bool get favoriteGroup;
  @override
  @JsonKey(ignore: true)
  _$GroupInfoCopyWith<_GroupInfo> get copyWith =>
      throw _privateConstructorUsedError;
}
