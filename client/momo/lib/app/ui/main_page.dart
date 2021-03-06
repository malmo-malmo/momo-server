import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:momo/app/provider/bottom_index_provider.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/calendar/calendar_page.dart';
import 'package:momo/app/ui/home/home_page.dart';
import 'package:momo/app/ui/my_meet/my_meet_page.dart';
import 'package:momo/app/ui/mypage/mypage.dart';
import 'package:momo/app/ui/search/search_page.dart';

import 'dart:developer';

class MainPage extends ConsumerWidget {
  MainPage({Key? key}) : super(key: key);

  DateTime? backbuttonpressedTime;

  final _pages = const [
    HomePage(),
    SearchPage(),
    CalendarPage(),
    MyMeetPage(),
    Mypage(),
  ];

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final index = ref.watch(bottomIndexProvider);

    return WillPopScope(
      onWillPop: () async {
        DateTime currentTime = DateTime.now();

        bool backButton = backbuttonpressedTime == null ||
            currentTime.difference(backbuttonpressedTime!) >
                const Duration(seconds: 1);

        if (backButton) {
          backbuttonpressedTime = currentTime;
          Fluttertoast.showToast(
            msg: '한번 더 뒤로가기 버튼을 누르면 종료됩니다',
            backgroundColor: MomoColor.main,
            textColor: MomoColor.white,
            fontSize: 16,
          );
          return Future.value(false);
        }
        return Future.value(true);
      },
      child: SafeArea(
        child: Scaffold(
          backgroundColor: MomoColor.scaffoldBackground,
          body: _pages[index],
          extendBodyBehindAppBar: true,
          bottomNavigationBar: Container(
            padding: const EdgeInsets.all(3),
            height: 64,
            decoration: const BoxDecoration(
              color: MomoColor.white,
              border: Border(
                top: BorderSide(
                  color: Color(0xffe6e6e6),
                  width: 1,
                ),
              ),
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                _bottomIcon(
                  index: 0,
                  title: '홈',
                  selImg: 'assets/icon/tab/tabicon_homepurple_32.svg',
                  unSelImg: 'assets/icon/tab/tabicon_homegray_32.svg',
                ),
                _bottomIcon(
                  index: 1,
                  title: '검색',
                  selImg: 'assets/icon/tab/tabicon_searchpurple_32.svg',
                  unSelImg: 'assets/icon/tab/tabicon_searchgray_32.svg',
                ),
                _bottomIcon(
                  index: 2,
                  title: '캘린더',
                  selImg: 'assets/icon/tab/tabicon_calenderpurple_32.svg',
                  unSelImg: 'assets/icon/tab/tabicon_calendargray_32.svg',
                ),
                _bottomIcon(
                  index: 3,
                  title: '내 모임',
                  selImg: 'assets/icon/tab/tabicon_mymoimpurple_32.svg',
                  unSelImg: 'assets/icon/tab/tabicon_mymoimgray_32.svg',
                ),
                _bottomIcon(
                  index: 4,
                  title: '마이페이지',
                  selImg: 'assets/icon/tab/tabicon_mypagepurple.svg',
                  unSelImg: 'assets/icon/tab/tabicon_mypagegray_32.svg',
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget _bottomIcon({
    required int index,
    required String selImg,
    required String unSelImg,
    required String title,
  }) {
    return Consumer(builder: (context, ref, _) {
      final bottomIndex = ref.watch(bottomIndexProvider);
      return Expanded(
        flex: 1,
        child: InkWell(
          onTap: () => ref.read(bottomIndexProvider.state).state = index,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              SvgPicture.asset(bottomIndex == index ? selImg : unSelImg),
              Text(
                title,
                style: MomoTextStyle.card.copyWith(
                  color: bottomIndex == index
                      ? MomoColor.main
                      : MomoColor.unSelText,
                  fontSize: 11,
                ),
              ),
            ],
          ),
        ),
      );
    });
  }
}
