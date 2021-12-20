import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/ui/components/text/sub_title.dart';
import 'package:momo/app/ui/my_meet/widget/manage_meeting_list.dart';
import 'package:momo/app/ui/my_meet/widget/participation_meeting_list.dart';
import 'package:momo/app/util/theme.dart';

class MyMeetPage extends ConsumerStatefulWidget {
  const MyMeetPage({Key? key}) : super(key: key);

  @override
  ConsumerState<MyMeetPage> createState() => _MyMeetPageState();
}

class _MyMeetPageState extends ConsumerState<MyMeetPage> {
  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: SingleChildScrollView(
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
              const Text(
                '내 모임',
                style: MomoTextStyle.mainTitle,
              ),
              const SubTitle(
                title: '내가 만든 모임',
                icon: 'assets/icon/meet/icon_manage_28.svg',
              ),
              const ManageMeetingList(),
              const SubTitle(
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
