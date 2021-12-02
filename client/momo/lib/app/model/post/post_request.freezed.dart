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
      {required int groupId,
      required String title,
      required String contents,
      required List<String> imageUrls,
      required String postType}) {
    return _PostRequest(
      groupId: groupId,
      title: title,
      contents: contents,
      imageUrls: imageUrls,
      postType: postType,
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
  int get groupId => throw _privateConstructorUsedError;
  String get title => throw _privateConstructorUsedError;
  String get contents => throw _privateConstructorUsedError;
  List<String> get imageUrls => throw _privateConstructorUsedError;
  String get postType => throw _privateConstructorUsedError;

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
  $Res call(
      {int groupId,
      String title,
      String contents,
      List<String> imageUrls,
      String postType});
}

/// @nodoc
class _$PostRequestCopyWithImpl<$Res> implements $PostRequestCopyWith<$Res> {
  _$PostRequestCopyWithImpl(this._value, this._then);

  final PostRequest _value;
  // ignore: unused_field
  final $Res Function(PostRequest) _then;

  @override
  $Res call({
    Object? groupId = freezed,
    Object? title = freezed,
    Object? contents = freezed,
    Object? imageUrls = freezed,
    Object? postType = freezed,
  }) {
    return _then(_value.copyWith(
      groupId: groupId == freezed
          ? _value.groupId
          : groupId // ignore: cast_nullable_to_non_nullable
              as int,
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
              as String,
      imageUrls: imageUrls == freezed
          ? _value.imageUrls
          : imageUrls // ignore: cast_nullable_to_non_nullable
              as List<String>,
      postType: postType == freezed
          ? _value.postType
          : postType // ignore: cast_nullable_to_non_nullable
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
  $Res call(
      {int groupId,
      String title,
      String contents,
      List<String> imageUrls,
      String postType});
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
    Object? groupId = freezed,
    Object? title = freezed,
    Object? contents = freezed,
    Object? imageUrls = freezed,
    Object? postType = freezed,
  }) {
    return _then(_PostRequest(
      groupId: groupId == freezed
          ? _value.groupId
          : groupId // ignore: cast_nullable_to_non_nullable
              as int,
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
              as String,
      imageUrls: imageUrls == freezed
          ? _value.imageUrls
          : imageUrls // ignore: cast_nullable_to_non_nullable
              as List<String>,
      postType: postType == freezed
          ? _value.postType
          : postType // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_PostRequest implements _PostRequest {
  _$_PostRequest(
      {required this.groupId,
      required this.title,
      required this.contents,
      required this.imageUrls,
      required this.postType});

  factory _$_PostRequest.fromJson(Map<String, dynamic> json) =>
      _$$_PostRequestFromJson(json);

  @override
  final int groupId;
  @override
  final String title;
  @override
  final String contents;
  @override
  final List<String> imageUrls;
  @override
  final String postType;

  @override
  String toString() {
    return 'PostRequest(groupId: $groupId, title: $title, contents: $contents, imageUrls: $imageUrls, postType: $postType)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _PostRequest &&
            (identical(other.groupId, groupId) || other.groupId == groupId) &&
            (identical(other.title, title) || other.title == title) &&
            (identical(other.contents, contents) ||
                other.contents == contents) &&
            const DeepCollectionEquality().equals(other.imageUrls, imageUrls) &&
            (identical(other.postType, postType) ||
                other.postType == postType));
  }

  @override
  int get hashCode => Object.hash(runtimeType, groupId, title, contents,
      const DeepCollectionEquality().hash(imageUrls), postType);

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
      {required int groupId,
      required String title,
      required String contents,
      required List<String> imageUrls,
      required String postType}) = _$_PostRequest;

  factory _PostRequest.fromJson(Map<String, dynamic> json) =
      _$_PostRequest.fromJson;

  @override
  int get groupId;
  @override
  String get title;
  @override
  String get contents;
  @override
  List<String> get imageUrls;
  @override
  String get postType;
  @override
  @JsonKey(ignore: true)
  _$PostRequestCopyWith<_PostRequest> get copyWith =>
      throw _privateConstructorUsedError;
}
