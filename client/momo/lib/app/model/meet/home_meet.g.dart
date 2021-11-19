// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'home_meet.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_HomeMeet _$$_HomeMeetFromJson(Map<String, dynamic> json) => _$_HomeMeet(
      id: json['id'] as int,
      title: json['title'] as String,
      onOff: json['onOff'] as String,
      headNum: json['headNum'] as int,
      startDay: json['startDay'] as String,
      img: json['img'] as String,
    );

Map<String, dynamic> _$$_HomeMeetToJson(_$_HomeMeet instance) =>
    <String, dynamic>{
      'id': instance.id,
      'title': instance.title,
      'onOff': instance.onOff,
      'headNum': instance.headNum,
      'startDay': instance.startDay,
      'img': instance.img,
    };
