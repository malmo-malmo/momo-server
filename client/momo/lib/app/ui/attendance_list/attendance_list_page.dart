import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/group/participant_users_provider.dart';
import 'package:momo/app/provider/schedule/attendacne_response_provider.dart';
import 'package:momo/app/provider/schedule/attendance_create_provider.dart';
import 'package:momo/app/provider/schedule/attendance_provider_arg.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/attendance_list/widget/attendance_card.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/button/confirm_action_icon.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/util/navigation_service.dart';

class AttendanceListPage extends ConsumerWidget {
  const AttendanceListPage({
    Key? key,
    required this.groupId,
    required this.scheduleId,
    required this.isCheck,
  }) : super(key: key);

  final int groupId;
  final int scheduleId;
  final bool isCheck;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    if (isCheck) {
      final attendanceResponse =
          ref.watch(attendanceResponseStateProvider(scheduleId));

      if (attendanceResponse.isLoading) {
        return const Scaffold(
          body: LoadingCard(),
        );
      }

      return SafeArea(
        child: Scaffold(
          backgroundColor: MomoColor.backgroundColor,
          appBar: CustomAppBar(
            leadingIcon: CupertinoIcons.xmark,
            isAction: true,
            actionWidget: ConfirmActionIcon(
              check: true,
              title: '수정',
              onTapIcon: () async {
                await ref
                    .read(attendanceResponseStateProvider(scheduleId).notifier)
                    .updateAttendance();
                ref.read(navigatorProvider).pop();
              },
              isShowDialog: true,
              dialogText: '출석체크를 수정했습니다',
            ),
          ),
          body: Padding(
            padding: const EdgeInsets.symmetric(horizontal: 16),
            child: SingleChildScrollView(
              child: Column(
                children: [
                  Padding(
                    padding: const EdgeInsets.only(
                        top: 30, right: 24, left: 24, bottom: 16),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        const Text('닉네임', style: MomoTextStyle.defaultStyle),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.end,
                          children: const [
                            Text('출석', style: MomoTextStyle.defaultStyle),
                            SizedBox(width: 27),
                            Text('결석', style: MomoTextStyle.defaultStyle),
                          ],
                        ),
                      ],
                    ),
                  ),
                  Material(
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(20)),
                    elevation: 2,
                    child: Container(
                      padding: const EdgeInsets.symmetric(vertical: 16),
                      width: double.infinity,
                      height: 32 + 72.0 * attendanceResponse.attendances.length,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(20),
                        color: MomoColor.flutterWhite,
                      ),
                      child: ListView.builder(
                        itemCount: attendanceResponse.attendances.length,
                        physics: const NeverScrollableScrollPhysics(),
                        itemBuilder: (context, index) => attendanceCard(
                          name: attendanceResponse.attendances[index].nickname,
                          profile: attendanceResponse
                                  .attendances[index].imageUrl ??
                              'https://img.insight.co.kr/static/2019/04/20/700/mev0r133kiy3hx0u4c48.jpg',
                          rate: attendanceResponse
                                  .attendances[index].attendanceRate ??
                              0,
                          onSelect: ref
                              .read(attendanceResponseStateProvider(scheduleId)
                                  .notifier)
                              .checkAttendance,
                          attend:
                              attendanceResponse.attendances[index].isAttend,
                          userId: attendanceResponse
                              .attendances[index].attendanceId,
                        ),
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
      );
    } else {
      final memeberResponse = ref.watch(participantUsersProvider(groupId));

      return SafeArea(
        child: memeberResponse.when(
          error: (error, stackTrace) => const Scaffold(body: ErrorCard()),
          loading: () => const Scaffold(body: LoadingCard()),
          data: (data) {
            final attendacneCheckState = ref.watch(
              attendacneCreateStateProvider(
                AttendanceProviderArg(
                  scheduleId: scheduleId,
                  userIds: data.map((e) => e.participantId).toList(),
                ),
              ),
            );

            return Scaffold(
              backgroundColor: MomoColor.backgroundColor,
              appBar: CustomAppBar(
                leadingIcon: CupertinoIcons.xmark,
                isAction: true,
                actionWidget: ConfirmActionIcon(
                  check: true,
                  title: isCheck ? '수정' : '완료',
                  onTapIcon: () async {
                    await ref
                        .read(
                            attendacneCreateStateProvider(AttendanceProviderArg(
                          scheduleId: scheduleId,
                          userIds: data.map((e) => e.participantId).toList(),
                        )).notifier)
                        .createAttendance();
                    ref.read(navigatorProvider).pop(result: true);
                  },
                  isShowDialog: true,
                  dialogText: '출석체크를 완료했어요!',
                ),
              ),
              body: Padding(
                padding: const EdgeInsets.symmetric(horizontal: 16),
                child: SingleChildScrollView(
                  child: Column(
                    children: [
                      Padding(
                        padding: const EdgeInsets.only(
                            top: 30, right: 24, left: 24, bottom: 16),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            const Text('닉네임',
                                style: MomoTextStyle.defaultStyle),
                            Row(
                              mainAxisAlignment: MainAxisAlignment.end,
                              children: const [
                                Text('출석', style: MomoTextStyle.defaultStyle),
                                SizedBox(width: 27),
                                Text('결석', style: MomoTextStyle.defaultStyle),
                              ],
                            ),
                          ],
                        ),
                      ),
                      Material(
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(20)),
                        elevation: 2,
                        child: Container(
                          padding: const EdgeInsets.symmetric(vertical: 16),
                          width: double.infinity,
                          height: 32 + 72.0 * data.length,
                          decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(20),
                            color: MomoColor.flutterWhite,
                          ),
                          child: ListView.builder(
                            itemCount: data.length,
                            physics: const NeverScrollableScrollPhysics(),
                            itemBuilder: (context, index) => attendanceCard(
                              name: data[index].nickname,
                              profile: data[index].imageUrl ??
                                  'https://img.insight.co.kr/static/2019/04/20/700/mev0r133kiy3hx0u4c48.jpg',
                              rate: data[index].attendanceRate,
                              onSelect: ref
                                  .read(attendacneCreateStateProvider(
                                      AttendanceProviderArg(
                                    scheduleId: scheduleId,
                                    userIds: data
                                        .map((e) => e.participantId)
                                        .toList(),
                                  )).notifier)
                                  .checkAttendacne,
                              attend: attendacneCheckState
                                  .attendanceCreateRequests[index].attend,
                              userId: attendacneCheckState
                                  .attendanceCreateRequests[index]
                                  .participantId,
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            );
          },
        ),
      );
    }
  }
}
