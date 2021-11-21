import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/bottom_index_provider.dart';
import 'package:momo/app/provider/calendar/scroll_state_provider.dart';
import 'package:momo/app/ui/components/sub_title.dart';
import 'package:momo/app/ui/my_meet/widget/manage_meeting_list.dart';
import 'package:momo/app/ui/my_meet/widget/participation_meeting_list.dart';
import 'package:momo/app/util/theme.dart';

class MyMeetPage extends ConsumerStatefulWidget {
  const MyMeetPage({Key? key}) : super(key: key);

  @override
  ConsumerState<MyMeetPage> createState() => _MyMeetPageState();
}

class _MyMeetPageState extends ConsumerState<MyMeetPage> {
  final _scrollController = ScrollController();

  @override
  void initState() {
    _scrollController.addListener(() {
      final direction = _scrollController.position.userScrollDirection;
      if (direction == ScrollDirection.forward) {
        ref.read(checkScrollStateProvider.state).state = true;
      } else {
        ref.read(checkScrollStateProvider.state).state = false;
      }

      if (_scrollController.position.pixels < 10) {
        ref.read(scrollStateProvider.state).state = 0;
      } else if (_scrollController.position.pixels < 50) {
        ref.read(scrollStateProvider.state).state = 1;
      } else {
        ref.read(scrollStateProvider.state).state = 2;
      }
    });
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: SingleChildScrollView(
        controller: _scrollController,
        child: Padding(
          padding:
              const EdgeInsets.only(right: 16, left: 16, top: 43, bottom: 32),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  SvgPicture.asset(
                    'assets/icon/icon_msg_28.svg',
                  ),
                ],
              ),
              SizedBox(height: 12.h),
              Text(
                '내 모임',
                style: MomoTextStyle.mainTitle,
              ),
              subTitle(
                title: '내가 만든 모임',
                icon: 'assets/icon/meet/icon_manage_28.svg',
              ),
              const ManageMeetingList(),
              subTitle(
                title: '그 외 참여 모임',
                icon: 'assets/icon/meet/icon_othermoim.svg',
              ),
              const ParticipationMettingList(),
            ],
          ),
        ),
      ),
    );
  }
}
