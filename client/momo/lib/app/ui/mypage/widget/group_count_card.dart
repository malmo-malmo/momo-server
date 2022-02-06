import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/group/mypage_group_count_provider.dart';
import 'package:momo/app/routes/app_routers.dart';
import 'package:momo/app/theme/theme.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/mypage/total_group/total_group_page.dart';
import 'package:momo/app/ui/mypage/widget/info_column.dart';
import 'package:momo/app/util/navigation_service.dart';

class GroupCountCard extends ConsumerStatefulWidget {
  const GroupCountCard({Key? key}) : super(key: key);

  @override
  ConsumerState<ConsumerStatefulWidget> createState() => _GroupCountCardState();
}

class _GroupCountCardState extends ConsumerState<GroupCountCard> {
  @override
  void initState() {
    super.initState();
    Future.delayed(Duration.zero, () {
      ref.read(mypageGroupCountProvider.notifier).getGroupCounts();
    });
  }

  @override
  Widget build(BuildContext context) {
    final groupCounts = ref.watch(mypageGroupCountProvider);
    return Container(
      height: 100,
      padding: const EdgeInsets.only(top: 16, right: 45, left: 45),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20),
        color: MomoColor.scaffoldBackground,
      ),
      child: groupCounts.isLoading
          ? const LoadingCard()
          : Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                InfoColumn(
                  count: groupCounts.groupCount,
                  title: '총 모임',
                  onTap: () {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => const TotalGroupPage()));
                  },
                ),
                InfoColumn(
                  count: groupCounts.favoriteGroupCount,
                  title: '찜한 모임',
                  onTap: () {
                    ref
                        .read(navigatorProvider)
                        .navigateTo(routeName: AppRoutes.wishGroup);
                  },
                ),
                InfoColumn(
                  count: groupCounts.badgeCount,
                  title: '획득뱃지',
                  onTap: () {},
                ),
              ],
            ),
    );
  }
}
