import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/provider/group/home_group_provider.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/text/sub_title.dart';
import 'package:momo/app/ui/home/widget/home_group_list.dart';

class HomeGroupColumn extends ConsumerStatefulWidget {
  const HomeGroupColumn({
    Key? key,
  }) : super(key: key);

  @override
  ConsumerState<ConsumerStatefulWidget> createState() =>
      _HomeGroupColumnState();
}

class _HomeGroupColumnState extends ConsumerState<HomeGroupColumn> {
  @override
  void initState() {
    super.initState();
    Future.delayed(Duration.zero, () {
      ref.read(homeGroupStateProvider.notifier).getGroups();
    });
  }

  @override
  Widget build(BuildContext context) {
    final homeGroup = ref.watch(homeGroupStateProvider);

    if (homeGroup.isLoading) {
      return const SizedBox(
        height: 300,
        width: double.infinity,
        child: LoadingCard(),
      );
    }
    return Column(
      children: [
        SubTitle(
          title: '추천',
          icon: 'assets/icon/home/icon_recommend_28.svg',
          actionIcon: Icons.add,
          favoriteCallBack:
              ref.read(homeGroupStateProvider.notifier).groupLikeCallBack,
        ),
        HomeMeetingList(
          groups: homeGroup.categoryGroup,
        ),
        SubTitle(
          title: '내 학교',
          icon: 'assets/icon/home/icon_myschool_28.svg',
          actionIcon: Icons.add,
          favoriteCallBack:
              ref.read(homeGroupStateProvider.notifier).groupLikeCallBack,
        ),
        HomeMeetingList(
          groups: homeGroup.universityGroup,
        ),
        SubTitle(
          title: '주변',
          icon: 'assets/icon/home/icon_location_28.svg',
          actionIcon: Icons.add,
          favoriteCallBack:
              ref.read(homeGroupStateProvider.notifier).groupLikeCallBack,
        ),
        HomeMeetingList(
          groups: homeGroup.districtGroup,
        ),
      ],
    );
  }
}
