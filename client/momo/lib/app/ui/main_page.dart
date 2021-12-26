import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/bottom_index_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/ui/calendar/calendar_page.dart';
import 'package:momo/app/ui/home/home_page.dart';
import 'package:momo/app/ui/my_meet/my_meet_page.dart';
import 'package:momo/app/ui/mypage/mypage.dart';
import 'package:momo/app/ui/search/search_page.dart';
import 'package:momo/app/util/navigation_service.dart';
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
        backgroundColor: const Color(0xfff7f7fd),
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
        return Consumer(builder: (context, ref, _) {
          return InkWell(
              onTap: () {
                ref
                    .read(navigatorProvider)
                    .navigateTo(routeName: AppRoutes.scheduleRequest);
              },
              child: SvgPicture.asset(
                  'assets/icon/calendar/floatingbtn_addschedule_84.svg'));
        });
      case 3:
        return Consumer(builder: (context, ref, _) {
          return InkWell(
            onTap: () => ref
                .read(navigatorProvider)
                .navigateTo(routeName: AppRoutes.groupRequest),
            child:
                SvgPicture.asset('assets/icon/meet/floatingbtn_addmoim_64.svg'),
          );
        });
      default:
        return null;
    }
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
