import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/provider/bottom_index_provider.dart';
import 'package:momo/app/provider/calendar/scroll_state_provider.dart';
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
                  Icon(
                    CupertinoIcons.chat_bubble_fill,
                    size: 28.w,
                    color: MomoColor.main,
                  ),
                ],
              ),
              SizedBox(height: 12.h),
              Text(
                '내 모임',
                style: TextStyle(
                  fontSize: 28.sp,
                  fontWeight: FontWeight.bold,
                ),
              ),
              _subTitle(title: '직접 관리 모임', icon: CupertinoIcons.pencil),
              const ManageMeetingList(),
              _subTitle(
                  title: '그 외 참여 모임', icon: CupertinoIcons.download_circle),
              ParticipationMettingList(),
            ],
          ),
        ),
      ),
    );
  }

  Widget _subTitle({required String title, required IconData icon}) {
    return Padding(
      padding: const EdgeInsets.only(top: 43, bottom: 14),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Row(
            children: [
              Icon(
                icon,
                size: 28.sp,
                color: MomoColor.main,
              ),
              SizedBox(width: 10.w),
              Text(
                title,
                style: TextStyle(
                  fontSize: 20.sp,
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
