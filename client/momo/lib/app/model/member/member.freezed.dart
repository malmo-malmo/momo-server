// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'member.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

Member _$MemberFromJson(Map<String, dynamic> json) {
  return _Member.fromJson(json);
}

/// @nodoc
class _$MemberTearOff {
  const _$MemberTearOff();

  _Member call(
      {required int id,
      required String name,
      required String profile,
      required int rate}) {
    return _Member(
      id: id,
      name: name,
      profile: profile,
      rate: rate,
    );
  }

  Member fromJson(Map<String, Object?> json) {
    return Member.fromJson(json);
  }
}

/// @nodoc
const $Member = _$MemberTearOff();

/// @nodoc
mixin _$Member {
  int get id => throw _privateConstructorUsedError;
  String get name => throw _privateConstructorUsedError;
  String get profile => throw _privateConstructorUsedError;
  int get rate => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $MemberCopyWith<Member> get copyWith => throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $MemberCopyWith<$Res> {
  factory $MemberCopyWith(Member value, $Res Function(Member) then) =
      _$MemberCopyWithImpl<$Res>;
  $Res call({int id, String name, String profile, int rate});
}

/// @nodoc
class _$MemberCopyWithImpl<$Res> implements $MemberCopyWith<$Res> {
  _$MemberCopyWithImpl(this._value, this._then);

  final Member _value;
  // ignore: unused_field
  final $Res Function(Member) _then;

  @override
  $Res call({
    Object? id = freezed,
    Object? name = freezed,
    Object? profile = freezed,
    Object? rate = freezed,
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
      profile: profile == freezed
          ? _value.profile
          : profile // ignore: cast_nullable_to_non_nullable
              as String,
      rate: rate == freezed
          ? _value.rate
          : rate // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
abstract class _$MemberCopyWith<$Res> implements $MemberCopyWith<$Res> {
  factory _$MemberCopyWith(_Member value, $Res Function(_Member) then) =
      __$MemberCopyWithImpl<$Res>;
  @override
  $Res call({int id, String name, String profile, int rate});
}

/// @nodoc
class __$MemberCopyWithImpl<$Res> extends _$MemberCopyWithImpl<$Res>
    implements _$MemberCopyWith<$Res> {
  __$MemberCopyWithImpl(_Member _value, $Res Function(_Member) _then)
      : super(_value, (v) => _then(v as _Member));

  @override
  _Member get _value => super._value as _Member;

  @override
  $Res call({
    Object? id = freezed,
    Object? name = freezed,
    Object? profile = freezed,
    Object? rate = freezed,
  }) {
    return _then(_Member(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      profile: profile == freezed
          ? _value.profile
          : profile // ignore: cast_nullable_to_non_nullable
              as String,
      rate: rate == freezed
          ? _value.rate
          : rate // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_Member implements _Member {
  _$_Member(
      {required this.id,
      required this.name,
      required this.profile,
      required this.rate});

  factory _$_Member.fromJson(Map<String, dynamic> json) =>
      _$$_MemberFromJson(json);

  @override
  final int id;
  @override
  final String name;
  @override
  final String profile;
  @override
  final int rate;

  @override
  String toString() {
    return 'Member(id: $id, name: $name, profile: $profile, rate: $rate)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _Member &&
            (identical(other.id, id) || other.id == id) &&
            (identical(other.name, name) || other.name == name) &&
            (identical(other.profile, profile) || other.profile == profile) &&
            (identical(other.rate, rate) || other.rate == rate));
  }

  @override
  int get hashCode => Object.hash(runtimeType, id, name, profile, rate);

  @JsonKey(ignore: true)
  @override
  _$MemberCopyWith<_Member> get copyWith =>
      __$MemberCopyWithImpl<_Member>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_MemberToJson(this);
  }
}

abstract class _Member implements Member {
  factory _Member(
      {required int id,
      required String name,
      required String profile,
      required int rate}) = _$_Member;

  factory _Member.fromJson(Map<String, dynamic> json) = _$_Member.fromJson;

  @override
  int get id;
  @override
  String get name;
  @override
  String get profile;
  @override
  int get rate;
  @override
  @JsonKey(ignore: true)
  _$MemberCopyWith<_Member> get copyWith => throw _privateConstructorUsedError;
}
