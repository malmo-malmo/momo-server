import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:momo/app/provider/group/group_detail_provider.dart';
import 'package:momo/app/provider/user/user_data_provider.dart';
import 'package:momo/app/ui/components/app_bar/custom_app_bar.dart';
import 'package:momo/app/ui/components/status/error_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/group_detail/widget/admin_bottom_sheet.dart';
import 'package:momo/app/ui/group_detail/widget/feed_list.dart';
import 'package:momo/app/ui/components/card/group_detail_title.dart';
import 'package:momo/app/ui/group_detail/widget/group_detail_card.dart';
import 'package:momo/app/ui/group_detail/widget/request_info_card.dart';
import 'package:momo/app/ui/group_detail/widget/user_bottom_sheet.dart';
import 'package:momo/app/util/theme.dart';

class GroupDetailPage extends ConsumerWidget {
  const GroupDetailPage({
    Key? key,
    required this.groupId,
  }) : super(key: key);

  final int groupId;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final response = ref.watch(groupDetailFutureProvider(groupId));

    return response.when(
      error: (error, stackTrace) => errorCard(),
      loading: () => loadingCard(),
      data: (data) {
        final groupDetail = ref.watch(groupDetailProvider(data));

        return SafeArea(
          child: Scaffold(
            backgroundColor: const Color(0xfff7f7f7),
            appBar: customAppBar(
              leadingIcon: CupertinoIcons.back,
              isAction: groupDetail.isParticipant,
              actionWidget: groupDetail.isParticipant
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
                                  topRight: Radius.circular(16),
                                ),
                              ),
                              builder: (context) =>
                                  userData.id == groupDetail.managerId
                                      ? groupDetailBottomSheetAdmin()
                                      : groupDetailBottomSheetUser());
                        },
                        child: SvgPicture.asset(
                            'assets/icon/icon_ooowhite_28.svg'),
                      ),
                    )
                  : const SizedBox(),
              leadingIconColor: MomoColor.white,
              backgroundColor: Colors.transparent,
            ),
            extendBodyBehindAppBar: true,
            body: CustomScrollView(
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
                groupDetail.isParticipant
                    ? groupDetailCard(groupId: groupDetail.id)
                    : requestInfoCard(
                        introduction: groupDetail.introduction,
                        onTapButton: ref
                            .read(groupDetailStateProvider(data).notifier)
                            .participantGroup,
                      ),
                groupDetail.isParticipant
                    ? const SliverPadding(
                        padding: EdgeInsets.symmetric(horizontal: 16),
                        sliver: FeedList(),
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
