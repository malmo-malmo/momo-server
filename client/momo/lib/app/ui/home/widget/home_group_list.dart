import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/provider/group/home_group_provider.dart';
import 'package:momo/app/ui/components/card/group_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';

class HomeMeetingList extends ConsumerWidget {
  const HomeMeetingList({
    Key? key,
    required this.groups,
  }) : super(key: key);

  final List<GroupInfo> groups;

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return SizedBox(
      height: 200,
      child: groups.isEmpty
          ? const NoItemCard()
          : ListView.separated(
              scrollDirection: Axis.horizontal,
              itemBuilder: (context, index) => GroupCard(
                group: groups[index],
                setLike: () {
                  if (groups[index].favoriteGroup) {
                    ref
                        .read(homeGroupStateProvider.notifier)
                        .deleteGroupLike(groups[index].id);
                  } else {
                    ref
                        .read(homeGroupStateProvider.notifier)
                        .createGroupLike(groups[index].id);
                  }
                },
              ),
              separatorBuilder: (context, index) => const SizedBox(width: 14),
              itemCount: groups.length,
            ),
    );
  }
}
