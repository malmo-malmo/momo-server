// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'group_detail.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

GroupDetail _$GroupDetailFromJson(Map<String, dynamic> json) {
  return _GroupDetail.fromJson(json);
}

/// @nodoc
class _$GroupDetailTearOff {
  const _$GroupDetailTearOff();

  _GroupDetail call(
      {required int id,
      required int managerId,
      required String city,
      required String district,
      String? imageUrl,
      required String introduction,
      required String name,
      required bool offline,
      required bool participant,
      required int participantCnt,
      required String startDate,
      String? university,
      required bool end,
      required int recruitmentCnt}) {
    return _GroupDetail(
      id: id,
      managerId: managerId,
      city: city,
      district: district,
      imageUrl: imageUrl,
      introduction: introduction,
      name: name,
      offline: offline,
      participant: participant,
      participantCnt: participantCnt,
      startDate: startDate,
      university: university,
      end: end,
      recruitmentCnt: recruitmentCnt,
    );
  }

  GroupDetail fromJson(Map<String, Object?> json) {
    return GroupDetail.fromJson(json);
  }
}

/// @nodoc
const $GroupDetail = _$GroupDetailTearOff();

/// @nodoc
mixin _$GroupDetail {
  int get id => throw _privateConstructorUsedError;
  int get managerId => throw _privateConstructorUsedError;
  String get city => throw _privateConstructorUsedError;
  String get district => throw _privateConstructorUsedError;
  String? get imageUrl => throw _privateConstructorUsedError;
  String get introduction => throw _privateConstructorUsedError;
  String get name => throw _privateConstructorUsedError;
  bool get offline => throw _privateConstructorUsedError;
  bool get participant => throw _privateConstructorUsedError;
  int get participantCnt => throw _privateConstructorUsedError;
  String get startDate => throw _privateConstructorUsedError;
  String? get university => throw _privateConstructorUsedError;
  bool get end => throw _privateConstructorUsedError;
  int get recruitmentCnt => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $GroupDetailCopyWith<GroupDetail> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $GroupDetailCopyWith<$Res> {
  factory $GroupDetailCopyWith(
          GroupDetail value, $Res Function(GroupDetail) then) =
      _$GroupDetailCopyWithImpl<$Res>;
  $Res call(
      {int id,
      int managerId,
      String city,
      String district,
      String? imageUrl,
      String introduction,
      String name,
      bool offline,
      bool participant,
      int participantCnt,
      String startDate,
      String? university,
      bool end,
      int recruitmentCnt});
}

/// @nodoc
class _$GroupDetailCopyWithImpl<$Res> implements $GroupDetailCopyWith<$Res> {
  _$GroupDetailCopyWithImpl(this._value, this._then);

  final GroupDetail _value;
  // ignore: unused_field
  final $Res Function(GroupDetail) _then;

  @override
  $Res call({
    Object? id = freezed,
    Object? managerId = freezed,
    Object? city = freezed,
    Object? district = freezed,
    Object? imageUrl = freezed,
    Object? introduction = freezed,
    Object? name = freezed,
    Object? offline = freezed,
    Object? participant = freezed,
    Object? participantCnt = freezed,
    Object? startDate = freezed,
    Object? university = freezed,
    Object? end = freezed,
    Object? recruitmentCnt = freezed,
  }) {
    return _then(_value.copyWith(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      managerId: managerId == freezed
          ? _value.managerId
          : managerId // ignore: cast_nullable_to_non_nullable
              as int,
      city: city == freezed
          ? _value.city
          : city // ignore: cast_nullable_to_non_nullable
              as String,
      district: district == freezed
          ? _value.district
          : district // ignore: cast_nullable_to_non_nullable
              as String,
      imageUrl: imageUrl == freezed
          ? _value.imageUrl
          : imageUrl // ignore: cast_nullable_to_non_nullable
              as String?,
      introduction: introduction == freezed
          ? _value.introduction
          : introduction // ignore: cast_nullable_to_non_nullable
              as String,
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      offline: offline == freezed
          ? _value.offline
          : offline // ignore: cast_nullable_to_non_nullable
              as bool,
      participant: participant == freezed
          ? _value.participant
          : participant // ignore: cast_nullable_to_non_nullable
              as bool,
      participantCnt: participantCnt == freezed
          ? _value.participantCnt
          : participantCnt // ignore: cast_nullable_to_non_nullable
              as int,
      startDate: startDate == freezed
          ? _value.startDate
          : startDate // ignore: cast_nullable_to_non_nullable
              as String,
      university: university == freezed
          ? _value.university
          : university // ignore: cast_nullable_to_non_nullable
              as String?,
      end: end == freezed
          ? _value.end
          : end // ignore: cast_nullable_to_non_nullable
              as bool,
      recruitmentCnt: recruitmentCnt == freezed
          ? _value.recruitmentCnt
          : recruitmentCnt // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
abstract class _$GroupDetailCopyWith<$Res>
    implements $GroupDetailCopyWith<$Res> {
  factory _$GroupDetailCopyWith(
          _GroupDetail value, $Res Function(_GroupDetail) then) =
      __$GroupDetailCopyWithImpl<$Res>;
  @override
  $Res call(
      {int id,
      int managerId,
      String city,
      String district,
      String? imageUrl,
      String introduction,
      String name,
      bool offline,
      bool participant,
      int participantCnt,
      String startDate,
      String? university,
      bool end,
      int recruitmentCnt});
}

/// @nodoc
class __$GroupDetailCopyWithImpl<$Res> extends _$GroupDetailCopyWithImpl<$Res>
    implements _$GroupDetailCopyWith<$Res> {
  __$GroupDetailCopyWithImpl(
      _GroupDetail _value, $Res Function(_GroupDetail) _then)
      : super(_value, (v) => _then(v as _GroupDetail));

  @override
  _GroupDetail get _value => super._value as _GroupDetail;

  @override
  $Res call({
    Object? id = freezed,
    Object? managerId = freezed,
    Object? city = freezed,
    Object? district = freezed,
    Object? imageUrl = freezed,
    Object? introduction = freezed,
    Object? name = freezed,
    Object? offline = freezed,
    Object? participant = freezed,
    Object? participantCnt = freezed,
    Object? startDate = freezed,
    Object? university = freezed,
    Object? end = freezed,
    Object? recruitmentCnt = freezed,
  }) {
    return _then(_GroupDetail(
      id: id == freezed
          ? _value.id
          : id // ignore: cast_nullable_to_non_nullable
              as int,
      managerId: managerId == freezed
          ? _value.managerId
          : managerId // ignore: cast_nullable_to_non_nullable
              as int,
      city: city == freezed
          ? _value.city
          : city // ignore: cast_nullable_to_non_nullable
              as String,
      district: district == freezed
          ? _value.district
          : district // ignore: cast_nullable_to_non_nullable
              as String,
      imageUrl: imageUrl == freezed
          ? _value.imageUrl
          : imageUrl // ignore: cast_nullable_to_non_nullable
              as String?,
      introduction: introduction == freezed
          ? _value.introduction
          : introduction // ignore: cast_nullable_to_non_nullable
              as String,
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      offline: offline == freezed
          ? _value.offline
          : offline // ignore: cast_nullable_to_non_nullable
              as bool,
      participant: participant == freezed
          ? _value.participant
          : participant // ignore: cast_nullable_to_non_nullable
              as bool,
      participantCnt: participantCnt == freezed
          ? _value.participantCnt
          : participantCnt // ignore: cast_nullable_to_non_nullable
              as int,
      startDate: startDate == freezed
          ? _value.startDate
          : startDate // ignore: cast_nullable_to_non_nullable
              as String,
      university: university == freezed
          ? _value.university
          : university // ignore: cast_nullable_to_non_nullable
              as String?,
      end: end == freezed
          ? _value.end
          : end // ignore: cast_nullable_to_non_nullable
              as bool,
      recruitmentCnt: recruitmentCnt == freezed
          ? _value.recruitmentCnt
          : recruitmentCnt // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_GroupDetail implements _GroupDetail {
  _$_GroupDetail(
      {required this.id,
      required this.managerId,
      required this.city,
      required this.district,
      this.imageUrl,
      required this.introduction,
      required this.name,
      required this.offline,
      required this.participant,
      required this.participantCnt,
      required this.startDate,
      this.university,
      required this.end,
      required this.recruitmentCnt});

  factory _$_GroupDetail.fromJson(Map<String, dynamic> json) =>
      _$$_GroupDetailFromJson(json);

  @override
  final int id;
  @override
  final int managerId;
  @override
  final String city;
  @override
  final String district;
  @override
  final String? imageUrl;
  @override
  final String introduction;
  @override
  final String name;
  @override
  final bool offline;
  @override
  final bool participant;
  @override
  final int participantCnt;
  @override
  final String startDate;
  @override
  final String? university;
  @override
  final bool end;
  @override
  final int recruitmentCnt;

  @override
  String toString() {
    return 'GroupDetail(id: $id, managerId: $managerId, city: $city, district: $district, imageUrl: $imageUrl, introduction: $introduction, name: $name, offline: $offline, participant: $participant, participantCnt: $participantCnt, startDate: $startDate, university: $university, end: $end, recruitmentCnt: $recruitmentCnt)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _GroupDetail &&
            (identical(other.id, id) || other.id == id) &&
            (identical(other.managerId, managerId) ||
                other.managerId == managerId) &&
            (identical(other.city, city) || other.city == city) &&
            (identical(other.district, district) ||
                other.district == district) &&
            (identical(other.imageUrl, imageUrl) ||
                other.imageUrl == imageUrl) &&
            (identical(other.introduction, introduction) ||
                other.introduction == introduction) &&
            (identical(other.name, name) || other.name == name) &&
            (identical(other.offline, offline) || other.offline == offline) &&
            (identical(other.participant, participant) ||
                other.participant == participant) &&
            (identical(other.participantCnt, participantCnt) ||
                other.participantCnt == participantCnt) &&
            (identical(other.startDate, startDate) ||
                other.startDate == startDate) &&
            (identical(other.university, university) ||
                other.university == university) &&
            (identical(other.end, end) || other.end == end) &&
            (identical(other.recruitmentCnt, recruitmentCnt) ||
                other.recruitmentCnt == recruitmentCnt));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType,
      id,
      managerId,
      city,
      district,
      imageUrl,
      introduction,
      name,
      offline,
      participant,
      participantCnt,
      startDate,
      university,
      end,
      recruitmentCnt);

  @JsonKey(ignore: true)
  @override
  _$GroupDetailCopyWith<_GroupDetail> get copyWith =>
      __$GroupDetailCopyWithImpl<_GroupDetail>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_GroupDetailToJson(this);
  }
}

abstract class _GroupDetail implements GroupDetail {
  factory _GroupDetail(
      {required int id,
      required int managerId,
      required String city,
      required String district,
      String? imageUrl,
      required String introduction,
      required String name,
      required bool offline,
      required bool participant,
      required int participantCnt,
      required String startDate,
      String? university,
      required bool end,
      required int recruitmentCnt}) = _$_GroupDetail;

  factory _GroupDetail.fromJson(Map<String, dynamic> json) =
      _$_GroupDetail.fromJson;

  @override
  int get id;
  @override
  int get managerId;
  @override
  String get city;
  @override
  String get district;
  @override
  String? get imageUrl;
  @override
  String get introduction;
  @override
  String get name;
  @override
  bool get offline;
  @override
  bool get participant;
  @override
  int get participantCnt;
  @override
  String get startDate;
  @override
  String? get university;
  @override
  bool get end;
  @override
  int get recruitmentCnt;
  @override
  @JsonKey(ignore: true)
  _$GroupDetailCopyWith<_GroupDetail> get copyWith =>
      throw _privateConstructorUsedError;
}
