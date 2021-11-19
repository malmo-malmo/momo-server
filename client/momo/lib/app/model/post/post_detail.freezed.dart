// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'post_detail.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

PostDetail _$PostDetailFromJson(Map<String, dynamic> json) {
  return _PostDetail.fromJson(json);
}

/// @nodoc
class _$PostDetailTearOff {
  const _$PostDetailTearOff();

  _PostDetail call(
      {required int id,
      required String name,
      required String title,
      required String contents,
      required String profile,
      required String img,
      required String date,
      required int comments}) {
    return _PostDetail(
      id: id,
      name: name,
      title: title,
      contents: contents,
      profile: profile,
      img: img,
      date: date,
      comments: comments,
    );
  }

  PostDetail fromJson(Map<String, Object?> json) {
    return PostDetail.fromJson(json);
  }
}

/// @nodoc
const $PostDetail = _$PostDetailTearOff();

/// @nodoc
mixin _$PostDetail {
  int get id => throw _privateConstructorUsedError;
  String get name => throw _privateConstructorUsedError;
  String get title => throw _privateConstructorUsedError;
  String get contents => throw _privateConstructorUsedError;
  String get profile => throw _privateConstructorUsedError;
  String get img => throw _privateConstructorUsedError;
  String get date => throw _privateConstructorUsedError;
  int get comments => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $PostDetailCopyWith<PostDetail> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $PostDetailCopyWith<$Res> {
  factory $PostDetailCopyWith(
          PostDetail value, $Res Function(PostDetail) then) =
      _$PostDetailCopyWithImpl<$Res>;
  $Res call(
      {int id,
      String name,
      String title,
      String contents,
      String profile,
      String img,
      String date,
      int comments});
}

/// @nodoc
class _$PostDetailCopyWithImpl<$Res> implements $PostDetailCopyWith<$Res> {
  _$PostDetailCopyWithImpl(this._value, this._then);

  final PostDetail _value;
  // ignore: unused_field
  final $Res Function(PostDetail) _then;

  @override
  $Res call({
    Object? id = freezed,
    Object? name = freezed,
    Object? title = freezed,
    Object? contents = freezed,
    Object? profile = freezed,
    Object? img = freezed,
    Object? date = freezed,
    Object? comments = freezed,
  }) {
    return _then(_value.copyWith(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
              as String,
      profile: profile == freezed
          ? _value.profile
          : profile // ignore: cast_nullable_to_non_nullable
              as String,
      img: img == freezed
          ? _value.img
          : img // ignore: cast_nullable_to_non_nullable
              as String,
      date: date == freezed
          ? _value.date
          : date // ignore: cast_nullable_to_non_nullable
              as String,
      comments: comments == freezed
          ? _value.comments
          : comments // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
abstract class _$PostDetailCopyWith<$Res> implements $PostDetailCopyWith<$Res> {
  factory _$PostDetailCopyWith(
          _PostDetail value, $Res Function(_PostDetail) then) =
      __$PostDetailCopyWithImpl<$Res>;
  @override
  $Res call(
      {int id,
      String name,
      String title,
      String contents,
      String profile,
      String img,
      String date,
      int comments});
}

/// @nodoc
class __$PostDetailCopyWithImpl<$Res> extends _$PostDetailCopyWithImpl<$Res>
    implements _$PostDetailCopyWith<$Res> {
  __$PostDetailCopyWithImpl(
      _PostDetail _value, $Res Function(_PostDetail) _then)
      : super(_value, (v) => _then(v as _PostDetail));

  @override
  _PostDetail get _value => super._value as _PostDetail;

  @override
  $Res call({
    Object? id = freezed,
    Object? name = freezed,
    Object? title = freezed,
    Object? contents = freezed,
    Object? profile = freezed,
    Object? img = freezed,
    Object? date = freezed,
    Object? comments = freezed,
  }) {
    return _then(_PostDetail(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
              as String,
      profile: profile == freezed
          ? _value.profile
          : profile // ignore: cast_nullable_to_non_nullable
              as String,
      img: img == freezed
          ? _value.img
          : img // ignore: cast_nullable_to_non_nullable
              as String,
      date: date == freezed
          ? _value.date
          : date // ignore: cast_nullable_to_non_nullable
              as String,
      comments: comments == freezed
          ? _value.comments
          : comments // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_PostDetail implements _PostDetail {
  _$_PostDetail(
      {required this.id,
      required this.name,
      required this.title,
      required this.contents,
      required this.profile,
      required this.img,
      required this.date,
      required this.comments});

  factory _$_PostDetail.fromJson(Map<String, dynamic> json) =>
      _$$_PostDetailFromJson(json);

  @override
  final int id;
  @override
  final String name;
  @override
  final String title;
  @override
  final String contents;
  @override
  final String profile;
  @override
  final String img;
  @override
  final String date;
  @override
  final int comments;

  @override
  String toString() {
    return 'PostDetail(id: $id, name: $name, title: $title, contents: $contents, profile: $profile, img: $img, date: $date, comments: $comments)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _PostDetail &&
            (identical(other.id, id) || other.id == id) &&
            (identical(other.name, name) || other.name == name) &&
            (identical(other.title, title) || other.title == title) &&
            (identical(other.contents, contents) ||
                other.contents == contents) &&
            (identical(other.profile, profile) || other.profile == profile) &&
            (identical(other.img, img) || other.img == img) &&
            (identical(other.date, date) || other.date == date) &&
            (identical(other.comments, comments) ||
                other.comments == comments));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType, id, name, title, contents, profile, img, date, comments);

  @JsonKey(ignore: true)
  @override
  _$PostDetailCopyWith<_PostDetail> get copyWith =>
      __$PostDetailCopyWithImpl<_PostDetail>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_PostDetailToJson(this);
  }
}

abstract class _PostDetail implements PostDetail {
  factory _PostDetail(
      {required int id,
      required String name,
      required String title,
      required String contents,
      required String profile,
      required String img,
      required String date,
      required int comments}) = _$_PostDetail;

  factory _PostDetail.fromJson(Map<String, dynamic> json) =
      _$_PostDetail.fromJson;

  @override
  int get id;
  @override
  String get name;
  @override
  String get title;
  @override
  String get contents;
  @override
  String get profile;
  @override
  String get img;
  @override
  String get date;
  @override
  int get comments;
  @override
  @JsonKey(ignore: true)
  _$PostDetailCopyWith<_PostDetail> get copyWith =>
      throw _privateConstructorUsedError;
}
