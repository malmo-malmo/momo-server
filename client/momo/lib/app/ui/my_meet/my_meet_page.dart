import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/group/my_group_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/button/message_button.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/text/sub_title.dart';
import 'package:momo/app/ui/my_meet/widget/manage_meeting_list.dart';
import 'package:momo/app/ui/my_meet/widget/participation_meeting_list.dart';
import 'package:momo/app/util/navigation_service.dart';

class MyMeetPage extends ConsumerStatefulWidget {
  const MyMeetPage({Key? key}) : super(key: key);

  @override
  ConsumerState<MyMeetPage> createState() => _MyMeetPageState();
}

class _MyMeetPageState extends ConsumerState<MyMeetPage> {
  @override
  void initState() {
    super.initState();
    Future.delayed(Duration.zero, () {
      ref.read(myGroupStateProvider.notifier).getMyGroups();
    });
  }

  @override
  Widget build(BuildContext context) {
    final myGroupList = ref.watch(myGroupStateProvider);

    return SafeArea(
      child: Scaffold(
        floatingActionButton: InkWell(
          onTap: () async {
            final result = await ref.read(navigatorProvider).navigateTo(
                  routeName: AppRoutes.groupRequest,
                );
            ref.read(myGroupStateProvider.notifier).createGroupCallback(result);
          },
          child:
              SvgPicture.asset('assets/icon/meet/floatingbtn_addmoim_64.svg'),
        ),
        body: SingleChildScrollView(
          child: Padding(
            padding:
                const EdgeInsets.only(right: 16, left: 16, top: 43, bottom: 32),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const MessageButton(),
                SizedBox(height: 12.h),
                const Text(
                  '내 모임',
                  style: MomoTextStyle.mainTitle,
                ),
                const SubTitle(
                  title: '내가 만든 모임',
                  icon: 'assets/icon/meet/icon_manage_28.svg',
                ),
                myGroupList.isLoading
                    ? const LoadingCard()
                    : ManageMeetingList(
                        myGroups: myGroupList.myGroups,
                      ),
                const SubTitle(
                  title: '그 외 참여 모임',
                  icon: 'assets/icon/meet/icon_othermoim.svg',
                ),
                myGroupList.isLoading
                    ? const LoadingCard()
                    : ParticipationMettingList(
                        participationGroups: myGroupList.participationGroups,
                      ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
