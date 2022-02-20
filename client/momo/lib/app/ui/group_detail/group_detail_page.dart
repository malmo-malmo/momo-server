import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/group/group_detail_provider.dart';
import 'package:momo/app/provider/user/user_data_provider.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/button/confirm_button.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/group_detail/widget/admin_bottom_sheet.dart';
import 'package:momo/app/ui/group_detail/widget/post_list_view.dart';
import 'package:momo/app/ui/components/card/group_detail_title.dart';
import 'package:momo/app/ui/group_detail/widget/group_detail_card.dart';
import 'package:momo/app/ui/group_detail/widget/request_info_card.dart';
import 'package:momo/app/ui/group_detail/widget/user_bottom_sheet.dart';

class GroupDetailPage extends ConsumerWidget {
  const GroupDetailPage({
    Key? key,
    required this.groupId,
  }) : super(key: key);

  final int groupId;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final userId = ref.watch(userDataProvider).id;
    final response = ref.watch(groupDetailFutureProvider(groupId));

    return response.when(
      error: (error, stackTrace) => const Scaffold(body: ErrorCard()),
      loading: () => const Scaffold(body: LoadingCard()),
      data: (data) {
        final groupDetail = ref.watch(groupDetailStateProvider(data));

        return SafeArea(
          child: Scaffold(
            appBar: CustomAppBar(
              leadingIcon: CupertinoIcons.back,
              isAction: !groupDetail.end && groupDetail.participant,
              actionWidget: groupDetail.participant
                  ? Padding(
                      padding: const EdgeInsets.only(right: 8),
                      child: InkWell(
                          onTap: () {
                            showModalBottomSheet(
                                context: context,
                                isScrollControlled: true,
                                shape: const RoundedRectangleBorder(
                                    borderRadius: BorderRadius.only(
                                        topLeft: Radius.circular(16),
                                        topRight: Radius.circular(16))),
                                builder: (context) => userId ==
                                        groupDetail.managerId
                                    ? AdminBottomSheet(
                                        groupId: groupDetail.id,
                                        groupDetail: groupDetail,
                                      )
                                    : UserBottomSheet(groupId: groupDetail.id));
                          },
                          child: SvgPicture.asset(
                              'assets/icon/icon_ooowhite_28.svg')))
                  : const SizedBox(),
              leadingIconColor: MomoColor.white,
              backgroundColor: Colors.transparent,
            ),
            extendBodyBehindAppBar: true,
            body: groupDetail.end
                ? CustomScrollView(
                    slivers: [
                      SliverToBoxAdapter(
                        child: GroupDetailTitle(
                          title: groupDetail.name,
                          onOff: groupDetail.offline,
                          count: groupDetail.participantCnt,
                          startDate: groupDetail.startDate,
                          city: groupDetail.city,
                          university: groupDetail.university,
                          district: groupDetail.district,
                          img: groupDetail.imageUrl,
                        ),
                      ),
                      SliverToBoxAdapter(
                        child: Column(
                          children: [
                            Container(
                              color: const Color(0xffffffff),
                              height: 160,
                              width: double.infinity,
                              padding: const EdgeInsets.symmetric(
                                  horizontal: 16, vertical: 32),
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  const Text('모임 설명',
                                      style: MomoTextStyle.subTitle),
                                  const SizedBox(height: 14),
                                  Text(groupDetail.introduction,
                                      style: MomoTextStyle.normal),
                                ],
                              ),
                            ),
                            const SizedBox(height: 275),
                            Padding(
                              padding:
                                  const EdgeInsets.symmetric(horizontal: 16),
                              child: ConfirmButton(
                                onPressButton: () {},
                                buttonText: '마감',
                                check: false,
                              ),
                            ),
                            const SizedBox(height: 36),
                          ],
                        ),
                      )
                    ],
                  )
                : CustomScrollView(
                    slivers: [
                      SliverToBoxAdapter(
                        child: GroupDetailTitle(
                          title: groupDetail.name,
                          onOff: groupDetail.offline,
                          count: groupDetail.participantCnt,
                          startDate: groupDetail.startDate,
                          city: groupDetail.city,
                          university: groupDetail.university,
                          district: groupDetail.district,
                          img: groupDetail.imageUrl,
                        ),
                      ),
                      groupDetail.participant
                          ? groupDetailCard(groupId: groupDetail.id)
                          : requestInfoCard(
                              introduction: groupDetail.introduction,
                              onTapButton: () {
                                ref
                                    .read(
                                        groupDetailStateProvider(data).notifier)
                                    .participantGroup();
                              },
                            ),
                      groupDetail.participant
                          ? SliverPadding(
                              padding:
                                  const EdgeInsets.symmetric(horizontal: 16),
                              sliver: PostListView(groupId: groupDetail.id),
                            )
                          : const SliverToBoxAdapter()
                    ],
                  ),
          ),
        );
      },
    );
  }
}
