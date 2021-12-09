import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/group/participant_users_provider.dart';
import 'package:momo/app/provider/schedule/attendance_check_provider.dart';
import 'package:momo/app/ui/attendance_list/widget/attendance_card.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/button/confirm_action_icon.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/util/theme.dart';

class AttendanceListPage extends ConsumerWidget {
  const AttendanceListPage({
    Key? key,
    required this.groupId,
  }) : super(key: key);

  final int groupId;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final memeberResponse = ref.watch(participantUsersProvider(groupId));

    return SafeArea(
      child: memeberResponse.when(
        error: (error, stackTrace) => Scaffold(body: errorCard()),
        loading: () => Scaffold(body: loadingCard()),
        data: (data) {
          final checks = ref.watch(attendanceCheckProvider(data.length));
          final check = ref.watch(isCheckAttendance(data.length));

          return Scaffold(
            backgroundColor: MomoColor.backgroundColor,
            appBar: customAppBar(
              leadingIcon: CupertinoIcons.xmark,
              isAction: true,
              actionWidget: confirmActionIcon(
                  check: check,
                  title: '완료',
                  onTapIcon: () {},
                  isShowDialog: true,
                  dialogText: '출석체크를 완료했어요!'),
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
                          Text('닉네임', style: MomoTextStyle.defaultStyle),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.end,
                            children: [
                              Text('출석', style: MomoTextStyle.defaultStyle),
                              const SizedBox(width: 27),
                              Text('결석', style: MomoTextStyle.defaultStyle),
                            ],
                          ),
                        ],
                      ),
                    ),
                    Material(
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(20),
                      ),
                      elevation: 2,
                      child: Container(
                        padding: const EdgeInsets.symmetric(vertical: 16),
                        width: double.infinity,
                        height: 32 + 72.0 * data.length,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(20),
                          color: const Color(0xffffffff),
                        ),
                        child: ListView.builder(
                          itemCount: data.length,
                          physics: const NeverScrollableScrollPhysics(),
                          itemBuilder: (context, index) => attendanceCard(
                            name: data[index].nickname,
                            profile: data[index].image ??
                                'https://img.insight.co.kr/static/2019/04/20/700/mev0r133kiy3hx0u4c48.jpg',
                            rate: data[index].attendanceRate,
                            index: index,
                            onSelect: ref
                                .read(attendanceCheckStateProvider(data.length)
                                    .notifier)
                                .check,
                            checkIndex: checks[index],
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
