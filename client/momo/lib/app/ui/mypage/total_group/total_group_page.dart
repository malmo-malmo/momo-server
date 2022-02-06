import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:momo/app/model/group/total_group_info.dart';
import 'package:momo/app/provider/group/mypage_total_group_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/card/on_off_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';
import 'package:momo/app/ui/components/text/member_date_row.dart';
import 'package:momo/app/util/navigation_service.dart';

class TotalGroupPage extends ConsumerStatefulWidget {
  const TotalGroupPage({Key? key}) : super(key: key);

  @override
  ConsumerState<TotalGroupPage> createState() => _TotalGroupPageState();
}

class _TotalGroupPageState extends ConsumerState<TotalGroupPage> {
  int _curretnIndex = 0;

  @override
  void initState() {
    super.initState();
    Future.delayed(Duration.zero, () {
      ref.read(totalGroupStateProvider.notifier).getTotalGroups();
    });
  }

  @override
  Widget build(BuildContext context) {
    final totalGroups = ref.watch(totalGroupStateProvider);

    return SafeArea(
      child: DefaultTabController(
        length: 3,
        child: Scaffold(
          appBar: AppBar(
            leading: Consumer(
                builder: (context, ref, _) => InkWell(
                    onTap: () => ref.read(navigatorProvider).pop(),
                    child: const Icon(CupertinoIcons.back,
                        color: MomoColor.black))),
            title: const Text('모임 관리', style: MomoTextStyle.defaultStyle),
            bottom: TabBar(
              onTap: (index) {
                _curretnIndex = index;
                setState(() {});
              },
              indicatorColor: MomoColor.main,
              physics: const NeverScrollableScrollPhysics(),
              tabs: [
                Tab(
                  child: Text(
                    '전체(6)',
                    style: MomoTextStyle.normalR.copyWith(
                      color: _curretnIndex == 0 ? MomoColor.main : null,
                    ),
                  ),
                ),
                Tab(
                  child: Text(
                    '참여 중',
                    style: MomoTextStyle.normalR.copyWith(
                      color: _curretnIndex == 1 ? MomoColor.main : null,
                    ),
                  ),
                ),
                Tab(
                  child: Text(
                    '종료',
                    style: MomoTextStyle.normalR.copyWith(
                      color: _curretnIndex == 2 ? MomoColor.main : null,
                    ),
                  ),
                ),
              ],
            ),
          ),
          body: totalGroups.isLoading
              ? const LoadingCard()
              : TabBarView(
                  physics: const NeverScrollableScrollPhysics(),
                  children: [
                    _GroupListView(
                      groups: totalGroups.groups,
                    ),
                    _GroupListView(
                      groups: totalGroups.groups
                          .where((element) => !element.end)
                          .toList(),
                    ),
                    _GroupListView(
                      groups: totalGroups.groups
                          .where((element) => !element.end)
                          .toList(),
                    ),
                  ],
                ),
        ),
      ),
    );
  }
}

class _GroupListView extends ConsumerWidget {
  const _GroupListView({
    Key? key,
    required this.groups,
  }) : super(key: key);

  final List<TotalGroupInfo> groups;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    if (groups.isEmpty) {
      return const NoItemCard();
    }
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 20),
      child: ListView.separated(
        itemBuilder: (context, index) => InkWell(
          onTap: () {
            ref.read(navigatorProvider).navigateTo(
                  routeName: AppRoutes.groupDetail,
                  arguments: groups[index],
                );
          },
          child: SizedBox(
            height: 76.h,
            child: Row(
              children: [
                ClipRRect(
                  borderRadius: BorderRadius.circular(16),
                  child: Image.network(
                    groups[index].imageUrl ??
                        'https://file.mk.co.kr/meet/neds/2021/11/image_readtop_2021_1081514_16372040344854618.jpg',
                    height: 76.h,
                    width: 76.h,
                    fit: BoxFit.fill,
                  ),
                ),
                const SizedBox(width: 14),
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    OnOffCard(onOff: groups[index].offline),
                    Text(
                      groups[index].name,
                      style: MomoTextStyle.normal,
                    ),
                    MemberDateRow(
                      headNum: groups[index].participantCnt,
                      startDay: groups[index].startDate,
                      color: MomoColor.black,
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
        separatorBuilder: (context, index) => const SizedBox(
          height: 14,
        ),
        itemCount: groups.length,
      ),
    );
  }
}
