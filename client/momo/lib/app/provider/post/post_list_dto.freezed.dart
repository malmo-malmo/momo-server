// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'post_list_dto.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$PostListDtoTearOff {
  const _$PostListDtoTearOff();

  _PostListDto call(
      {required List<Post> posts,
      required int nextPage,
      required bool hasNext}) {
    return _PostListDto(
      posts: posts,
      nextPage: nextPage,
      hasNext: hasNext,
    );
  }
}

/// @nodoc
const $PostListDto = _$PostListDtoTearOff();

/// @nodoc
mixin _$PostListDto {
  List<Post> get posts => throw _privateConstructorUsedError;
  int get nextPage => throw _privateConstructorUsedError;
  bool get hasNext => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $PostListDtoCopyWith<PostListDto> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $PostListDtoCopyWith<$Res> {
  factory $PostListDtoCopyWith(
          PostListDto value, $Res Function(PostListDto) then) =
      _$PostListDtoCopyWithImpl<$Res>;
  $Res call({List<Post> posts, int nextPage, bool hasNext});
}

/// @nodoc
class _$PostListDtoCopyWithImpl<$Res> implements $PostListDtoCopyWith<$Res> {
  _$PostListDtoCopyWithImpl(this._value, this._then);

  final PostListDto _value;
  // ignore: unused_field
  final $Res Function(PostListDto) _then;

  @override
  $Res call({
    Object? posts = freezed,
    Object? nextPage = freezed,
    Object? hasNext = freezed,
  }) {
    return _then(_value.copyWith(
      posts: posts == freezed
          ? _value.posts
          : posts // ignore: cast_nullable_to_non_nullable
              as List<Post>,
      nextPage: nextPage == freezed
          ? _value.nextPage
          : nextPage // ignore: cast_nullable_to_non_nullable
              as int,
      hasNext: hasNext == freezed
          ? _value.hasNext
          : hasNext // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc
abstract class _$PostListDtoCopyWith<$Res>
    implements $PostListDtoCopyWith<$Res> {
  factory _$PostListDtoCopyWith(
          _PostListDto value, $Res Function(_PostListDto) then) =
      __$PostListDtoCopyWithImpl<$Res>;
  @override
  $Res call({List<Post> posts, int nextPage, bool hasNext});
}

/// @nodoc
class __$PostListDtoCopyWithImpl<$Res> extends _$PostListDtoCopyWithImpl<$Res>
    implements _$PostListDtoCopyWith<$Res> {
  __$PostListDtoCopyWithImpl(
      _PostListDto _value, $Res Function(_PostListDto) _then)
      : super(_value, (v) => _then(v as _PostListDto));

  @override
  _PostListDto get _value => super._value as _PostListDto;

  @override
  $Res call({
    Object? posts = freezed,
    Object? nextPage = freezed,
    Object? hasNext = freezed,
  }) {
    return _then(_PostListDto(
      posts: posts == freezed
          ? _value.posts
          : posts // ignore: cast_nullable_to_non_nullable
              as List<Post>,
      nextPage: nextPage == freezed
          ? _value.nextPage
          : nextPage // ignore: cast_nullable_to_non_nullable
              as int,
      hasNext: hasNext == freezed
          ? _value.hasNext
          : hasNext // ignore: cast_nullable_to_non_nullable
              as bool,
    ));
  }
}

/// @nodoc

class _$_PostListDto implements _PostListDto {
  _$_PostListDto(
      {required this.posts, required this.nextPage, required this.hasNext});

  @override
  final List<Post> posts;
  @override
  final int nextPage;
  @override
  final bool hasNext;

  @override
  String toString() {
    return 'PostListDto(posts: $posts, nextPage: $nextPage, hasNext: $hasNext)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _PostListDto &&
            const DeepCollectionEquality().equals(other.posts, posts) &&
            (identical(other.nextPage, nextPage) ||
                other.nextPage == nextPage) &&
            (identical(other.hasNext, hasNext) || other.hasNext == hasNext));
  }

  @override
  int get hashCode => Object.hash(runtimeType,
      const DeepCollectionEquality().hash(posts), nextPage, hasNext);

  @JsonKey(ignore: true)
  @override
  _$PostListDtoCopyWith<_PostListDto> get copyWith =>
      __$PostListDtoCopyWithImpl<_PostListDto>(this, _$identity);
}

abstract class _PostListDto implements PostListDto {
  factory _PostListDto(
      {required List<Post> posts,
      required int nextPage,
      required bool hasNext}) = _$_PostListDto;

  @override
  List<Post> get posts;
  @override
  int get nextPage;
  @override
  bool get hasNext;
  @override
  @JsonKey(ignore: true)
  _$PostListDtoCopyWith<_PostListDto> get copyWith =>
      throw _privateConstructorUsedError;
}
