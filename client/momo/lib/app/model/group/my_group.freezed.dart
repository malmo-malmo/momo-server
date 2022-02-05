// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'my_group.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

MyGroup _$MyGroupFromJson(Map<String, dynamic> json) {
  return _MyGroup.fromJson(json);
}

/// @nodoc
class _$MyGroupTearOff {
  const _$MyGroupTearOff();

  _MyGroup call(
      {required int id,
      required String name,
      String? imageUrl,
      int? achievementRate}) {
    return _MyGroup(
      id: id,
      name: name,
      imageUrl: imageUrl,
      achievementRate: achievementRate,
    );
  }

  MyGroup fromJson(Map<String, Object?> json) {
    return MyGroup.fromJson(json);
  }
}

/// @nodoc
const $MyGroup = _$MyGroupTearOff();

/// @nodoc
mixin _$MyGroup {
  int get id => throw _privateConstructorUsedError;
  String get name => throw _privateConstructorUsedError;
  String? get imageUrl => throw _privateConstructorUsedError;
  int? get achievementRate => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $MyGroupCopyWith<MyGroup> get copyWith => throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $MyGroupCopyWith<$Res> {
  factory $MyGroupCopyWith(MyGroup value, $Res Function(MyGroup) then) =
      _$MyGroupCopyWithImpl<$Res>;
  $Res call({int id, String name, String? imageUrl, int? achievementRate});
}

/// @nodoc
class _$MyGroupCopyWithImpl<$Res> implements $MyGroupCopyWith<$Res> {
  _$MyGroupCopyWithImpl(this._value, this._then);

  final MyGroup _value;
  // ignore: unused_field
  final $Res Function(MyGroup) _then;

  @override
  $Res call({
    Object? id = freezed,
    Object? name = freezed,
    Object? imageUrl = freezed,
    Object? achievementRate = freezed,
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
      imageUrl: imageUrl == freezed
          ? _value.imageUrl
          : imageUrl // ignore: cast_nullable_to_non_nullable
              as String?,
      achievementRate: achievementRate == freezed
          ? _value.achievementRate
          : achievementRate // ignore: cast_nullable_to_non_nullable
              as int?,
    ));
  }
}

/// @nodoc
abstract class _$MyGroupCopyWith<$Res> implements $MyGroupCopyWith<$Res> {
  factory _$MyGroupCopyWith(_MyGroup value, $Res Function(_MyGroup) then) =
      __$MyGroupCopyWithImpl<$Res>;
  @override
  $Res call({int id, String name, String? imageUrl, int? achievementRate});
}

/// @nodoc
class __$MyGroupCopyWithImpl<$Res> extends _$MyGroupCopyWithImpl<$Res>
    implements _$MyGroupCopyWith<$Res> {
  __$MyGroupCopyWithImpl(_MyGroup _value, $Res Function(_MyGroup) _then)
      : super(_value, (v) => _then(v as _MyGroup));

  @override
  _MyGroup get _value => super._value as _MyGroup;

  @override
  $Res call({
    Object? id = freezed,
    Object? name = freezed,
    Object? imageUrl = freezed,
    Object? achievementRate = freezed,
  }) {
    return _then(_MyGroup(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      imageUrl: imageUrl == freezed
          ? _value.imageUrl
          : imageUrl // ignore: cast_nullable_to_non_nullable
              as String?,
      achievementRate: achievementRate == freezed
          ? _value.achievementRate
          : achievementRate // ignore: cast_nullable_to_non_nullable
              as int?,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_MyGroup implements _MyGroup {
  _$_MyGroup(
      {required this.id,
      required this.name,
      this.imageUrl,
      this.achievementRate});

  factory _$_MyGroup.fromJson(Map<String, dynamic> json) =>
      _$$_MyGroupFromJson(json);

  @override
  final int id;
  @override
  final String name;
  @override
  final String? imageUrl;
  @override
  final int? achievementRate;

  @override
  String toString() {
    return 'MyGroup(id: $id, name: $name, imageUrl: $imageUrl, achievementRate: $achievementRate)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _MyGroup &&
            (identical(other.id, id) || other.id == id) &&
            (identical(other.name, name) || other.name == name) &&
            (identical(other.imageUrl, imageUrl) ||
                other.imageUrl == imageUrl) &&
            (identical(other.achievementRate, achievementRate) ||
                other.achievementRate == achievementRate));
  }

  @override
  int get hashCode =>
      Object.hash(runtimeType, id, name, imageUrl, achievementRate);

  @JsonKey(ignore: true)
  @override
  _$MyGroupCopyWith<_MyGroup> get copyWith =>
      __$MyGroupCopyWithImpl<_MyGroup>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_MyGroupToJson(this);
  }
}

abstract class _MyGroup implements MyGroup {
  factory _MyGroup(
      {required int id,
      required String name,
      String? imageUrl,
      int? achievementRate}) = _$_MyGroup;

  factory _MyGroup.fromJson(Map<String, dynamic> json) = _$_MyGroup.fromJson;

  @override
  int get id;
  @override
  String get name;
  @override
  String? get imageUrl;
  @override
  int? get achievementRate;
  @override
  @JsonKey(ignore: true)
  _$MyGroupCopyWith<_MyGroup> get copyWith =>
      throw _privateConstructorUsedError;
}
