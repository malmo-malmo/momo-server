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

/// @nodoc
class _$PostRequestTearOff {
  const _$PostRequestTearOff();

  _PostRequest call(
      {required int groupId,
      required String title,
      required String contents,
      required List<String> images,
      required String typeName}) {
    return _PostRequest(
      groupId: groupId,
      title: title,
      contents: contents,
      images: images,
      typeName: typeName,
    );
  }
}

/// @nodoc
const $PostRequest = _$PostRequestTearOff();

/// @nodoc
mixin _$PostRequest {
  int get groupId => throw _privateConstructorUsedError;
  String get title => throw _privateConstructorUsedError;
  String get contents => throw _privateConstructorUsedError;
  List<String> get images => throw _privateConstructorUsedError;
  String get typeName => throw _privateConstructorUsedError;

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
      List<String> images,
      String typeName});
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
    Object? images = freezed,
    Object? typeName = freezed,
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
      images: images == freezed
          ? _value.images
          : images // ignore: cast_nullable_to_non_nullable
              as List<String>,
      typeName: typeName == freezed
          ? _value.typeName
          : typeName // ignore: cast_nullable_to_non_nullable
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
      List<String> images,
      String typeName});
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
    Object? images = freezed,
    Object? typeName = freezed,
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
      images: images == freezed
          ? _value.images
          : images // ignore: cast_nullable_to_non_nullable
              as List<String>,
      typeName: typeName == freezed
          ? _value.typeName
          : typeName // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc

class _$_PostRequest implements _PostRequest {
  _$_PostRequest(
      {required this.groupId,
      required this.title,
      required this.contents,
      required this.images,
      required this.typeName});

  @override
  final int groupId;
  @override
  final String title;
  @override
  final String contents;
  @override
  final List<String> images;
  @override
  final String typeName;

  @override
  String toString() {
    return 'PostRequest(groupId: $groupId, title: $title, contents: $contents, images: $images, typeName: $typeName)';
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
            const DeepCollectionEquality().equals(other.images, images) &&
            (identical(other.typeName, typeName) ||
                other.typeName == typeName));
  }

  @override
  int get hashCode => Object.hash(runtimeType, groupId, title, contents,
      const DeepCollectionEquality().hash(images), typeName);

  @JsonKey(ignore: true)
  @override
  _$PostRequestCopyWith<_PostRequest> get copyWith =>
      __$PostRequestCopyWithImpl<_PostRequest>(this, _$identity);
}

abstract class _PostRequest implements PostRequest {
  factory _PostRequest(
      {required int groupId,
      required String title,
      required String contents,
      required List<String> images,
      required String typeName}) = _$_PostRequest;

  @override
  int get groupId;
  @override
  String get title;
  @override
  String get contents;
  @override
  List<String> get images;
  @override
  String get typeName;
  @override
  @JsonKey(ignore: true)
  _$PostRequestCopyWith<_PostRequest> get copyWith =>
      throw _privateConstructorUsedError;
}
