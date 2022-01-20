// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'wish_group_response.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

WishGroupResponse _$WishGroupResponseFromJson(Map<String, dynamic> json) {
  return _WishGroupResponse.fromJson(json);
}

/// @nodoc
class _$WishGroupResponseTearOff {
  const _$WishGroupResponseTearOff();

  _WishGroupResponse call(
      {required int id, required GroupInfo groupCardResponse}) {
    return _WishGroupResponse(
      id: id,
      groupCardResponse: groupCardResponse,
    );
  }

  WishGroupResponse fromJson(Map<String, Object?> json) {
    return WishGroupResponse.fromJson(json);
  }
}

/// @nodoc
const $WishGroupResponse = _$WishGroupResponseTearOff();

/// @nodoc
mixin _$WishGroupResponse {
  int get id => throw _privateConstructorUsedError;
  GroupInfo get groupCardResponse => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $WishGroupResponseCopyWith<WishGroupResponse> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $WishGroupResponseCopyWith<$Res> {
  factory $WishGroupResponseCopyWith(
          WishGroupResponse value, $Res Function(WishGroupResponse) then) =
      _$WishGroupResponseCopyWithImpl<$Res>;
  $Res call({int id, GroupInfo groupCardResponse});

  $GroupInfoCopyWith<$Res> get groupCardResponse;
}

/// @nodoc
class _$WishGroupResponseCopyWithImpl<$Res>
    implements $WishGroupResponseCopyWith<$Res> {
  _$WishGroupResponseCopyWithImpl(this._value, this._then);

  final WishGroupResponse _value;
  // ignore: unused_field
  final $Res Function(WishGroupResponse) _then;

  @override
  $Res call({
    Object? id = freezed,
    Object? groupCardResponse = freezed,
  }) {
    return _then(_value.copyWith(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      groupCardResponse: groupCardResponse == freezed
          ? _value.groupCardResponse
          : groupCardResponse // ignore: cast_nullable_to_non_nullable
              as GroupInfo,
    ));
  }

  @override
  $GroupInfoCopyWith<$Res> get groupCardResponse {
    return $GroupInfoCopyWith<$Res>(_value.groupCardResponse, (value) {
      return _then(_value.copyWith(groupCardResponse: value));
    });
  }
}

/// @nodoc
abstract class _$WishGroupResponseCopyWith<$Res>
    implements $WishGroupResponseCopyWith<$Res> {
  factory _$WishGroupResponseCopyWith(
          _WishGroupResponse value, $Res Function(_WishGroupResponse) then) =
      __$WishGroupResponseCopyWithImpl<$Res>;
  @override
  $Res call({int id, GroupInfo groupCardResponse});

  @override
  $GroupInfoCopyWith<$Res> get groupCardResponse;
}

/// @nodoc
class __$WishGroupResponseCopyWithImpl<$Res>
    extends _$WishGroupResponseCopyWithImpl<$Res>
    implements _$WishGroupResponseCopyWith<$Res> {
  __$WishGroupResponseCopyWithImpl(
      _WishGroupResponse _value, $Res Function(_WishGroupResponse) _then)
      : super(_value, (v) => _then(v as _WishGroupResponse));

  @override
  _WishGroupResponse get _value => super._value as _WishGroupResponse;

  @override
  $Res call({
    Object? id = freezed,
    Object? groupCardResponse = freezed,
  }) {
    return _then(_WishGroupResponse(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      groupCardResponse: groupCardResponse == freezed
          ? _value.groupCardResponse
          : groupCardResponse // ignore: cast_nullable_to_non_nullable
              as GroupInfo,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_WishGroupResponse implements _WishGroupResponse {
  _$_WishGroupResponse({required this.id, required this.groupCardResponse});

  factory _$_WishGroupResponse.fromJson(Map<String, dynamic> json) =>
      _$$_WishGroupResponseFromJson(json);

  @override
  final int id;
  @override
  final GroupInfo groupCardResponse;

  @override
  String toString() {
    return 'WishGroupResponse(id: $id, groupCardResponse: $groupCardResponse)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _WishGroupResponse &&
            (identical(other.id, id) || other.id == id) &&
            (identical(other.groupCardResponse, groupCardResponse) ||
                other.groupCardResponse == groupCardResponse));
  }

  @override
  int get hashCode => Object.hash(runtimeType, id, groupCardResponse);

  @JsonKey(ignore: true)
  @override
  _$WishGroupResponseCopyWith<_WishGroupResponse> get copyWith =>
      __$WishGroupResponseCopyWithImpl<_WishGroupResponse>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_WishGroupResponseToJson(this);
  }
}

abstract class _WishGroupResponse implements WishGroupResponse {
  factory _WishGroupResponse(
      {required int id,
      required GroupInfo groupCardResponse}) = _$_WishGroupResponse;

  factory _WishGroupResponse.fromJson(Map<String, dynamic> json) =
      _$_WishGroupResponse.fromJson;

  @override
  int get id;
  @override
  GroupInfo get groupCardResponse;
  @override
  @JsonKey(ignore: true)
  _$WishGroupResponseCopyWith<_WishGroupResponse> get copyWith =>
      throw _privateConstructorUsedError;
}
