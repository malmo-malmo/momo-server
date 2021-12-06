import 'package:flutter/material.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';
import 'package:momo/app/ui/search/widget/search_result_card.dart';

class SearchResultList extends StatelessWidget {
  const SearchResultList({
    Key? key,
    required this.pagingController,
  }) : super(key: key);

  final PagingController<int, GroupInfo> pagingController;

  @override
  Widget build(BuildContext context) {
    return PagedSliverList.separated(
      pagingController: pagingController,
      builderDelegate: PagedChildBuilderDelegate<GroupInfo>(
        itemBuilder: (context, item, index) => searchResultCard(group: item),
        newPageProgressIndicatorBuilder: (context) => loadingCard(),
        firstPageProgressIndicatorBuilder: (context) => loadingCard(),
        noItemsFoundIndicatorBuilder: (context) => noItemCard(),
      ),
      separatorBuilder: (context, index) => const SizedBox(height: 14),
    );
  }
}
