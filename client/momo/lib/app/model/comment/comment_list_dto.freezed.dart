// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'comment_list_dto.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$CommentListDtoTearOff {
  const _$CommentListDtoTearOff();

  _CommentListDto call(
      {required List<Comment> comments,
      required int nextPage,
      required bool hasNext}) {
    return _CommentListDto(
      comments: comments,
      nextPage: nextPage,
      hasNext: hasNext,
    );
  }
}

/// @nodoc
const $CommentListDto = _$CommentListDtoTearOff();

/// @nodoc
mixin _$CommentListDto {
  List<Comment> get comments => throw _privateConstructorUsedError;
  int get nextPage => throw _privateConstructorUsedError;
  bool get hasNext => throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $CommentListDtoCopyWith<CommentListDto> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $CommentListDtoCopyWith<$Res> {
  factory $CommentListDtoCopyWith(
          CommentListDto value, $Res Function(CommentListDto) then) =
      _$CommentListDtoCopyWithImpl<$Res>;
  $Res call({List<Comment> comments, int nextPage, bool hasNext});
}

/// @nodoc
class _$CommentListDtoCopyWithImpl<$Res>
    implements $CommentListDtoCopyWith<$Res> {
  _$CommentListDtoCopyWithImpl(this._value, this._then);

  final CommentListDto _value;
  // ignore: unused_field
  final $Res Function(CommentListDto) _then;

  @override
  $Res call({
    Object? comments = freezed,
    Object? nextPage = freezed,
    Object? hasNext = freezed,
  }) {
    return _then(_value.copyWith(
      comments: comments == freezed
          ? _value.comments
          : comments // ignore: cast_nullable_to_non_nullable
              as List<Comment>,
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
abstract class _$CommentListDtoCopyWith<$Res>
    implements $CommentListDtoCopyWith<$Res> {
  factory _$CommentListDtoCopyWith(
          _CommentListDto value, $Res Function(_CommentListDto) then) =
      __$CommentListDtoCopyWithImpl<$Res>;
  @override
  $Res call({List<Comment> comments, int nextPage, bool hasNext});
}

/// @nodoc
class __$CommentListDtoCopyWithImpl<$Res>
    extends _$CommentListDtoCopyWithImpl<$Res>
    implements _$CommentListDtoCopyWith<$Res> {
  __$CommentListDtoCopyWithImpl(
      _CommentListDto _value, $Res Function(_CommentListDto) _then)
      : super(_value, (v) => _then(v as _CommentListDto));

  @override
  _CommentListDto get _value => super._value as _CommentListDto;

  @override
  $Res call({
    Object? comments = freezed,
    Object? nextPage = freezed,
    Object? hasNext = freezed,
  }) {
    return _then(_CommentListDto(
      comments: comments == freezed
          ? _value.comments
          : comments // ignore: cast_nullable_to_non_nullable
              as List<Comment>,
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

class _$_CommentListDto implements _CommentListDto {
  _$_CommentListDto(
      {required this.comments, required this.nextPage, required this.hasNext});

  @override
  final List<Comment> comments;
  @override
  final int nextPage;
  @override
  final bool hasNext;

  @override
  String toString() {
    return 'CommentListDto(comments: $comments, nextPage: $nextPage, hasNext: $hasNext)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _CommentListDto &&
            const DeepCollectionEquality().equals(other.comments, comments) &&
            (identical(other.nextPage, nextPage) ||
                other.nextPage == nextPage) &&
            (identical(other.hasNext, hasNext) || other.hasNext == hasNext));
  }

  @override
  int get hashCode => Object.hash(runtimeType,
      const DeepCollectionEquality().hash(comments), nextPage, hasNext);

  @JsonKey(ignore: true)
  @override
  _$CommentListDtoCopyWith<_CommentListDto> get copyWith =>
      __$CommentListDtoCopyWithImpl<_CommentListDto>(this, _$identity);
}

abstract class _CommentListDto implements CommentListDto {
  factory _CommentListDto(
      {required List<Comment> comments,
      required int nextPage,
      required bool hasNext}) = _$_CommentListDto;

  @override
  List<Comment> get comments;
  @override
  int get nextPage;
  @override
  bool get hasNext;
  @override
  @JsonKey(ignore: true)
  _$CommentListDtoCopyWith<_CommentListDto> get copyWith =>
      throw _privateConstructorUsedError;
}
