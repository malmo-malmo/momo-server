// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'comment_response.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

CommentResponse _$CommentResponseFromJson(Map<String, dynamic> json) {
  return _CommentResponse.fromJson(json);
}

/// @nodoc
class _$CommentResponseTearOff {
  const _$CommentResponseTearOff();

  _CommentResponse call(
      {@JsonKey(name: 'commentResponses') required List<Comment> comments,
      required int commentCnt}) {
    return _CommentResponse(
      comments: comments,
      commentCnt: commentCnt,
    );
  }

  CommentResponse fromJson(Map<String, Object?> json) {
    return CommentResponse.fromJson(json);
  }
}

/// @nodoc
const $CommentResponse = _$CommentResponseTearOff();

/// @nodoc
mixin _$CommentResponse {
  @JsonKey(name: 'commentResponses')
  List<Comment> get comments => throw _privateConstructorUsedError;
  int get commentCnt => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $CommentResponseCopyWith<CommentResponse> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $CommentResponseCopyWith<$Res> {
  factory $CommentResponseCopyWith(
          CommentResponse value, $Res Function(CommentResponse) then) =
      _$CommentResponseCopyWithImpl<$Res>;
  $Res call(
      {@JsonKey(name: 'commentResponses') List<Comment> comments,
      int commentCnt});
}

/// @nodoc
class _$CommentResponseCopyWithImpl<$Res>
    implements $CommentResponseCopyWith<$Res> {
  _$CommentResponseCopyWithImpl(this._value, this._then);

  final CommentResponse _value;
  // ignore: unused_field
  final $Res Function(CommentResponse) _then;

  @override
  $Res call({
    Object? comments = freezed,
    Object? commentCnt = freezed,
  }) {
    return _then(_value.copyWith(
      comments: comments == freezed
          ? _value.comments
          : comments // ignore: cast_nullable_to_non_nullable
              as List<Comment>,
      commentCnt: commentCnt == freezed
          ? _value.commentCnt
          : commentCnt // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
abstract class _$CommentResponseCopyWith<$Res>
    implements $CommentResponseCopyWith<$Res> {
  factory _$CommentResponseCopyWith(
          _CommentResponse value, $Res Function(_CommentResponse) then) =
      __$CommentResponseCopyWithImpl<$Res>;
  @override
  $Res call(
      {@JsonKey(name: 'commentResponses') List<Comment> comments,
      int commentCnt});
}

/// @nodoc
class __$CommentResponseCopyWithImpl<$Res>
    extends _$CommentResponseCopyWithImpl<$Res>
    implements _$CommentResponseCopyWith<$Res> {
  __$CommentResponseCopyWithImpl(
      _CommentResponse _value, $Res Function(_CommentResponse) _then)
      : super(_value, (v) => _then(v as _CommentResponse));

  @override
  _CommentResponse get _value => super._value as _CommentResponse;

  @override
  $Res call({
    Object? comments = freezed,
    Object? commentCnt = freezed,
  }) {
    return _then(_CommentResponse(
      comments: comments == freezed
          ? _value.comments
          : comments // ignore: cast_nullable_to_non_nullable
              as List<Comment>,
      commentCnt: commentCnt == freezed
          ? _value.commentCnt
          : commentCnt // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_CommentResponse implements _CommentResponse {
  _$_CommentResponse(
      {@JsonKey(name: 'commentResponses') required this.comments,
      required this.commentCnt});

  factory _$_CommentResponse.fromJson(Map<String, dynamic> json) =>
      _$$_CommentResponseFromJson(json);

  @override
  @JsonKey(name: 'commentResponses')
  final List<Comment> comments;
  @override
  final int commentCnt;

  @override
  String toString() {
    return 'CommentResponse(comments: $comments, commentCnt: $commentCnt)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _CommentResponse &&
            const DeepCollectionEquality().equals(other.comments, comments) &&
            (identical(other.commentCnt, commentCnt) ||
                other.commentCnt == commentCnt));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType, const DeepCollectionEquality().hash(comments), commentCnt);

  @JsonKey(ignore: true)
  @override
  _$CommentResponseCopyWith<_CommentResponse> get copyWith =>
      __$CommentResponseCopyWithImpl<_CommentResponse>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_CommentResponseToJson(this);
  }
}

abstract class _CommentResponse implements CommentResponse {
  factory _CommentResponse(
      {@JsonKey(name: 'commentResponses') required List<Comment> comments,
      required int commentCnt}) = _$_CommentResponse;

  factory _CommentResponse.fromJson(Map<String, dynamic> json) =
      _$_CommentResponse.fromJson;

  @override
  @JsonKey(name: 'commentResponses')
  List<Comment> get comments;
  @override
  int get commentCnt;
  @override
  @JsonKey(ignore: true)
  _$CommentResponseCopyWith<_CommentResponse> get copyWith =>
      throw _privateConstructorUsedError;
}
