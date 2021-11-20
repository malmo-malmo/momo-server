import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/ui/meeting_detail/widget/admin_bottom_sheet.dart';
import 'package:momo/app/ui/meeting_detail/widget/feed_list.dart';
import 'package:momo/app/ui/meeting_detail/widget/meeting_title.dart';
import 'package:momo/app/ui/meeting_detail/widget/notice_list_card.dart';
import 'package:momo/app/ui/meeting_detail/widget/schedule_list_card.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class MeetingDetailPage extends StatelessWidget {
  const MeetingDetailPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: const Color(0xfff7f7f7),
        appBar: AppBar(
          backgroundColor: Colors.transparent,
          elevation: 0,
          leading: Consumer(
            builder: (context, ref, _) {
              return InkWell(
                onTap: () {
                  ref.read(navigatorProvider).pop();
                },
                child: const Icon(
                  CupertinoIcons.back,
                  color: MomoColor.white,
                ),
              );
            },
          ),
          actions: [
            Padding(
              padding: const EdgeInsets.only(right: 8),
              child: InkWell(
                onTap: () {
                  showModalBottomSheet(
                    context: context,
                    isScrollControlled: true,
                    shape: const RoundedRectangleBorder(
                      borderRadius: BorderRadius.only(
                        topLeft: Radius.circular(16),
                        topRight: Radius.circular(16),
                      ),
                    ),
                    builder: (context) {
                      return meetingDetailBottomSheetAdmin();
                      // return meetingDetailBottomSheetUser();
                    },
                  );
                },
                child: SvgPicture.asset('assets/icon/icon_ooowhite_28.svg'),
              ),
            ),
          ],
        ),
        extendBodyBehindAppBar: true,
        body: CustomScrollView(
          slivers: [
            const SliverToBoxAdapter(
              child: MeetingTitle(
                onOff: '온라인',
                meetingTitle: '테니스 왕자 모임',
                count: 3,
                startDate: '11/3~',
                location: '서울시 마포구',
                school: '모모대학교',
                img:
                    'https://blog.kakaocdn.net/dn/rvD0N/btqIN98covH/EvM2SADX36wTlh7G9etQu1/img.jpg',
              ),
            ),
            const SliverToBoxAdapter(
              child: ScheduleListCard(),
            ),
            SliverToBoxAdapter(
              child: Container(
                height: 10,
                color: MomoColor.backgroundColor,
              ),
            ),
            const SliverToBoxAdapter(
              child: NoticeListCard(),
            ),
            SliverToBoxAdapter(
              child: Padding(
                padding: const EdgeInsets.only(left: 16, top: 27, bottom: 17),
                child: Text(
                  '게시물',
                  style: MomoTextStyle.subTitle,
                ),
              ),
            ),
            const SliverPadding(
              padding: EdgeInsets.symmetric(horizontal: 16),
              sliver: FeedList(),
            ),
          ],
        ),
      ),
    );
  }
}
