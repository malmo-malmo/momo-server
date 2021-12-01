// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'comment_request.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

CommentRequest _$CommentRequestFromJson(Map<String, dynamic> json) {
  return _CommentRequest.fromJson(json);
}

/// @nodoc
class _$CommentRequestTearOff {
  const _$CommentRequestTearOff();

  _CommentRequest call({required int postId, required String contents}) {
    return _CommentRequest(
      postId: postId,
      contents: contents,
    );
  }

  CommentRequest fromJson(Map<String, Object?> json) {
    return CommentRequest.fromJson(json);
  }
}

/// @nodoc
const $CommentRequest = _$CommentRequestTearOff();

/// @nodoc
mixin _$CommentRequest {
  int get postId => throw _privateConstructorUsedError;
  String get contents => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $CommentRequestCopyWith<CommentRequest> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $CommentRequestCopyWith<$Res> {
  factory $CommentRequestCopyWith(
          CommentRequest value, $Res Function(CommentRequest) then) =
      _$CommentRequestCopyWithImpl<$Res>;
  $Res call({int postId, String contents});
}

/// @nodoc
class _$CommentRequestCopyWithImpl<$Res>
    implements $CommentRequestCopyWith<$Res> {
  _$CommentRequestCopyWithImpl(this._value, this._then);

  final CommentRequest _value;
  // ignore: unused_field
  final $Res Function(CommentRequest) _then;

  @override
  $Res call({
    Object? postId = freezed,
    Object? contents = freezed,
  }) {
    return _then(_value.copyWith(
      postId: postId == freezed
          ? _value.postId
          : postId // ignore: cast_nullable_to_non_nullable
              as int,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
abstract class _$CommentRequestCopyWith<$Res>
    implements $CommentRequestCopyWith<$Res> {
  factory _$CommentRequestCopyWith(
          _CommentRequest value, $Res Function(_CommentRequest) then) =
      __$CommentRequestCopyWithImpl<$Res>;
  @override
  $Res call({int postId, String contents});
}

/// @nodoc
class __$CommentRequestCopyWithImpl<$Res>
    extends _$CommentRequestCopyWithImpl<$Res>
    implements _$CommentRequestCopyWith<$Res> {
  __$CommentRequestCopyWithImpl(
      _CommentRequest _value, $Res Function(_CommentRequest) _then)
      : super(_value, (v) => _then(v as _CommentRequest));

  @override
  _CommentRequest get _value => super._value as _CommentRequest;

  @override
  $Res call({
    Object? postId = freezed,
    Object? contents = freezed,
  }) {
    return _then(_CommentRequest(
      postId: postId == freezed
          ? _value.postId
          : postId // ignore: cast_nullable_to_non_nullable
              as int,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_CommentRequest implements _CommentRequest {
  _$_CommentRequest({required this.postId, required this.contents});

  factory _$_CommentRequest.fromJson(Map<String, dynamic> json) =>
      _$$_CommentRequestFromJson(json);

  @override
  final int postId;
  @override
  final String contents;

  @override
  String toString() {
    return 'CommentRequest(postId: $postId, contents: $contents)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _CommentRequest &&
            (identical(other.postId, postId) || other.postId == postId) &&
            (identical(other.contents, contents) ||
                other.contents == contents));
  }

  @override
  int get hashCode => Object.hash(runtimeType, postId, contents);

  @JsonKey(ignore: true)
  @override
  _$CommentRequestCopyWith<_CommentRequest> get copyWith =>
      __$CommentRequestCopyWithImpl<_CommentRequest>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_CommentRequestToJson(this);
  }
}

abstract class _CommentRequest implements CommentRequest {
  factory _CommentRequest({required int postId, required String contents}) =
      _$_CommentRequest;

  factory _CommentRequest.fromJson(Map<String, dynamic> json) =
      _$_CommentRequest.fromJson;

  @override
  int get postId;
  @override
  String get contents;
  @override
  @JsonKey(ignore: true)
  _$CommentRequestCopyWith<_CommentRequest> get copyWith =>
      throw _privateConstructorUsedError;
}
