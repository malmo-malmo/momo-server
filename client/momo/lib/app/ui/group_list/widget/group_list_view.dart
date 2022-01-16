import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/ui/components/card/group_card.dart';

class GroupListView extends StatelessWidget {
  const GroupListView({
    Key? key,
    required this.pagingController,
    required this.setLike,
  }) : super(key: key);

  final PagingController<int, GroupInfo> pagingController;
  final Function setLike;

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: PagedGridView(
        pagingController: pagingController,
        builderDelegate: PagedChildBuilderDelegate<GroupInfo>(
          itemBuilder: (context, item, index) => GroupCard(
            group: item,
            height: 200.h,
            width: double.infinity,
            setLike: setLike,
          ),
        ),
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
          mainAxisExtent: 200.h,
          mainAxisSpacing: 14,
          crossAxisSpacing: 14,
          childAspectRatio: 100 / 150,
        ),
        showNewPageProgressIndicatorAsGridChild: false,
        showNewPageErrorIndicatorAsGridChild: false,
        showNoMoreItemsIndicatorAsGridChild: false,
      ),
    );
  }
}
