// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'new_meet.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_NewMeet _$$_NewMeetFromJson(Map<String, dynamic> json) => _$_NewMeet(
      meetName: json['meetName'] as String,
      category: json['category'] as String,
      onOff: json['onOff'] as String,
      headNum: json['headNum'] as int,
      startDay: json['startDay'] as String,
      school: json['school'] as String,
      city: json['city'] as String,
      country: json['country'] as String,
      contents: json['contents'] as String,
      img: json['img'] as String,
    );

Map<String, dynamic> _$$_NewMeetToJson(_$_NewMeet instance) =>
    <String, dynamic>{
      'meetName': instance.meetName,
      'category': instance.category,
      'onOff': instance.onOff,
      'headNum': instance.headNum,
      'startDay': instance.startDay,
      'school': instance.school,
      'city': instance.city,
      'country': instance.country,
      'contents': instance.contents,
      'img': instance.img,
    };
