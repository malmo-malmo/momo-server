import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/group/group_summary_provider.dart';
import 'package:momo/app/provider/schedule/schedule_request_provider.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/input_box/custom_dropdown_list.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/text/sub_title.dart';
import 'package:momo/app/util/offset/cal_offset.dart';

final _groupNameDropDownKey = GlobalKey();

class GroupNameDropDown extends ConsumerWidget {
  const GroupNameDropDown({Key? key, this.groupId}) : super(key: key);

  final int? groupId;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final scheduleRequest = ref.watch(scheduleRequestProvider(groupId));
    final groupSumamry = ref.watch(groupSummaryProvider);
    return groupSumamry.when(
      error: (error, stacktrace) => const ErrorCard(),
      loading: () => const LoadingCard(),
      data: (data) {
        return Column(
          children: [
            const SubTitle(title: '모임선택'),
            Container(
              key: _groupNameDropDownKey,
              padding: const EdgeInsets.symmetric(horizontal: 16),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(16),
                color: MomoColor.backgroundColor,
              ),
              height: 44,
              width: double.infinity,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Text(
                    scheduleRequest.groupId == -1
                        ? '모임을 선택해주세요'
                        : data
                            .where((e) => e.id == scheduleRequest.groupId)
                            .first
                            .name,
                    style: MomoTextStyle.defaultStyleR.copyWith(
                      color: scheduleRequest.groupId == -1
                          ? MomoColor.unSelIcon
                          : null,
                    ),
                  ),
                  InkWell(
                    onTap: () {
                      Navigator.push(
                        context,
                        PageRouteBuilder(
                          opaque: false,
                          pageBuilder:
                              (context, animation, secondaryAnimation) =>
                                  CustomDropDownList(
                            values: data.map((e) => e.name).toList(),
                            setValue: (name) {
                              ref
                                  .read(scheduleRequestStateProvider(groupId)
                                      .notifier)
                                  .setGroupId(data
                                      .where((element) => element.name == name)
                                      .first
                                      .id);
                            },
                            curValue: scheduleRequest.groupId == -1
                                ? ''
                                : data
                                    .where((element) =>
                                        element.id == scheduleRequest.groupId)
                                    .first
                                    .name,
                            offset: getParentOffset(_groupNameDropDownKey),
                            defaultValue: data.first.name,
                            width: MediaQuery.of(context).size.width - 32,
                            backgroundColor: MomoColor.backgroundColor,
                          ),
                          transitionsBuilder:
                              (context, animation, secondaryAnimation, child) =>
                                  FadeTransition(
                            opacity: animation,
                            child: child,
                          ),
                        ),
                      );
                    },
                    child: Transform.rotate(
                      angle: pi * 3 / 2,
                      child: const Icon(
                        CupertinoIcons.back,
                        size: 20,
                      ),
                    ),
                  )
                ],
              ),
            ),
          ],
        );
      },
    );
  }
}
