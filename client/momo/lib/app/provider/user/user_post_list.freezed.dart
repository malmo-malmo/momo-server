// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'user_post_list.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$UserPostListTearOff {
  const _$UserPostListTearOff();

  _UserPostList call(
      {required List<ManagementPostResponse> posts,
      required bool hasNext,
      required int nextPage}) {
    return _UserPostList(
      posts: posts,
      hasNext: hasNext,
      nextPage: nextPage,
    );
  }
}

/// @nodoc
const $UserPostList = _$UserPostListTearOff();

/// @nodoc
mixin _$UserPostList {
  List<ManagementPostResponse> get posts => throw _privateConstructorUsedError;
  bool get hasNext => throw _privateConstructorUsedError;
  int get nextPage => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $UserPostListCopyWith<UserPostList> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $UserPostListCopyWith<$Res> {
  factory $UserPostListCopyWith(
          UserPostList value, $Res Function(UserPostList) then) =
      _$UserPostListCopyWithImpl<$Res>;
  $Res call({List<ManagementPostResponse> posts, bool hasNext, int nextPage});
}

/// @nodoc
class _$UserPostListCopyWithImpl<$Res> implements $UserPostListCopyWith<$Res> {
  _$UserPostListCopyWithImpl(this._value, this._then);

  final UserPostList _value;
  // ignore: unused_field
  final $Res Function(UserPostList) _then;

  @override
  $Res call({
    Object? posts = freezed,
    Object? hasNext = freezed,
    Object? nextPage = freezed,
  }) {
    return _then(_value.copyWith(
      posts: posts == freezed
          ? _value.posts
          : posts // ignore: cast_nullable_to_non_nullable
              as List<ManagementPostResponse>,
      hasNext: hasNext == freezed
          ? _value.hasNext
          : hasNext // ignore: cast_nullable_to_non_nullable
              as bool,
      nextPage: nextPage == freezed
          ? _value.nextPage
          : nextPage // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
abstract class _$UserPostListCopyWith<$Res>
    implements $UserPostListCopyWith<$Res> {
  factory _$UserPostListCopyWith(
          _UserPostList value, $Res Function(_UserPostList) then) =
      __$UserPostListCopyWithImpl<$Res>;
  @override
  $Res call({List<ManagementPostResponse> posts, bool hasNext, int nextPage});
}

/// @nodoc
class __$UserPostListCopyWithImpl<$Res> extends _$UserPostListCopyWithImpl<$Res>
    implements _$UserPostListCopyWith<$Res> {
  __$UserPostListCopyWithImpl(
      _UserPostList _value, $Res Function(_UserPostList) _then)
      : super(_value, (v) => _then(v as _UserPostList));

  @override
  _UserPostList get _value => super._value as _UserPostList;

  @override
  $Res call({
    Object? posts = freezed,
    Object? hasNext = freezed,
    Object? nextPage = freezed,
  }) {
    return _then(_UserPostList(
      posts: posts == freezed
          ? _value.posts
          : posts // ignore: cast_nullable_to_non_nullable
              as List<ManagementPostResponse>,
      hasNext: hasNext == freezed
          ? _value.hasNext
          : hasNext // ignore: cast_nullable_to_non_nullable
              as bool,
      nextPage: nextPage == freezed
          ? _value.nextPage
          : nextPage // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc

class _$_UserPostList implements _UserPostList {
  _$_UserPostList(
      {required this.posts, required this.hasNext, required this.nextPage});

  @override
  final List<ManagementPostResponse> posts;
  @override
  final bool hasNext;
  @override
  final int nextPage;

  @override
  String toString() {
    return 'UserPostList(posts: $posts, hasNext: $hasNext, nextPage: $nextPage)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _UserPostList &&
            const DeepCollectionEquality().equals(other.posts, posts) &&
            (identical(other.hasNext, hasNext) || other.hasNext == hasNext) &&
            (identical(other.nextPage, nextPage) ||
                other.nextPage == nextPage));
  }

  @override
  int get hashCode => Object.hash(runtimeType,
      const DeepCollectionEquality().hash(posts), hasNext, nextPage);

  @JsonKey(ignore: true)
  @override
  _$UserPostListCopyWith<_UserPostList> get copyWith =>
      __$UserPostListCopyWithImpl<_UserPostList>(this, _$identity);
}

abstract class _UserPostList implements UserPostList {
  factory _UserPostList(
      {required List<ManagementPostResponse> posts,
      required bool hasNext,
      required int nextPage}) = _$_UserPostList;

  @override
  List<ManagementPostResponse> get posts;
  @override
  bool get hasNext;
  @override
  int get nextPage;
  @override
  @JsonKey(ignore: true)
  _$UserPostListCopyWith<_UserPostList> get copyWith =>
      throw _privateConstructorUsedError;
}
