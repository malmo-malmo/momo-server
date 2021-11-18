import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/bottom_index_provider.dart';
import 'package:momo/app/ui/calendar/calendar_page.dart';
import 'package:momo/app/ui/home/home_page.dart';
import 'package:momo/app/ui/my_meet/my_meet_page.dart';
import 'package:momo/app/ui/mypage/mypage.dart';
import 'package:momo/app/ui/search/search_page.dart';
import 'package:momo/app/util/theme.dart';

class MainPage extends ConsumerWidget {
  const MainPage({Key? key}) : super(key: key);

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
    final visible = ref.watch(checkScrollProvider);

    return SafeArea(
      child: Scaffold(
        body: _pages[index],
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
              bottomIcon(index: 0, icon: CupertinoIcons.home, title: '홈'),
              bottomIcon(index: 1, icon: CupertinoIcons.search, title: '검색'),
              bottomIcon(index: 2, icon: CupertinoIcons.calendar, title: '캘린더'),
              bottomIcon(
                  index: 3, icon: CupertinoIcons.person_2, title: '내 모임'),
              bottomIcon(index: 4, icon: CupertinoIcons.person, title: '마이페이지'),
            ],
          ),
        ),
        floatingActionButton: _floatingButton(index, visible),
      ),
    );
  }

  Widget? _floatingButton(int index, bool isShow) {
    switch (index) {
      case 0:
        return null;
      case 1:
        return null;

      case 2:
        return isShow
            ? FloatingActionButton(
                onPressed: () {},
                backgroundColor: MomoColor.main,
                child: const Icon(
                  CupertinoIcons.calendar_today,
                ),
              )
            : null;
      case 3:
        return isShow
            ? FloatingActionButton(
                onPressed: () {},
                backgroundColor: MomoColor.main,
                child: const Icon(
                  CupertinoIcons.app_fill,
                ),
              )
            : null;
      default:
        return null;
    }
  }

  Widget bottomIcon({
    required int index,
    required IconData icon,
    required String title,
  }) {
    return Consumer(builder: (context, ref, _) {
      final bottomIndex = ref.watch(bottomIndexProvider);
      return Expanded(
        flex: 1,
        child: InkWell(
          onTap: () {
            ref.read(bottomIndexStateProvider.state).state = index;
          },
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Icon(
                icon,
                color:
                    bottomIndex == index ? MomoColor.main : MomoColor.unSelIcon,
              ),
              Text(
                title,
                style: TextStyle(
                  color: bottomIndex == index
                      ? MomoColor.main
                      : MomoColor.unSelText,
                  fontSize: 11.sp,
                ),
              ),
            ],
          ),
        ),
      );
    });
  }
}
