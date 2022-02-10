// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'total_group_info.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

TotalGroupInfo _$TotalGroupInfoFromJson(Map<String, dynamic> json) {
  return _TotalGroupInfo.fromJson(json);
}

/// @nodoc
class _$TotalGroupInfoTearOff {
  const _$TotalGroupInfoTearOff();

  _TotalGroupInfo call(
      {required int id,
      required String name,
      required bool offline,
      required int participantCnt,
      required String startDate,
      String? imageUrl,
      required bool end}) {
    return _TotalGroupInfo(
      id: id,
      name: name,
      offline: offline,
      participantCnt: participantCnt,
      startDate: startDate,
      imageUrl: imageUrl,
      end: end,
    );
  }

  TotalGroupInfo fromJson(Map<String, Object?> json) {
    return TotalGroupInfo.fromJson(json);
  }
}

/// @nodoc
const $TotalGroupInfo = _$TotalGroupInfoTearOff();

/// @nodoc
mixin _$TotalGroupInfo {
  int get id => throw _privateConstructorUsedError;
  String get name => throw _privateConstructorUsedError;
  bool get offline => throw _privateConstructorUsedError;
  int get participantCnt => throw _privateConstructorUsedError;
  String get startDate => throw _privateConstructorUsedError;
  String? get imageUrl => throw _privateConstructorUsedError;
  bool get end => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $TotalGroupInfoCopyWith<TotalGroupInfo> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $TotalGroupInfoCopyWith<$Res> {
  factory $TotalGroupInfoCopyWith(
          TotalGroupInfo value, $Res Function(TotalGroupInfo) then) =
      _$TotalGroupInfoCopyWithImpl<$Res>;
  $Res call(
      {int id,
      String name,
      bool offline,
      int participantCnt,
      String startDate,
      String? imageUrl,
      bool end});
}

/// @nodoc
class _$TotalGroupInfoCopyWithImpl<$Res>
    implements $TotalGroupInfoCopyWith<$Res> {
  _$TotalGroupInfoCopyWithImpl(this._value, this._then);

  final TotalGroupInfo _value;
  // ignore: unused_field
  final $Res Function(TotalGroupInfo) _then;

  @override
  $Res call({
    Object? id = freezed,
    Object? name = freezed,
    Object? offline = freezed,
    Object? participantCnt = freezed,
    Object? startDate = freezed,
    Object? imageUrl = freezed,
    Object? end = freezed,
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
      end: end == freezed
          ? _value.end
          : end // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc
abstract class _$TotalGroupInfoCopyWith<$Res>
    implements $TotalGroupInfoCopyWith<$Res> {
  factory _$TotalGroupInfoCopyWith(
          _TotalGroupInfo value, $Res Function(_TotalGroupInfo) then) =
      __$TotalGroupInfoCopyWithImpl<$Res>;
  @override
  $Res call(
      {int id,
      String name,
      bool offline,
      int participantCnt,
      String startDate,
      String? imageUrl,
      bool end});
}

/// @nodoc
class __$TotalGroupInfoCopyWithImpl<$Res>
    extends _$TotalGroupInfoCopyWithImpl<$Res>
    implements _$TotalGroupInfoCopyWith<$Res> {
  __$TotalGroupInfoCopyWithImpl(
      _TotalGroupInfo _value, $Res Function(_TotalGroupInfo) _then)
      : super(_value, (v) => _then(v as _TotalGroupInfo));

  @override
  _TotalGroupInfo get _value => super._value as _TotalGroupInfo;

  @override
  $Res call({
    Object? id = freezed,
    Object? name = freezed,
    Object? offline = freezed,
    Object? participantCnt = freezed,
    Object? startDate = freezed,
    Object? imageUrl = freezed,
    Object? end = freezed,
  }) {
    return _then(_TotalGroupInfo(
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
      end: end == freezed
          ? _value.end
          : end // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_TotalGroupInfo implements _TotalGroupInfo {
  _$_TotalGroupInfo(
      {required this.id,
      required this.name,
      required this.offline,
      required this.participantCnt,
      required this.startDate,
      this.imageUrl,
      required this.end});

  factory _$_TotalGroupInfo.fromJson(Map<String, dynamic> json) =>
      _$$_TotalGroupInfoFromJson(json);

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
  final bool end;

  @override
  String toString() {
    return 'TotalGroupInfo(id: $id, name: $name, offline: $offline, participantCnt: $participantCnt, startDate: $startDate, imageUrl: $imageUrl, end: $end)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _TotalGroupInfo &&
            (identical(other.id, id) || other.id == id) &&
            (identical(other.name, name) || other.name == name) &&
            (identical(other.offline, offline) || other.offline == offline) &&
            (identical(other.participantCnt, participantCnt) ||
                other.participantCnt == participantCnt) &&
            (identical(other.startDate, startDate) ||
                other.startDate == startDate) &&
            (identical(other.imageUrl, imageUrl) ||
                other.imageUrl == imageUrl) &&
            (identical(other.end, end) || other.end == end));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType, id, name, offline, participantCnt, startDate, imageUrl, end);

  @JsonKey(ignore: true)
  @override
  _$TotalGroupInfoCopyWith<_TotalGroupInfo> get copyWith =>
      __$TotalGroupInfoCopyWithImpl<_TotalGroupInfo>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_TotalGroupInfoToJson(this);
  }
}

abstract class _TotalGroupInfo implements TotalGroupInfo {
  factory _TotalGroupInfo(
      {required int id,
      required String name,
      required bool offline,
      required int participantCnt,
      required String startDate,
      String? imageUrl,
      required bool end}) = _$_TotalGroupInfo;

  factory _TotalGroupInfo.fromJson(Map<String, dynamic> json) =
      _$_TotalGroupInfo.fromJson;

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
  bool get end;
  @override
  @JsonKey(ignore: true)
  _$TotalGroupInfoCopyWith<_TotalGroupInfo> get copyWith =>
      throw _privateConstructorUsedError;
}
