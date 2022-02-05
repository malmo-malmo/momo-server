// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'group_summary_response.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

GroupSummaryReseponse _$GroupSummaryReseponseFromJson(
    Map<String, dynamic> json) {
  return _GroupSummaryReseponse.fromJson(json);
}

/// @nodoc
class _$GroupSummaryReseponseTearOff {
  const _$GroupSummaryReseponseTearOff();

  _GroupSummaryReseponse call(
      {required int id, required String name, required String category}) {
    return _GroupSummaryReseponse(
      id: id,
      name: name,
      category: category,
    );
  }

  GroupSummaryReseponse fromJson(Map<String, Object?> json) {
    return GroupSummaryReseponse.fromJson(json);
  }
}

/// @nodoc
const $GroupSummaryReseponse = _$GroupSummaryReseponseTearOff();

/// @nodoc
mixin _$GroupSummaryReseponse {
  int get id => throw _privateConstructorUsedError;
  String get name => throw _privateConstructorUsedError;
  String get category => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $GroupSummaryReseponseCopyWith<GroupSummaryReseponse> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $GroupSummaryReseponseCopyWith<$Res> {
  factory $GroupSummaryReseponseCopyWith(GroupSummaryReseponse value,
          $Res Function(GroupSummaryReseponse) then) =
      _$GroupSummaryReseponseCopyWithImpl<$Res>;
  $Res call({int id, String name, String category});
}

/// @nodoc
class _$GroupSummaryReseponseCopyWithImpl<$Res>
    implements $GroupSummaryReseponseCopyWith<$Res> {
  _$GroupSummaryReseponseCopyWithImpl(this._value, this._then);

  final GroupSummaryReseponse _value;
  // ignore: unused_field
  final $Res Function(GroupSummaryReseponse) _then;

  @override
  $Res call({
    Object? id = freezed,
    Object? name = freezed,
    Object? category = freezed,
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
      category: category == freezed
          ? _value.category
          : category // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
abstract class _$GroupSummaryReseponseCopyWith<$Res>
    implements $GroupSummaryReseponseCopyWith<$Res> {
  factory _$GroupSummaryReseponseCopyWith(_GroupSummaryReseponse value,
          $Res Function(_GroupSummaryReseponse) then) =
      __$GroupSummaryReseponseCopyWithImpl<$Res>;
  @override
  $Res call({int id, String name, String category});
}

/// @nodoc
class __$GroupSummaryReseponseCopyWithImpl<$Res>
    extends _$GroupSummaryReseponseCopyWithImpl<$Res>
    implements _$GroupSummaryReseponseCopyWith<$Res> {
  __$GroupSummaryReseponseCopyWithImpl(_GroupSummaryReseponse _value,
      $Res Function(_GroupSummaryReseponse) _then)
      : super(_value, (v) => _then(v as _GroupSummaryReseponse));

  @override
  _GroupSummaryReseponse get _value => super._value as _GroupSummaryReseponse;

  @override
  $Res call({
    Object? id = freezed,
    Object? name = freezed,
    Object? category = freezed,
  }) {
    return _then(_GroupSummaryReseponse(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      category: category == freezed
          ? _value.category
          : category // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_GroupSummaryReseponse implements _GroupSummaryReseponse {
  _$_GroupSummaryReseponse(
      {required this.id, required this.name, required this.category});

  factory _$_GroupSummaryReseponse.fromJson(Map<String, dynamic> json) =>
      _$$_GroupSummaryReseponseFromJson(json);

  @override
  final int id;
  @override
  final String name;
  @override
  final String category;

  @override
  String toString() {
    return 'GroupSummaryReseponse(id: $id, name: $name, category: $category)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _GroupSummaryReseponse &&
            (identical(other.id, id) || other.id == id) &&
            (identical(other.name, name) || other.name == name) &&
            (identical(other.category, category) ||
                other.category == category));
  }

  @override
  int get hashCode => Object.hash(runtimeType, id, name, category);

  @JsonKey(ignore: true)
  @override
  _$GroupSummaryReseponseCopyWith<_GroupSummaryReseponse> get copyWith =>
      __$GroupSummaryReseponseCopyWithImpl<_GroupSummaryReseponse>(
          this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_GroupSummaryReseponseToJson(this);
  }
}

abstract class _GroupSummaryReseponse implements GroupSummaryReseponse {
  factory _GroupSummaryReseponse(
      {required int id,
      required String name,
      required String category}) = _$_GroupSummaryReseponse;

  factory _GroupSummaryReseponse.fromJson(Map<String, dynamic> json) =
      _$_GroupSummaryReseponse.fromJson;

  @override
  int get id;
  @override
  String get name;
  @override
  String get category;
  @override
  @JsonKey(ignore: true)
  _$GroupSummaryReseponseCopyWith<_GroupSummaryReseponse> get copyWith =>
      throw _privateConstructorUsedError;
}
