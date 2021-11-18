import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/meet/new_meet.dart';

final newMeetCheckProvider = Provider.autoDispose<bool>((ref) {
  final newMeet = ref.watch(newMeetStateProvider);

  if (newMeet.meetName.isNotEmpty &&
      newMeet.category.isNotEmpty &&
      newMeet.school.isNotEmpty &&
      newMeet.img.isNotEmpty &&
      newMeet.contents.isNotEmpty &&
      newMeet.city.isNotEmpty &&
      newMeet.country.isNotEmpty &&
      newMeet.startDay.isNotEmpty &&
      newMeet.onOff.isNotEmpty &&
      newMeet.headNum != 0) {
    return true;
  }
  return false;
});

final newMeetProvider = Provider.autoDispose<NewMeet>((ref) {
  final newMeetState = ref.watch(newMeetStateProvider);
  return newMeetState;
});

final newMeetStateProvider =
    StateNotifierProvider.autoDispose<NewMeetState, NewMeet>(
        (ref) => NewMeetState());

class NewMeetState extends StateNotifier<NewMeet> {
  NewMeetState()
      : super(
          NewMeet(
            meetName: '',
            category: '',
            onOff: '',
            headNum: 0,
            startDay: '',
            school: '',
            city: '',
            country: '',
            contents: '',
            img: '',
          ),
        );

  void setMeetName(String name) => state = state.copyWith(meetName: name);

  void setCategory(String category) =>
      state = state.copyWith(category: category);

  void setOnOff(String onOff) => state = state.copyWith(onOff: onOff);

  void setHeadNum(String num) =>
      state = state.copyWith(headNum: int.parse(num));

  void setStartDay(String startDay) =>
      state = state.copyWith(startDay: startDay);

  void setContents(String contents) =>
      state = state.copyWith(contents: contents);

  void setSchool(String school) => state = state.copyWith(school: school);

  void setCity(String city) => state = state.copyWith(city: city);

  void setCountry(String country) => state = state.copyWith(country: country);

  void setImage(String img) => state = state.copyWith(img: img);
}
