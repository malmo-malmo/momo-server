// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'management_post_response.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

ManagementPostResponse _$ManagementPostResponseFromJson(
    Map<String, dynamic> json) {
  return _ManagementPostResponse.fromJson(json);
}

/// @nodoc
class _$ManagementPostResponseTearOff {
  const _$ManagementPostResponseTearOff();

  _ManagementPostResponse call(
      {required String groupName,
      @JsonKey(name: 'postCardResponse') required Post post}) {
    return _ManagementPostResponse(
      groupName: groupName,
      post: post,
    );
  }

  ManagementPostResponse fromJson(Map<String, Object?> json) {
    return ManagementPostResponse.fromJson(json);
  }
}

/// @nodoc
const $ManagementPostResponse = _$ManagementPostResponseTearOff();

/// @nodoc
mixin _$ManagementPostResponse {
  String get groupName => throw _privateConstructorUsedError;
  @JsonKey(name: 'postCardResponse')
  Post get post => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $ManagementPostResponseCopyWith<ManagementPostResponse> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $ManagementPostResponseCopyWith<$Res> {
  factory $ManagementPostResponseCopyWith(ManagementPostResponse value,
          $Res Function(ManagementPostResponse) then) =
      _$ManagementPostResponseCopyWithImpl<$Res>;
  $Res call({String groupName, @JsonKey(name: 'postCardResponse') Post post});

  $PostCopyWith<$Res> get post;
}

/// @nodoc
class _$ManagementPostResponseCopyWithImpl<$Res>
    implements $ManagementPostResponseCopyWith<$Res> {
  _$ManagementPostResponseCopyWithImpl(this._value, this._then);

  final ManagementPostResponse _value;
  // ignore: unused_field
  final $Res Function(ManagementPostResponse) _then;

  @override
  $Res call({
    Object? groupName = freezed,
    Object? post = freezed,
  }) {
    return _then(_value.copyWith(
      groupName: groupName == freezed
          ? _value.groupName
          : groupName // ignore: cast_nullable_to_non_nullable
              as String,
      post: post == freezed
          ? _value.post
          : post // ignore: cast_nullable_to_non_nullable
              as Post,
    ));
  }

  @override
  $PostCopyWith<$Res> get post {
    return $PostCopyWith<$Res>(_value.post, (value) {
      return _then(_value.copyWith(post: value));
    });
  }
}

/// @nodoc
abstract class _$ManagementPostResponseCopyWith<$Res>
    implements $ManagementPostResponseCopyWith<$Res> {
  factory _$ManagementPostResponseCopyWith(_ManagementPostResponse value,
          $Res Function(_ManagementPostResponse) then) =
      __$ManagementPostResponseCopyWithImpl<$Res>;
  @override
  $Res call({String groupName, @JsonKey(name: 'postCardResponse') Post post});

  @override
  $PostCopyWith<$Res> get post;
}

/// @nodoc
class __$ManagementPostResponseCopyWithImpl<$Res>
    extends _$ManagementPostResponseCopyWithImpl<$Res>
    implements _$ManagementPostResponseCopyWith<$Res> {
  __$ManagementPostResponseCopyWithImpl(_ManagementPostResponse _value,
      $Res Function(_ManagementPostResponse) _then)
      : super(_value, (v) => _then(v as _ManagementPostResponse));

  @override
  _ManagementPostResponse get _value => super._value as _ManagementPostResponse;

  @override
  $Res call({
    Object? groupName = freezed,
    Object? post = freezed,
  }) {
    return _then(_ManagementPostResponse(
      groupName: groupName == freezed
          ? _value.groupName
          : groupName // ignore: cast_nullable_to_non_nullable
              as String,
      post: post == freezed
          ? _value.post
          : post // ignore: cast_nullable_to_non_nullable
              as Post,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_ManagementPostResponse implements _ManagementPostResponse {
  _$_ManagementPostResponse(
      {required this.groupName,
      @JsonKey(name: 'postCardResponse') required this.post});

  factory _$_ManagementPostResponse.fromJson(Map<String, dynamic> json) =>
      _$$_ManagementPostResponseFromJson(json);

  @override
  final String groupName;
  @override
  @JsonKey(name: 'postCardResponse')
  final Post post;

  @override
  String toString() {
    return 'ManagementPostResponse(groupName: $groupName, post: $post)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _ManagementPostResponse &&
            (identical(other.groupName, groupName) ||
                other.groupName == groupName) &&
            (identical(other.post, post) || other.post == post));
  }

  @override
  int get hashCode => Object.hash(runtimeType, groupName, post);

  @JsonKey(ignore: true)
  @override
  _$ManagementPostResponseCopyWith<_ManagementPostResponse> get copyWith =>
      __$ManagementPostResponseCopyWithImpl<_ManagementPostResponse>(
          this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_ManagementPostResponseToJson(this);
  }
}

abstract class _ManagementPostResponse implements ManagementPostResponse {
  factory _ManagementPostResponse(
          {required String groupName,
          @JsonKey(name: 'postCardResponse') required Post post}) =
      _$_ManagementPostResponse;

  factory _ManagementPostResponse.fromJson(Map<String, dynamic> json) =
      _$_ManagementPostResponse.fromJson;

  @override
  String get groupName;
  @override
  @JsonKey(name: 'postCardResponse')
  Post get post;
  @override
  @JsonKey(ignore: true)
  _$ManagementPostResponseCopyWith<_ManagementPostResponse> get copyWith =>
      throw _privateConstructorUsedError;
}
