import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/ui/request_meeting/widget/req_meeting_title.dart';
import 'package:momo/app/util/navigation_service.dart';
import 'package:momo/app/util/theme.dart';

class RequestMeetingPage extends ConsumerWidget {
  const RequestMeetingPage({
    Key? key,
    required this.img,
  }) : super(key: key);

  final String img;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return SafeArea(
      child: Scaffold(
          body: CustomScrollView(
        slivers: [
          SliverToBoxAdapter(
            child: SizedBox(
              width: double.infinity,
              height: 215.h,
              child: Stack(
                children: [
                  img.isEmpty
                      ? SizedBox(
                          height: 215.h,
                          width: double.infinity,
                          child: const Center(
                            child: Text('No Image'),
                          ),
                        )
                      : Image.network(
                          img,
                          height: 215.h,
                          width: double.infinity,
                          fit: BoxFit.fill,
                        ),
                  Padding(
                    padding: const EdgeInsets.only(left: 16, top: 16),
                    child: InkWell(
                      onTap: () {
                        ref.read(navigatorProvider).pop();
                      },
                      child: const Icon(
                        CupertinoIcons.back,
                        color: MomoColor.black,
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
          SliverToBoxAdapter(
            child: Padding(
              padding: const EdgeInsets.only(right: 16, left: 16, bottom: 24),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const ReqMeetingTitle(
                    onOff: '온라인',
                    meetingTitle: '테니스 왕자 모임',
                    count: 3,
                    startDate: '11/3~',
                    location: '서울시 마포구',
                    school: '모모대학교',
                  ),
                  const SizedBox(height: 24),
                  Text(
                    '모임 설명',
                    style: TextStyle(
                      fontSize: 16.sp,
                    ),
                  ),
                  const SizedBox(height: 16),
                  Text(
                    '청계천에서 매주 함께 달리고 일주일에 두번 산책하는 것을 인증하는 곳입니다. 꾸준히 할 사람만 신청해주세요',
                    style: TextStyle(
                      fontSize: 14.sp,
                    ),
                  ),
                  const SizedBox(height: 80),
                  SizedBox(
                    height: 57,
                    width: double.infinity,
                    child: ElevatedButton(
                      style: ButtonStyle(
                        shape:
                            MaterialStateProperty.all<RoundedRectangleBorder>(
                          RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(32),
                          ),
                        ),
                        backgroundColor: MaterialStateProperty.resolveWith(
                          (states) {
                            if (states.contains(MaterialState.disabled)) {
                              return const Color(0xfff2f2f2);
                            }
                            return const Color(0xff000000);
                          },
                        ),
                      ),
                      onPressed: () {},
                      child: Text(
                        '멤버 신청',
                        style: TextStyle(
                          fontSize: 16.sp,
                        ),
                      ),
                    ),
                  )
                ],
              ),
            ),
          ),
        ],
      )),
    );
  }
}
