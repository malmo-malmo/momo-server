import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/ui/components/card/group_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';

class HomeMeetingList extends StatelessWidget {
  const HomeMeetingList({
    Key? key,
    required this.pagingController,
  }) : super(key: key);

  final PagingController<int, GroupInfo> pagingController;
  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 200,
      child: PagedListView<int, GroupInfo>.separated(
        pagingController: pagingController,
        scrollDirection: Axis.horizontal,
        builderDelegate: PagedChildBuilderDelegate<GroupInfo>(
          itemBuilder: (context, item, index) => GroupCard(group: item),
          newPageProgressIndicatorBuilder: (context) => const LoadingCard(),
          firstPageProgressIndicatorBuilder: (context) => const LoadingCard(),
          noItemsFoundIndicatorBuilder: (context) => const NoItemCard(),
        ),
        separatorBuilder: (context, index) => const SizedBox(width: 14),
      ),
    );
  }
}
