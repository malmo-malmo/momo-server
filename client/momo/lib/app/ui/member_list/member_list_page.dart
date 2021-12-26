import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/group/participant_users_provider.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/member_list/widget/admin_dialog.dart';
import 'package:momo/app/ui/member_list/widget/member_card.dart';
import 'package:momo/app/util/navigation_service.dart';

class MemberListPage extends ConsumerWidget {
  const MemberListPage({Key? key, required this.groupId}) : super(key: key);

  final int groupId;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final memeberResponse = ref.watch(participantUsersProvider(groupId));

    return SafeArea(
      child: memeberResponse.when(
        error: (error, stackTrace) => const ErrorCard(),
        loading: () => const LoadingCard(),
        data: (data) {
          final checks = ref.watch(participantCheckStateProvider(data.length));
          final checkIndex = ref.watch(isCheckUserProvider(data.length));

          return Scaffold(
            appBar: CustomAppBar(
              leadingIcon: CupertinoIcons.xmark,
              isAction: true,
              actionWidget: InkWell(
                onTap: checkIndex != -1
                    ? () async {
                        final check = await showDialog(
                          context: context,
                          builder: (context) =>
                              adminDialog(participantUser: data[checkIndex]),
                        );
                        if (check) {
                          ref.read(navigatorProvider).pop(result: true);
                        }
                      }
                    : null,
                child: Padding(
                  padding: const EdgeInsets.symmetric(vertical: 10),
                  child: Container(
                    height: 36,
                    width: 64,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(16),
                      color: checkIndex != -1
                          ? MomoColor.main
                          : MomoColor.checkBackground,
                    ),
                    child: Center(
                        child: Text(
                      '완료',
                      style: MomoTextStyle.small.copyWith(
                        color: checkIndex != -1
                            ? MomoColor.white
                            : MomoColor.unSelIcon,
                      ),
                    )),
                  ),
                ),
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
                        children: const [
                          Text('닉네임', style: MomoTextStyle.defaultStyle),
                          Text('선택', style: MomoTextStyle.defaultStyle),
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
                        ),
                        child: ListView.builder(
                          itemCount: data.length,
                          physics: const NeverScrollableScrollPhysics(),
                          itemBuilder: (context, index) => memberCard(
                            name: data[index].nickname,
                            profile: data[index].image ??
                                'https://img.insight.co.kr/static/2019/04/20/700/mev0r133kiy3hx0u4c48.jpg',
                            rate: data[index].attendanceRate,
                            check: checks[index],
                            index: index,
                            onSelect: ref
                                .read(participantCheckStateProvider(data.length)
                                    .notifier)
                                .check,
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
