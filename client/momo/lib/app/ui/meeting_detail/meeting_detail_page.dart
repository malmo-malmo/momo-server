import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/ui/meeting_detail/widget/feed_list.dart';
import 'package:momo/app/ui/meeting_detail/widget/meeting_detail_bottom_sheet.dart';
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
        backgroundColor: const Color(0xffffffff),
        appBar: AppBar(
          backgroundColor: const Color(0xffffffff),
          elevation: 0,
          leading: Consumer(
            builder: (context, ref, _) {
              return InkWell(
                onTap: () {
                  ref.read(navigatorProvider).pop();
                },
                child: const Icon(
                  Icons.arrow_back,
                  color: MomoColor.black,
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
                      // return meetingDetailBottomSheetAdmin();
                      return meetingDetailBottomSheetUser();
                    },
                  );
                },
                child: const Icon(
                  Icons.info,
                  color: MomoColor.main,
                  size: 40,
                ),
              ),
            ),
          ],
        ),
        body: const Padding(
          padding: EdgeInsets.symmetric(horizontal: 16),
          child: CustomScrollView(
            slivers: [
              SliverToBoxAdapter(
                child: MeetingTitle(
                  onOff: '온라인',
                  meetingTitle: '테니스 왕자 모임',
                  count: 3,
                  startDate: '11/3~',
                  location: '서울시 마포구',
                  school: '모모대학교',
                ),
              ),
              SliverToBoxAdapter(
                child: ScheduleListCard(),
              ),
              SliverToBoxAdapter(
                child: NoticeListCard(),
              ),
              FeedList()
            ],
          ),
        ),
      ),
    );
  }
}
