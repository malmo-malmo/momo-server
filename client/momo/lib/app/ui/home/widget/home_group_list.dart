import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:momo/app/model/group/group_info.dart';
import 'package:momo/app/ui/components/card/home_group_card.dart';
import 'package:momo/app/ui/components/status/loading_card.dart';
import 'package:momo/app/ui/components/status/no_item_card.dart';
import 'package:momo/app/util/format/group_date_format.dart';

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
          itemBuilder: (context, item, index) => homeGroupCard(
            title: item.name,
            onOff: item.offline,
            headNum: item.participantCnt,
            date: groupDateFormat(item.startDate),
            img:
                'https://newsimg.hankookilbo.com/cms/articlerelease/2020/09/09/11106de2-cc63-4fae-80dc-85cbeafb6040.jpg',
            height: 200,
            width: 144,
          ),
          newPageProgressIndicatorBuilder: (context) => loadingCard(),
          firstPageProgressIndicatorBuilder: (context) => loadingCard(),
          noItemsFoundIndicatorBuilder: (context) => noItemCard(),
        ),
        separatorBuilder: (context, index) => const SizedBox(width: 14),
      ),
    );
  }
}
