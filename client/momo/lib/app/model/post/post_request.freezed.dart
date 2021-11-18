// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'post_request.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

PostRequest _$PostRequestFromJson(Map<String, dynamic> json) {
  return _PostRequest.fromJson(json);
}

/// @nodoc
class _$PostRequestTearOff {
  const _$PostRequestTearOff();

  _PostRequest call(
      {required String title, required String contents, required String img}) {
    return _PostRequest(
      title: title,
      contents: contents,
      img: img,
    );
  }

  PostRequest fromJson(Map<String, Object?> json) {
    return PostRequest.fromJson(json);
  }
}

/// @nodoc
const $PostRequest = _$PostRequestTearOff();

/// @nodoc
mixin _$PostRequest {
  String get title => throw _privateConstructorUsedError;
  String get contents => throw _privateConstructorUsedError;
  String get img => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $PostRequestCopyWith<PostRequest> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $PostRequestCopyWith<$Res> {
  factory $PostRequestCopyWith(
          PostRequest value, $Res Function(PostRequest) then) =
      _$PostRequestCopyWithImpl<$Res>;
  $Res call({String title, String contents, String img});
}

/// @nodoc
class _$PostRequestCopyWithImpl<$Res> implements $PostRequestCopyWith<$Res> {
  _$PostRequestCopyWithImpl(this._value, this._then);

  final PostRequest _value;
  // ignore: unused_field
  final $Res Function(PostRequest) _then;

  @override
  $Res call({
    Object? title = freezed,
    Object? contents = freezed,
    Object? img = freezed,
  }) {
    return _then(_value.copyWith(
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
              as String,
      img: img == freezed
          ? _value.img
          : img // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
abstract class _$PostRequestCopyWith<$Res>
    implements $PostRequestCopyWith<$Res> {
  factory _$PostRequestCopyWith(
          _PostRequest value, $Res Function(_PostRequest) then) =
      __$PostRequestCopyWithImpl<$Res>;
  @override
  $Res call({String title, String contents, String img});
}

/// @nodoc
class __$PostRequestCopyWithImpl<$Res> extends _$PostRequestCopyWithImpl<$Res>
    implements _$PostRequestCopyWith<$Res> {
  __$PostRequestCopyWithImpl(
      _PostRequest _value, $Res Function(_PostRequest) _then)
      : super(_value, (v) => _then(v as _PostRequest));

  @override
  _PostRequest get _value => super._value as _PostRequest;

  @override
  $Res call({
    Object? title = freezed,
    Object? contents = freezed,
    Object? img = freezed,
  }) {
    return _then(_PostRequest(
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
              as String,
      img: img == freezed
          ? _value.img
          : img // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_PostRequest implements _PostRequest {
  _$_PostRequest(
      {required this.title, required this.contents, required this.img});

  factory _$_PostRequest.fromJson(Map<String, dynamic> json) =>
      _$$_PostRequestFromJson(json);

  @override
  final String title;
  @override
  final String contents;
  @override
  final String img;

  @override
  String toString() {
    return 'PostRequest(title: $title, contents: $contents, img: $img)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _PostRequest &&
            (identical(other.title, title) || other.title == title) &&
            (identical(other.contents, contents) ||
                other.contents == contents) &&
            (identical(other.img, img) || other.img == img));
  }

  @override
  int get hashCode => Object.hash(runtimeType, title, contents, img);

  @JsonKey(ignore: true)
  @override
  _$PostRequestCopyWith<_PostRequest> get copyWith =>
      __$PostRequestCopyWithImpl<_PostRequest>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_PostRequestToJson(this);
  }
}

abstract class _PostRequest implements PostRequest {
  factory _PostRequest(
      {required String title,
      required String contents,
      required String img}) = _$_PostRequest;

  factory _PostRequest.fromJson(Map<String, dynamic> json) =
      _$_PostRequest.fromJson;

  @override
  String get title;
  @override
  String get contents;
  @override
  String get img;
  @override
  @JsonKey(ignore: true)
  _$PostRequestCopyWith<_PostRequest> get copyWith =>
      throw _privateConstructorUsedError;
}
