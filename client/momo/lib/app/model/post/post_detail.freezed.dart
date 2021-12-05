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
      required int authorId,
      required String authorNickname,
      required String title,
      required String contents,
      String? authorImage,
      required List<String> imageUrls,
      required String createdDate}) {
    return _PostDetail(
      id: id,
      authorId: authorId,
      authorNickname: authorNickname,
      title: title,
      contents: contents,
      authorImage: authorImage,
      imageUrls: imageUrls,
      createdDate: createdDate,
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
  int get authorId => throw _privateConstructorUsedError;
  String get authorNickname => throw _privateConstructorUsedError;
  String get title => throw _privateConstructorUsedError;
  String get contents => throw _privateConstructorUsedError;
  String? get authorImage => throw _privateConstructorUsedError;
  List<String> get imageUrls => throw _privateConstructorUsedError;
  String get createdDate => throw _privateConstructorUsedError;

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
      int authorId,
      String authorNickname,
      String title,
      String contents,
      String? authorImage,
      List<String> imageUrls,
      String createdDate});
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
    Object? authorId = freezed,
    Object? authorNickname = freezed,
    Object? title = freezed,
    Object? contents = freezed,
    Object? authorImage = freezed,
    Object? imageUrls = freezed,
    Object? createdDate = freezed,
  }) {
    return _then(_value.copyWith(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      authorId: authorId == freezed
          ? _value.authorId
          : authorId // ignore: cast_nullable_to_non_nullable
              as int,
      authorNickname: authorNickname == freezed
          ? _value.authorNickname
          : authorNickname // ignore: cast_nullable_to_non_nullable
              as String,
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
              as String,
      authorImage: authorImage == freezed
          ? _value.authorImage
          : authorImage // ignore: cast_nullable_to_non_nullable
              as String?,
      imageUrls: imageUrls == freezed
          ? _value.imageUrls
          : imageUrls // ignore: cast_nullable_to_non_nullable
              as List<String>,
      createdDate: createdDate == freezed
          ? _value.createdDate
          : createdDate // ignore: cast_nullable_to_non_nullable
              as String,
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
      int authorId,
      String authorNickname,
      String title,
      String contents,
      String? authorImage,
      List<String> imageUrls,
      String createdDate});
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
    Object? authorId = freezed,
    Object? authorNickname = freezed,
    Object? title = freezed,
    Object? contents = freezed,
    Object? authorImage = freezed,
    Object? imageUrls = freezed,
    Object? createdDate = freezed,
  }) {
    return _then(_PostDetail(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      authorId: authorId == freezed
          ? _value.authorId
          : authorId // ignore: cast_nullable_to_non_nullable
              as int,
      authorNickname: authorNickname == freezed
          ? _value.authorNickname
          : authorNickname // ignore: cast_nullable_to_non_nullable
              as String,
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
              as String,
      authorImage: authorImage == freezed
          ? _value.authorImage
          : authorImage // ignore: cast_nullable_to_non_nullable
              as String?,
      imageUrls: imageUrls == freezed
          ? _value.imageUrls
          : imageUrls // ignore: cast_nullable_to_non_nullable
              as List<String>,
      createdDate: createdDate == freezed
          ? _value.createdDate
          : createdDate // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_PostDetail implements _PostDetail {
  _$_PostDetail(
      {required this.id,
      required this.authorId,
      required this.authorNickname,
      required this.title,
      required this.contents,
      this.authorImage,
      required this.imageUrls,
      required this.createdDate});

  factory _$_PostDetail.fromJson(Map<String, dynamic> json) =>
      _$$_PostDetailFromJson(json);

  @override
  final int id;
  @override
  final int authorId;
  @override
  final String authorNickname;
  @override
  final String title;
  @override
  final String contents;
  @override
  final String? authorImage;
  @override
  final List<String> imageUrls;
  @override
  final String createdDate;

  @override
  String toString() {
    return 'PostDetail(id: $id, authorId: $authorId, authorNickname: $authorNickname, title: $title, contents: $contents, authorImage: $authorImage, imageUrls: $imageUrls, createdDate: $createdDate)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _PostDetail &&
            (identical(other.id, id) || other.id == id) &&
            (identical(other.authorId, authorId) ||
                other.authorId == authorId) &&
            (identical(other.authorNickname, authorNickname) ||
                other.authorNickname == authorNickname) &&
            (identical(other.title, title) || other.title == title) &&
            (identical(other.contents, contents) ||
                other.contents == contents) &&
            (identical(other.authorImage, authorImage) ||
                other.authorImage == authorImage) &&
            const DeepCollectionEquality().equals(other.imageUrls, imageUrls) &&
            (identical(other.createdDate, createdDate) ||
                other.createdDate == createdDate));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType,
      id,
      authorId,
      authorNickname,
      title,
      contents,
      authorImage,
      const DeepCollectionEquality().hash(imageUrls),
      createdDate);

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
      required int authorId,
      required String authorNickname,
      required String title,
      required String contents,
      String? authorImage,
      required List<String> imageUrls,
      required String createdDate}) = _$_PostDetail;

  factory _PostDetail.fromJson(Map<String, dynamic> json) =
      _$_PostDetail.fromJson;

  @override
  int get id;
  @override
  int get authorId;
  @override
  String get authorNickname;
  @override
  String get title;
  @override
  String get contents;
  @override
  String? get authorImage;
  @override
  List<String> get imageUrls;
  @override
  String get createdDate;
  @override
  @JsonKey(ignore: true)
  _$PostDetailCopyWith<_PostDetail> get copyWith =>
      throw _privateConstructorUsedError;
}
