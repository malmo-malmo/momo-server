import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/ui/components/card/home_group_card.dart';
import 'package:momo/app/util/date_format.dart';

class GroupListView extends StatelessWidget {
  const GroupListView({Key? key, required this.pagingController})
      : super(key: key);

  final PagingController<int, GroupInfo> pagingController;

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: PagedGridView(
        pagingController: pagingController,
        builderDelegate: PagedChildBuilderDelegate<GroupInfo>(
          itemBuilder: (context, item, index) => homeGroupCard(
            onOff: item.offline,
            title: item.name,
            headNum: item.participantCnt,
            date: groupDateFormat(item.startDate),
            img:
                'https://newsimg.hankookilbo.com/cms/articlerelease/2020/09/09/11106de2-cc63-4fae-80dc-85cbeafb6040.jpg',
            height: 200.h,
            width: double.infinity,
          ),
        ),
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
          mainAxisExtent: 200.h,
          mainAxisSpacing: 16,
          crossAxisSpacing: 16,
          childAspectRatio: 100 / 150,
        ),
        showNewPageProgressIndicatorAsGridChild: false,
        showNewPageErrorIndicatorAsGridChild: false,
        showNoMoreItemsIndicatorAsGridChild: false,
      ),
    );
  }
}
