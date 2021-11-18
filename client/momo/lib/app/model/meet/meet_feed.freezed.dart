// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'meet_feed.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

MeetFeed _$MeetFeedFromJson(Map<String, dynamic> json) {
  return _MeetFeed.fromJson(json);
}

/// @nodoc
class _$MeetFeedTearOff {
  const _$MeetFeedTearOff();

  _MeetFeed call(
      {required int id,
      required String userName,
      required String title,
      required String contents,
      required int comments}) {
    return _MeetFeed(
      id: id,
      userName: userName,
      title: title,
      contents: contents,
      comments: comments,
    );
  }

  MeetFeed fromJson(Map<String, Object?> json) {
    return MeetFeed.fromJson(json);
  }
}

/// @nodoc
const $MeetFeed = _$MeetFeedTearOff();

/// @nodoc
mixin _$MeetFeed {
  int get id => throw _privateConstructorUsedError;
  String get userName => throw _privateConstructorUsedError;
  String get title => throw _privateConstructorUsedError;
  String get contents => throw _privateConstructorUsedError;
  int get comments => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $MeetFeedCopyWith<MeetFeed> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $MeetFeedCopyWith<$Res> {
  factory $MeetFeedCopyWith(MeetFeed value, $Res Function(MeetFeed) then) =
      _$MeetFeedCopyWithImpl<$Res>;
  $Res call(
      {int id, String userName, String title, String contents, int comments});
}

/// @nodoc
class _$MeetFeedCopyWithImpl<$Res> implements $MeetFeedCopyWith<$Res> {
  _$MeetFeedCopyWithImpl(this._value, this._then);

  final MeetFeed _value;
  // ignore: unused_field
  final $Res Function(MeetFeed) _then;

  @override
  $Res call({
    Object? id = freezed,
    Object? userName = freezed,
    Object? title = freezed,
    Object? contents = freezed,
    Object? comments = freezed,
  }) {
    return _then(_value.copyWith(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      userName: userName == freezed
          ? _value.userName
          : userName // ignore: cast_nullable_to_non_nullable
              as String,
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
              as String,
      comments: comments == freezed
          ? _value.comments
          : comments // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
abstract class _$MeetFeedCopyWith<$Res> implements $MeetFeedCopyWith<$Res> {
  factory _$MeetFeedCopyWith(_MeetFeed value, $Res Function(_MeetFeed) then) =
      __$MeetFeedCopyWithImpl<$Res>;
  @override
  $Res call(
      {int id, String userName, String title, String contents, int comments});
}

/// @nodoc
class __$MeetFeedCopyWithImpl<$Res> extends _$MeetFeedCopyWithImpl<$Res>
    implements _$MeetFeedCopyWith<$Res> {
  __$MeetFeedCopyWithImpl(_MeetFeed _value, $Res Function(_MeetFeed) _then)
      : super(_value, (v) => _then(v as _MeetFeed));

  @override
  _MeetFeed get _value => super._value as _MeetFeed;

  @override
  $Res call({
    Object? id = freezed,
    Object? userName = freezed,
    Object? title = freezed,
    Object? contents = freezed,
    Object? comments = freezed,
  }) {
    return _then(_MeetFeed(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      userName: userName == freezed
          ? _value.userName
          : userName // ignore: cast_nullable_to_non_nullable
              as String,
      title: title == freezed
          ? _value.title
          : title // ignore: cast_nullable_to_non_nullable
              as String,
      contents: contents == freezed
          ? _value.contents
          : contents // ignore: cast_nullable_to_non_nullable
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
class _$_MeetFeed implements _MeetFeed {
  _$_MeetFeed(
      {required this.id,
      required this.userName,
      required this.title,
      required this.contents,
      required this.comments});

  factory _$_MeetFeed.fromJson(Map<String, dynamic> json) =>
      _$$_MeetFeedFromJson(json);

  @override
  final int id;
  @override
  final String userName;
  @override
  final String title;
  @override
  final String contents;
  @override
  final int comments;

  @override
  String toString() {
    return 'MeetFeed(id: $id, userName: $userName, title: $title, contents: $contents, comments: $comments)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _MeetFeed &&
            (identical(other.id, id) || other.id == id) &&
            (identical(other.userName, userName) ||
                other.userName == userName) &&
            (identical(other.title, title) || other.title == title) &&
            (identical(other.contents, contents) ||
                other.contents == contents) &&
            (identical(other.comments, comments) ||
                other.comments == comments));
  }

  @override
  int get hashCode =>
      Object.hash(runtimeType, id, userName, title, contents, comments);

  @JsonKey(ignore: true)
  @override
  _$MeetFeedCopyWith<_MeetFeed> get copyWith =>
      __$MeetFeedCopyWithImpl<_MeetFeed>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_MeetFeedToJson(this);
  }
}

abstract class _MeetFeed implements MeetFeed {
  factory _MeetFeed(
      {required int id,
      required String userName,
      required String title,
      required String contents,
      required int comments}) = _$_MeetFeed;

  factory _MeetFeed.fromJson(Map<String, dynamic> json) = _$_MeetFeed.fromJson;

  @override
  int get id;
  @override
  String get userName;
  @override
  String get title;
  @override
  String get contents;
  @override
  int get comments;
  @override
  @JsonKey(ignore: true)
  _$MeetFeedCopyWith<_MeetFeed> get copyWith =>
      throw _privateConstructorUsedError;
}
