// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'comment.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

Comment _$CommentFromJson(Map<String, dynamic> json) {
  return _Comment.fromJson(json);
}

/// @nodoc
class _$CommentTearOff {
  const _$CommentTearOff();

  _Comment call(
      {required int id,
      required String authorNickname,
      required String contents,
      required String authorImage,
      required String createdDate}) {
    return _Comment(
      id: id,
      authorNickname: authorNickname,
      contents: contents,
      authorImage: authorImage,
      createdDate: createdDate,
    );
  }

  Comment fromJson(Map<String, Object?> json) {
    return Comment.fromJson(json);
  }
}

/// @nodoc
const $Comment = _$CommentTearOff();

/// @nodoc
mixin _$Comment {
  int get id => throw _privateConstructorUsedError;
  String get authorNickname => throw _privateConstructorUsedError;
  String get contents => throw _privateConstructorUsedError;
  String get authorImage => throw _privateConstructorUsedError;
  String get createdDate => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $CommentCopyWith<Comment> get copyWith => throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $CommentCopyWith<$Res> {
  factory $CommentCopyWith(Comment value, $Res Function(Comment) then) =
      _$CommentCopyWithImpl<$Res>;
  $Res call(
      {int id,
      String authorNickname,
      String contents,
      String authorImage,
      String createdDate});
}

/// @nodoc
class _$CommentCopyWithImpl<$Res> implements $CommentCopyWith<$Res> {
  _$CommentCopyWithImpl(this._value, this._then);

  final Comment _value;
  // ignore: unused_field
  final $Res Function(Comment) _then;

  @override
  $Res call({
    Object? id = freezed,
    Object? authorNickname = freezed,
    Object? contents = freezed,
    Object? authorImage = freezed,
    Object? createdDate = freezed,
  }) {
    return _then(_value.copyWith(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      authorNickname: authorNickname == freezed
          ? _value.authorNickname
          : authorNickname // ignore: cast_nullable_to_non_nullable
              as String,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
              as String,
      authorImage: authorImage == freezed
          ? _value.authorImage
          : authorImage // ignore: cast_nullable_to_non_nullable
              as String,
      createdDate: createdDate == freezed
          ? _value.createdDate
          : createdDate // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
abstract class _$CommentCopyWith<$Res> implements $CommentCopyWith<$Res> {
  factory _$CommentCopyWith(_Comment value, $Res Function(_Comment) then) =
      __$CommentCopyWithImpl<$Res>;
  @override
  $Res call(
      {int id,
      String authorNickname,
      String contents,
      String authorImage,
      String createdDate});
}

/// @nodoc
class __$CommentCopyWithImpl<$Res> extends _$CommentCopyWithImpl<$Res>
    implements _$CommentCopyWith<$Res> {
  __$CommentCopyWithImpl(_Comment _value, $Res Function(_Comment) _then)
      : super(_value, (v) => _then(v as _Comment));

  @override
  _Comment get _value => super._value as _Comment;

  @override
  $Res call({
    Object? id = freezed,
    Object? authorNickname = freezed,
    Object? contents = freezed,
    Object? authorImage = freezed,
    Object? createdDate = freezed,
  }) {
    return _then(_Comment(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      authorNickname: authorNickname == freezed
          ? _value.authorNickname
          : authorNickname // ignore: cast_nullable_to_non_nullable
              as String,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
              as String,
      authorImage: authorImage == freezed
          ? _value.authorImage
          : authorImage // ignore: cast_nullable_to_non_nullable
              as String,
      createdDate: createdDate == freezed
          ? _value.createdDate
          : createdDate // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_Comment implements _Comment {
  _$_Comment(
      {required this.id,
      required this.authorNickname,
      required this.contents,
      required this.authorImage,
      required this.createdDate});

  factory _$_Comment.fromJson(Map<String, dynamic> json) =>
      _$$_CommentFromJson(json);

  @override
  final int id;
  @override
  final String authorNickname;
  @override
  final String contents;
  @override
  final String authorImage;
  @override
  final String createdDate;

  @override
  String toString() {
    return 'Comment(id: $id, authorNickname: $authorNickname, contents: $contents, authorImage: $authorImage, createdDate: $createdDate)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _Comment &&
            (identical(other.id, id) || other.id == id) &&
            (identical(other.authorNickname, authorNickname) ||
                other.authorNickname == authorNickname) &&
            (identical(other.contents, contents) ||
                other.contents == contents) &&
            (identical(other.authorImage, authorImage) ||
                other.authorImage == authorImage) &&
            (identical(other.createdDate, createdDate) ||
                other.createdDate == createdDate));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType, id, authorNickname, contents, authorImage, createdDate);

  @JsonKey(ignore: true)
  @override
  _$CommentCopyWith<_Comment> get copyWith =>
      __$CommentCopyWithImpl<_Comment>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_CommentToJson(this);
  }
}

abstract class _Comment implements Comment {
  factory _Comment(
      {required int id,
      required String authorNickname,
      required String contents,
      required String authorImage,
      required String createdDate}) = _$_Comment;

  factory _Comment.fromJson(Map<String, dynamic> json) = _$_Comment.fromJson;

  @override
  int get id;
  @override
  String get authorNickname;
  @override
  String get contents;
  @override
  String get authorImage;
  @override
  String get createdDate;
  @override
  @JsonKey(ignore: true)
  _$CommentCopyWith<_Comment> get copyWith =>
      throw _privateConstructorUsedError;
}
